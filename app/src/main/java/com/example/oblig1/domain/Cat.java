package com.example.oblig1.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
*   This class is used in other classes to create new Cat-elements
*   It is also connected to the table in the database (ROOM)
*   Make it possible to get both cat-name and -image
 */

@Entity(tableName = "Cat")
public class Cat {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="catName")
    private String name;

    @ColumnInfo(name="image")
    private String image;

    public Cat(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }


    public String getImage() {
        return image;
    }
}



