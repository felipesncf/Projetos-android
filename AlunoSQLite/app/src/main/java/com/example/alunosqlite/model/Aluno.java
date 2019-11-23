package com.example.alunosqlite.model;

public class Aluno {
    int id;
    String cpf;
    String nome;
    String idade;

    public Aluno(int id, String cpf, String nome, String idade){
        this.id=id;
        this.cpf=cpf;
        this.nome=nome;
        this.idade=idade;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome=nome;
    }

    public String getIdade(){
        return idade;
    }
    public void setIdade(String idade){
        this.idade=idade;
    }

}
