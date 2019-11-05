package com.proudpet.ipet.classes;

import java.io.Serializable;

public class VacinaPronta implements Serializable {

    private int id;
    private String nome;
    private int validade;
    private int animal;  //1 -> cachorro 2 -> gato

    public VacinaPronta(String nome, int validade, int animal) {
        this.nome = nome;
        this.validade = validade;
        this.animal = animal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

    public int getAnimal() {
        return animal;
    }

    public void setAnimal(int animal) {
        this.animal = animal;
    }

    public String getStringValidade() {
        return getValidade() + "Ano"; //nescessario revisao
    }
}
