package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        EditText editText = (EditText) findViewById(R.id.editText1);
        int correct = 0;


        Button btn3 = (Button)findViewById(R.id.buttonSvar);       //The third button is created´"Sjekk svar"
        btn3.setOnClickListener(new View.OnClickListener() {    //The third buttons action is created
            public void onClick(View v) {
                if(editText != null){

                    CharSequence text = "Correct Answer!";
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                    //check if editText equals the text connected with the photo
                    //if it is correct, then a toast saying its correct, is going to appear
                    //if incorrect, the toast should say it is wrong
                    //correct++;
                }
            }
        });


        Button btn4 = (Button)findViewById(R.id.buttonNeste);       //The fourth button is created "Neste"
        btn4.setOnClickListener(new View.OnClickListener() {    //The fourth button´s action is created
            public void onClick(View v) {
                //Jumps to the next image
                // on the last image, it should provide a score
            }
        });
    }
}