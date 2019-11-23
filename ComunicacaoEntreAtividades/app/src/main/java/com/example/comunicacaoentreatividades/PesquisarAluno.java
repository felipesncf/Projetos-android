package com.example.comunicacaoentreatividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PesquisarAluno extends AppCompatActivity {
    List<Aluno> listaPesquisa;
    List<String> listaString;
    List<Aluno> tooperator;
    TextView tvPesqNome;
    TextView tvPesqCPF;
    TextView tvPesqEmail;
    TextView tvPesqIdade;
    Button btnTrazerdados;
    Button btnEditar;
    Button btnExcluir;
    Button btnEditarSave;


    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_aluno);
        tooperator = new ArrayList<Aluno>();

        btnEditar = (Button) findViewById(R.id.btnEditarAlunoPesquisa);
        btnEditar.setVisibility(View.INVISIBLE);
        btnExcluir = (Button) findViewById(R.id.btnExcluirAlunoPesquisa);
        btnExcluir.setVisibility(View.INVISIBLE);
        btnEditarSave = (Button) findViewById(R.id.BtnSaveEditPesquisar);
        btnEditarSave.setVisibility(View.INVISIBLE);
        btnTrazerdados = (Button) findViewById(R.id.btnRealizarPesquisa);
        tvPesqCPF = (TextView) findViewById(R.id.tvPesqCPF);
        tvPesqCPF.setVisibility(View.INVISIBLE);
        tvPesqEmail = (TextView) findViewById(R.id.tvPesqEmail);
        tvPesqEmail.setVisibility(View.INVISIBLE);
        tvPesqIdade = (TextView) findViewById(R.id.tvPesqIdade);
        tvPesqIdade.setVisibility(View.INVISIBLE);
        tvPesqNome = (TextView) findViewById(R.id.tvPesqNome);
        tvPesqNome.setVisibility(View.INVISIBLE);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.atctv);
        

        final Bundle[] extras = {getIntent().getExtras()};
        final ArrayList<Aluno> ListaRecebida = extras[0].getParcelableArrayList("ListaAlunos");
        listaString = new ArrayList<>();

        for (Aluno aluno: ListaRecebida) {
            listaString.add(aluno.getCpf());
        }

        PesquisarAdapter adapter = new PesquisarAdapter(this, android.R.layout.simple_dropdown_item_1line, listaString);
        autoCompleteTextView.setAdapter(adapter);


        btnTrazerdados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String whoSelected = autoCompleteTextView.getText().toString();
                Aluno Editar = new Aluno();
                for (Aluno aluno: ListaRecebida
                ) {
                    if(whoSelected.equals(aluno.getCpf())){
                        Editar = aluno;
                    }
                }
                if (Editar != null){
                    btnEditar.setVisibility(View.VISIBLE);
                    btnExcluir.setVisibility(View.VISIBLE);
                    tooperator.add(Editar);
                }else{
                    String textotoast = "Aluno Inválido.";
                    Toast toast = Toast.makeText(getApplicationContext(), textotoast, Toast.LENGTH_SHORT);
                }
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPesqNome.setVisibility(View.VISIBLE);
                tvPesqEmail.setVisibility(View.VISIBLE);
                tvPesqIdade.setVisibility(View.VISIBLE);
                btnEditarSave.setVisibility(View.VISIBLE);

                tvPesqEmail.setText("Teste");
                tvPesqNome.setText(tooperator.get(tooperator.size()-1).getNome().toString());
                String tratamento = Integer.toString(tooperator.get(tooperator.size()-1).getIdade());
                tvPesqIdade.setText(tratamento);
                tvPesqEmail.setText(tooperator.get(tooperator.size()-1).getEmail().toString());
            }
        });

        btnEditarSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno alunoedittosend = new Aluno(tooperator.get(tooperator.size()-1).getCpf(), tvPesqNome.getText().toString(),
                        Integer.parseInt(tvPesqIdade.getText().toString()), tvPesqEmail.getText().toString());
                Intent ToMainEditedAluno = new Intent(getApplicationContext(), MainActivity.class);
                ToMainEditedAluno.putExtra("AlunoEdited", alunoedittosend);
                startActivity(ToMainEditedAluno);
            }
        });
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToMainExcludedAluno = new Intent(getApplicationContext(), MainActivity.class);
                ToMainExcludedAluno.putExtra("AlunoExcluded", tooperator.get(tooperator.size()-1));
                startActivity(ToMainExcludedAluno);
            }
        });
    }
}
