package com.example.alunosqlite.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alunosqlite.model.Aluno;
import com.example.alunosqlite.util.ConexaoUtil;

public class AlunoDAO {
    private SQLiteDatabase db;
    private ConexaoUtil conexao;

    public AlunoDAO(Context context){
        conexao = new ConexaoUtil(context);
    }

    public String insereDado(Aluno aluno){
        ContentValues valores;
        long resultado;

        db = conexao.getWritableDatabase();
        valores = new ContentValues();
        valores.put(ConexaoUtil.CPF, aluno.getCpf());
        valores.put(ConexaoUtil.NOME, aluno.getNome());
        valores.put(ConexaoUtil.IDADE, aluno.getIdade());

        // nullcolumnhack identificar coluna que aceite nulo
        resultado = db.insert(ConexaoUtil.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir aluno";
        else
            return "Aluno Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {conexao.ID,conexao.CPF};
        db = conexao.getReadableDatabase();
        cursor = db.query(conexao.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {conexao.ID,conexao.CPF,conexao.NOME,conexao.IDADE};
        String where = ConexaoUtil.ID + "=" + id;
        db = conexao.getReadableDatabase();
        cursor = db.query(ConexaoUtil.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(Aluno aluno){
        ContentValues valores;
        String where;

        db = conexao.getWritableDatabase();

        where = ConexaoUtil.ID + "=" + aluno.getId();

        valores = new ContentValues();
        valores.put(ConexaoUtil.CPF, aluno.getCpf());
        valores.put(ConexaoUtil.NOME, aluno.getNome());
        valores.put(ConexaoUtil.IDADE, aluno.getIdade());

        db.update(ConexaoUtil.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(Aluno aluno){
        String where = ConexaoUtil.ID + "=" + aluno.getId();
        db = conexao.getReadableDatabase();
        db.delete(ConexaoUtil.TABELA,where,null);
        db.close();
    }
}
