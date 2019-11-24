package com.example.comunicacaoentreatividades;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ALUNO=1;

    Button btnCadastrarAluno;
    Button btnPesquisarAluno;
    Aluno alunotocad;

    private static final int REQUEST_STRING=1;

    static ArrayList<Aluno> listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarAluno = (Button) findViewById(R.id.btnCadastrarAluno);
        btnPesquisarAluno = (Button) findViewById(R.id.btnPesquisarAluno);

        if(listaAlunos != null){

        }else{
            listaAlunos = new ArrayList<>();
        }

        Intent received = getIntent();

        if(received.getParcelableExtra("AlunoEdited") != null){
            Aluno editcomparator = received.getParcelableExtra("AlunoEdited");
            for (Aluno aluno: listaAlunos
            ) {
                if (aluno.getCpf().equals(editcomparator.getCpf())){
                    aluno.setEmail(editcomparator.getEmail());
                    aluno.setIdade(editcomparator.getIdade());
                    aluno.setNome(editcomparator.getNome());
                    Toast toastedit = Toast.makeText(getApplicationContext(),"Aluno Editado !", Toast.LENGTH_SHORT);
                    toastedit.show();
                }
            }
        }

        if(received.getParcelableExtra("AlunoExcluded") != null){
            Aluno excludcomparator = received.getParcelableExtra("AlunoExcluded");

            for (Aluno aluno: listaAlunos
            ) {
                if (aluno.getCpf().equals(excludcomparator.getCpf())){
                    listaAlunos.remove(aluno);
                    Toast toastexclud = Toast.makeText(getApplicationContext(),"Aluno Excluido !", Toast.LENGTH_SHORT);
                    toastexclud.show();
                }
            }

        }

        btnCadastrarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCadastroAluno = new Intent(getApplicationContext(), CadastrarAlunoActivity.class);
                toCadastroAluno.putExtra(CadastrarAlunoActivity.EXTRA_ALUNO, alunotocad);
                startActivityForResult(toCadastroAluno, REQUEST_STRING);
            }
        });

        btnPesquisarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPesquisarAluno = new Intent(getApplicationContext(), PesquisarAluno.class);
                Bundle toPesquisarbundle = new Bundle();
                toPesquisarbundle.putParcelableArrayList("ListaAlunos", listaAlunos);
                toPesquisarAluno.putExtras(toPesquisarbundle);
                startActivity(toPesquisarAluno);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_STRING){
            alunotocad = data.getParcelableExtra(CadastrarAlunoActivity.EXTRA_RESULTADO);
            listaAlunos.add(alunotocad);
            Toast toastcad = Toast.makeText(getApplicationContext(),"Aluno Cadastrado !", Toast.LENGTH_SHORT);
            toastcad.show();
        }
    }


}
