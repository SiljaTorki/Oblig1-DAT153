package com.example.oblig1;

import java.io.Serializable;

public class Cat implements Serializable {
    private String navn;
    private Integer bilde;


    public Cat(String navn, Integer bilde) {
        this.navn=navn;
        this.bilde=bilde;
    }

    public String getNavn() {
        return navn;
    }

    public Integer getBilde() {
        return bilde;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setBilde(Integer bilde) {
        this.bilde = bilde;
    }
}
