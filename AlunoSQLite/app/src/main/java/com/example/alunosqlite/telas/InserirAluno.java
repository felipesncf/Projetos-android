package com.example.alunosqlite.telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alunosqlite.R;
import com.example.alunosqlite.daos.AlunoDAO;
import com.example.alunosqlite.model.Aluno;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class InserirAluno extends AppCompatActivity {
    EditText cpf;
    EditText nome;
    EditText idade;
    Aluno alunoSelecionado;
    Button cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_aluno);
        Button botao = (Button)findViewById(R.id.button);


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlunoDAO crud = new AlunoDAO(getBaseContext());
                cpf = (EditText)findViewById(R.id.editText);
                nome = (EditText)findViewById(R.id.editText2);
                idade = (EditText)findViewById(R.id.editText3);

                SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
                MaskTextWatcher mtw = new MaskTextWatcher(cpf,smf);
                cpf.addTextChangedListener(mtw);

                String cpfString = cpf.getText().toString();
                String nomeString = nome.getText().toString();
                String idadeString = idade.getText().toString();
                String resultado;

                alunoSelecionado = new Aluno(0,cpfString,nomeString,idadeString);
                resultado = crud.insereDado(alunoSelecionado);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent i=new Intent(InserirAluno.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        cancelar = (Button) findViewById(R.id.button2);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InserirAluno.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
