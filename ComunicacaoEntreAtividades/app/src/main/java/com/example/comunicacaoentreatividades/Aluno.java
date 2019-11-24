package com.example.comunicacaoentreatividades;

import android.os.Parcel;
import android.os.Parcelable;

public class Aluno implements Parcelable {
    private String cpf;
    private String nome;
    private int idade;
    private  String email;

    public Aluno(){

    }

    public Aluno(String cpf, String nome, int idade, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    protected Aluno(Parcel in) {
        cpf = in.readString();
        nome = in.readString();
        idade = in.readInt();
        email = in.readString();
    }

    public static final Creator<Aluno> CREATOR = new Creator<Aluno>() {
        @Override
        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        @Override
        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };

    public String toString(){
        return this.getCpf();
    }

    // Getters and Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cpf);
        dest.writeString(nome);
        dest.writeInt(idade);
        dest.writeString(email);
    }
}
