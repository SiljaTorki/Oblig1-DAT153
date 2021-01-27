package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends AppCompatActivity {

    HashMap<Integer,String> hm = new HashMap<Integer,String>(); //Hashmap to find correct name for each image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);


      //  ArrayList<Person> personListe = new ArrayList<Person>();
       // Person p1 = new Person("Cat one", R.drawable.cat_one);

        Button btn1 = (Button)findViewById(R.id.buttonToAdd);       //The first button is created´"Ta quiz"
        btn1.setOnClickListener(new View.OnClickListener() {    //The first buttons action is created
            public void onClick(View v) {
                //this should send the user to the quiz-activity
                Intent intent = new Intent(Database.this, Add.class);
                startActivity(intent);
            }
        });
    }

    public void hashMapSetUp(){
        hm.put(R.drawable.cat_one,"cat one");
        hm.put(R.drawable.cat_two, "cat two");
        hm.put(R.drawable.cat_three3, "cat three");
    }

    public void hashMapAdd(Integer bilde, String txt){
        hm.put(bilde,txt);
    }


}