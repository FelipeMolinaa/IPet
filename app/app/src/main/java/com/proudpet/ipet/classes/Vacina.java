package com.proudpet.ipet.classes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Vacina implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private int idAnimal = 0;
    private String nome;
    private String tipo;
    private String dataVacina;
    private String dataValidade;
    private String validade;
    private boolean obrigatorio;

    public Vacina(){
    }

    @Ignore
    public Vacina(int idAnimal, String nome, String tipo) {
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.tipo = tipo;
        this.obrigatorio = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataVacina() {
        return dataVacina;
    }

    public void setDataVacina(String dataVacina) {
        this.dataVacina = dataVacina;
    }

    public String getValidade() {
        try {
            setValidade(PegaDiasRestantes(getDataValidade()));
        }catch (Exception e){
            setValidade("0");
        }
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    private String PegaDiasRestantes(String dataFinal) {
        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
        Date data = new Date(System.currentTimeMillis());

        Date dateInicial = null;
        Date dateFinal = null;
        try
        {
            dateInicial = df.parse(df.format(data));
            dateFinal = df.parse(dataFinal);
        }
        catch (java.text.ParseException evt ) {}

        long dataTotal = (dateFinal.getTime() - dateInicial.getTime()) + 3600000;
        long dias = (dataTotal / 86400000L);
        return String.valueOf(dias);
    }

    public String getValidadeString() {
        int vacinaInt = Integer.parseInt(validade);
        if (vacinaInt >= 1) {
            return "Expira em " + vacinaInt + " dias";
        }
        else {
            return "Expirou a " + vacinaInt * (-1) + " dias";
        }
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public void renovaVacina(){
        Log.i("data1", getDataValidade() + " - " + getDataVacina());
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            cal.setTime(formatter.parse(String.valueOf(date)));
        }catch (Exception e){

        }
        int ano = cal.get(Calendar.YEAR);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH);

        String dataTotal = (dia + "/" + (mes + 1) + "/" + (ano + 1));
        Log.i("data1", dataTotal);
        setDataValidade(dataTotal);

        String data = null;
        try
        {
            data = formatter.format(date);
        }
        catch (Exception evt ) {}
        setDataVacina(data);
        Log.i("data1", getDataValidade() + " - " + getDataVacina());
    }
}