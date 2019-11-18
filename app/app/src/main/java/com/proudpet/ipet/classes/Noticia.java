package com.proudpet.ipet.classes;

import java.io.Serializable;

public class Noticia implements Serializable {



    private int id;
    private String dia;
    private String mes;
    private String ano;
    private String fonte;
    private String titulo;



    private String link;

    public Noticia(String dia, String mes, String ano, String titulo, String link, String fonte) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.link = link;
        this.titulo = titulo;
        this.fonte = fonte;
    }

    private String materia2;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMateria2() {
        return materia2;
    }

    public void setMateria2(String materia2) {
        this.materia2 = materia2;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}