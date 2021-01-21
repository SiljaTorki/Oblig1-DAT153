package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        Button btn3 = (Button)findViewById(R.id.button3);       //The third button is created´"Sjekk svar"
        btn3.setOnClickListener(new View.OnClickListener() {    //The third buttons action is created
            public void onClick(View v) {
                // Perform action on click
            }
        });


        Button btn4 = (Button)findViewById(R.id.button4);       //The fourth button is created "Neste"
        btn4.setOnClickListener(new View.OnClickListener() {    //The fourth button´s action is created
            public void onClick(View v) {
                // Perform action on click
            }
        });
    }
}