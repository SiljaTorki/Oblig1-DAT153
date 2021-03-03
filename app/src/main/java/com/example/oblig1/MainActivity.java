package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.viewModels.ViewModelDatabase;

import java.util.List;

/**
*  This class provides a visual of the three most important buttons of the application
*  It also sets up the database, making it ready for use
 */

public class MainActivity extends AppCompatActivity {

    private ViewModelDatabase vmd;
    final String DRAWABLE = "android.resource://com.example.oblig1/drawable/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll() // <=====
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());
*/
        //Sets up the database if its empty
        setup();

        //The buttons are implemented
        Button btnQuiz = (Button) findViewById(R.id.mainButtonQuiz);
        Button btnAddImage = (Button) findViewById(R.id.mainButtonAdd);
        Button btnDatabase = (Button) findViewById(R.id.mainButtonDatabase);


       //Redirects the user to the QuizActivity-page
        btnQuiz.setOnClickListener((View v)->{
                //this should send the user to the quiz-activity
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
        });


        //Redirects the user to the AddActivity-page
        btnAddImage.setOnClickListener((View v) ->{
                // this should make it possible for user to add images from the phone´s library
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

        });

       //Redirects the user to the database-page
        btnDatabase.setOnClickListener((View v) -> {
                // this should make it possible for user to look at all the images added to the quiz
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
        });


    }
    // Checks if the database is empty, and adding the three main cats
    public void setup(){
    vmd = new ViewModelDatabase(getApplication());
        vmd.getAllLive().observe(this,(List<Cat> obs) -> {
        if(obs.size()==0){
            vmd.insert(new Cat("Cat one", DRAWABLE + "cat_one"));
            vmd.insert(new Cat("Cat two", DRAWABLE + "cat_two"));
            vmd.insert(new Cat("Cat three", DRAWABLE + "cat_three3"));

        }
    });}
}