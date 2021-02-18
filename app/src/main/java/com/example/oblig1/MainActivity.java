package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oblig1.helpers.DatabaseHelper;

//import com.example.oblig1.domain.CatList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new DatabaseHelper(getApplicationContext());
        dbHelper.setUp();

        Button btn1 = (Button) findViewById(R.id.buttonQuiz);       //The first button is created´"Ta quiz"
        btn1.setOnClickListener(new View.OnClickListener() {        //The first buttons action is created
            public void onClick(View v) {
                //this should send the user to the quiz-activity
                Intent intent = new Intent(MainActivity.this, Quiz.class);
                startActivity(intent);
            }
        });


        Button btn2 = (Button) findViewById(R.id.buttonAddImageMain);       //The second button is created "Add Image"
        btn2.setOnClickListener(new View.OnClickListener() {         //The second button´s action is created
            public void onClick(View v) {
                // this should make it possible for user to add images from the phone´s library
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });

        Button btn3 = (Button) findViewById(R.id.buttonDatabase);       //The third button is created "Database"
        btn3.setOnClickListener(new View.OnClickListener() {            //The third button´s action is created
            public void onClick(View v) {
                // this should make it possible for user to look at all the images added to the quiz
                Intent intent = new Intent(MainActivity.this, Database.class);
                startActivity(intent);
            }
        });
    }
}