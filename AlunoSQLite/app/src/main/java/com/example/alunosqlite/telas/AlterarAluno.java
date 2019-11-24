package com.example.alunosqlite.telas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alunosqlite.R;
import com.example.alunosqlite.daos.AlunoDAO;
import com.example.alunosqlite.model.Aluno;
import com.example.alunosqlite.util.ConexaoUtil;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class AlterarAluno extends AppCompatActivity {
    EditText cpf;
    EditText nome;
    EditText idade;
    Button alterar;
    Button deletar;
    Cursor cursor;
    AlunoDAO crud;
    String codigo;
    Aluno alunoSelecionado;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_aluno);
        codigo = this.getIntent().getStringExtra("codigo");

        crud = new AlunoDAO(getBaseContext());

        cpf = (EditText)findViewById(R.id.editText4);
        nome = (EditText)findViewById(R.id.editText5);
        idade = (EditText)findViewById(R.id.editText6);

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(cpf,smf);
        cpf.addTextChangedListener(mtw);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        alunoSelecionado = new Aluno(Integer.parseInt(codigo), cursor.getString(cursor.getColumnIndexOrThrow(ConexaoUtil.CPF)),cursor.getString(cursor.getColumnIndexOrThrow(ConexaoUtil.NOME)), cursor.getString(cursor.getColumnIndexOrThrow(ConexaoUtil.IDADE)));
        cpf.setText(alunoSelecionado.getCpf());
        nome.setText(alunoSelecionado.getNome());
        idade.setText(alunoSelecionado.getIdade());

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alunoSelecionado.setCpf(cpf.getText().toString());
                alunoSelecionado.setNome(nome.getText().toString());
                alunoSelecionado.setIdade(idade.getText().toString());
                crud.alteraRegistro(alunoSelecionado);
                Intent intent = new Intent(AlterarAluno.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cancelar = (Button)findViewById(R.id.button4);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AlterarAluno.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(alunoSelecionado);
                Intent intent = new Intent(AlterarAluno.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
