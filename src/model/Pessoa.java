package model;

import java.time.LocalDate;


public class Pessoa {


    protected String nome;

    protected LocalDate dataNascimento;

    public Pessoa(String nome,LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        this.nome = nome;
    }

}
