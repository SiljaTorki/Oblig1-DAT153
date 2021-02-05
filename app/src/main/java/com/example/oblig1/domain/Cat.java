package com.example.oblig1.domain;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

//@Entity
public class Cat {
   // @ColumnInfo(name = "name")
    private String name;

    //@ColumnInfo(name = "image")
    private Bitmap image;


    public Cat(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    public String getNavn() {
        return name;
    }

    public Bitmap getBilde() {
        return image;
    }
}
