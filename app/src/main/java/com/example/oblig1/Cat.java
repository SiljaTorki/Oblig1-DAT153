package com.example.oblig1;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class Cat {
    private String navn;
    private Bitmap bilde;


    public Cat(String navn, Bitmap bilde) {
        this.navn=navn;
        this.bilde=bilde;
    }

    public String getNavn() {
        return navn;
    }

    public Bitmap getBilde() {
        return bilde;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setBilde(Bitmap bilde) {
        this.bilde = bilde;
    }
}
