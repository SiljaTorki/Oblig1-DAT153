package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class Add extends AppCompatActivity {
    private ArrayList<Cat> catList;  // A list to store the cats photos in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //TODO: Liste av navn/bilder som er generert i tilfeldig rekkefølge (random)
        ArrayList<Cat> catList = (ArrayList<Cat>) getIntent().getSerializableExtra("liste");


    }
    //Method to add new cat-photos and names
    public void addCat(Cat cat) {
        catList.add(cat);
    }

    //TODO: Add Cat button:
    // Create a new Cat object with the Picture and name
    // Add the Cat to the database list

    //TODO: Choose picture button:
    // Use the built in feature
    // Add the picture to the ImageView
}