package com.example.comunicacaoentreatividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.List;

public class CadastrarAlunoActivity extends AppCompatActivity {
    EditText etNome;
    EditText etCPF;
    EditText etEmail;
    EditText etIdade;
    Button btnConfirmCadastro;

    public static final String EXTRA_ALUNO ="Aluno";
    public static final String EXTRA_RESULTADO="result";

    List<Aluno> listaAlunos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_aluno);

        etCPF = (EditText) findViewById(R.id.tvCPF);
        etNome = (EditText) findViewById(R.id.tvNome);
        etEmail = (EditText) findViewById(R.id.tvEmail);
        etIdade = (EditText) findViewById(R.id.tvIdade);
        btnConfirmCadastro = (Button) findViewById(R.id.btnCadastroConcluir);
        listaAlunos = getIntent().getParcelableArrayListExtra("Alunos");

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(etCPF,smf);
        etCPF.addTextChangedListener(mtw);
        btnConfirmCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno aluno = new Aluno(etCPF.getText().toString(),etNome.getText().toString(),
                        Integer.parseInt(etIdade.getText().toString()),etEmail.getText().toString());
                Intent toMainMenufromCad = new Intent(getApplicationContext(), MainActivity.class);
                toMainMenufromCad.putExtra(EXTRA_RESULTADO, aluno);
                setResult(RESULT_OK, toMainMenufromCad);
                finish();

            }
        });
    }
}
