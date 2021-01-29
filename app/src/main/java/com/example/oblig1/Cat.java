package com.example.oblig1;

import android.net.Uri;

import java.io.Serializable;

public class Cat {
    private String navn;
    private Uri bilde;


    public Cat(String navn, Uri bilde) {
        this.navn=navn;
        this.bilde=bilde;
    }

    public String getNavn() {
        return navn;
    }

    public Uri getBilde() {
        return bilde;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setBilde(Uri bilde) {
        this.bilde = bilde;
    }
}
