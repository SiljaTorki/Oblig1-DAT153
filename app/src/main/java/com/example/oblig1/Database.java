package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends AppCompatActivity {

    private ArrayList<Cat> catList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        HashMap<Image,String> hm = new HashMap<Image,String>(); //Hashmap to find correct name for each image

        Button btn1 = (Button)findViewById(R.id.buttonToAdd);       //The first button is created´"Ta quiz"
        btn1.setOnClickListener(new View.OnClickListener() {    //The first buttons action is created
            public void onClick(View v) {
                //this should send the user to the quiz-activity
                Intent intent = new Intent(Database.this, Add.class);
                startActivity(intent);
            }
        });
    }

    public void addCat(Cat cat) {
        catList.add(cat);
    }
}