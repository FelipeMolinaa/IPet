package com.proudpet.ipet.classes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

@Entity
public class Animal implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String dataNascimento;
    private String peso;
    private int especie;
    private int Sexo;
    private boolean Castrado;

    public Animal(){
    }

    public void adicionaVacina(List<Vacina> vacinas){
        for (Vacina a: vacinas) {
            vacinas.add(a);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecie(int especie) {
        this.especie = especie;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido(){
        return id > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getEspecie() {
        return especie;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPeso() {
        return peso;
    }

    public String getStringPeso() {
        return peso + "Kg";
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public int getSexo() {
        return Sexo;
    }

    public void setSexo(int sexo) {
        Sexo = sexo;
    }

    public boolean isCastrado() {
        return Castrado;
    }

    public void setCastrado(boolean castrado) {
        Castrado = castrado;
    }

    public String getStringCastrado() {
        if (this.isCastrado()){
            return "Castrado";
        }
        return "";
    }

    public String getPesoString() {
        return getPeso() + "Kg";
    }

    public String getStringSexo() {
        if (getSexo() == 1){
            return "fêmea";
        }else{
            return "Macho";
        }
    }
}