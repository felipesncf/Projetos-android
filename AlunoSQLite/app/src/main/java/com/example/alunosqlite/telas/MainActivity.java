package com.example.alunosqlite.telas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.alunosqlite.R;
import com.example.alunosqlite.daos.AlunoDAO;
import com.example.alunosqlite.util.ConexaoUtil;

public class MainActivity extends AppCompatActivity {
    private ListView lista;
    private Button inserir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlunoDAO crud = new AlunoDAO(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[]
                {ConexaoUtil.ID, ConexaoUtil.CPF};
        int[] idViews = new int[] {R.id.idAluno, R.id.nomeAluno};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.aluno_layout,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(ConexaoUtil.ID));
                Intent intent = new Intent(MainActivity.this, AlterarAluno.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

        inserir = (Button)findViewById(R.id.botaoinserir);
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InserirAluno.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
