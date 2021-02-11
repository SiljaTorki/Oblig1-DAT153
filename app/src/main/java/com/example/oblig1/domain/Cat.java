package com.example.oblig1.domain;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.oblig1.helpers.BitMapHelp;

import java.io.Serializable;

@Entity(tableName = "Cat")
public class Cat {

    //@NonNull
    //@PrimaryKey(autoGenerate = true)
    //public int id;
    @NonNull
    @PrimaryKey
    private String name;

    //TODO: finne en måte å kunne ha bitmap her, men uten at den skal komme seg inn i databasen
    //private Bitmap image;
    @ColumnInfo(name="image")
    private String image;

    //Images are usually stored as BLOB data in the database
//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
 //   private byte[] byteImage;


    public Cat(String name, String image) {
        this.name = name;
        //this.byteImage = byteImage;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    /*public byte[] getByteImage() {
        return byteImage;
    }*/

    public String getImage() {
        return image;
    }
}



