package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oblig1.helpers.DatabaseHelper;

/**
*  This class provides a visual of the three most important buttons of the application
*  It also sets up the database, making it ready for use
 */

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets up the database
        dbHelper = new DatabaseHelper(getApplicationContext());
        dbHelper.setUp();

        //The buttons are implemented
        Button btnQuiz = (Button) findViewById(R.id.buttonQuiz);
        Button btnAddImage = (Button) findViewById(R.id.buttonAddImageMain);
        Button btnDatabase = (Button) findViewById(R.id.buttonDatabase);


       //Redirects the user to the Quiz-page
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //this should send the user to the quiz-activity
                Intent intent = new Intent(MainActivity.this, Quiz.class);
                startActivity(intent);
            }
        });


        //Redirects the user to the Add-page
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // this should make it possible for user to add images from the phone´s library
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });

       //Redirects the user to the database-page
        btnDatabase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // this should make it possible for user to look at all the images added to the quiz
                Intent intent = new Intent(MainActivity.this, Database.class);
                startActivity(intent);
            }
        });
    }
}