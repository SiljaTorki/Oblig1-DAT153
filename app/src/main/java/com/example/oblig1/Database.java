package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;

import java.util.HashMap;

public class Database extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        HashMap<Image,String> hm = new HashMap<Image,String>(); //Hashmap to find correct name for each image

    }
}