package com.digitalhouse.firebaseapp.model;

import java.util.Date;

public class User {

    private Double peso;
    private Double altura;
    private Date date;

    public User(){}

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
