package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    private ArrayList<Cat> cats;
    TextView score;

    private int correct = 0;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        EditText editText = (EditText) findViewById(R.id.editText1);

        int counter = 0; //Counter for the quiz number, used to get correct image
        //TODO: Liste av navn/bilder som er generert i tilfeldig rekkefølge (random)

        cats = CatList.getCatList();

        //TODO: Update to first picture


        final int min = 0;
        final int max = cats.size();


        ImageView image = (ImageView) findViewById(R.id.imageView);

        image.setImageBitmap(cats.get(i).getBilde());


        Button btn3 = (Button)findViewById(R.id.buttonSvar);       //The button is created´"Sjekk svar"
        btn3.setOnClickListener(new View.OnClickListener() {    //The  buttons action is created
            public void onClick(View v) {
                score = findViewById(R.id.quizScore);
                if(editText != null){
                    //TODO: If som sjekker om svaret er riktig
                    String svar = editText.getText().toString();

                    CharSequence text;
                    if(cats.get(i).getNavn().equals(svar)){         //check if editText equals the text connected with the photo
                        text = "Correct Answer!";                   //if it is correct, then a toast saying its correct
                        correct++;
                    }else{
                        text = "Wrong Answer!";                      //if incorrect, the toast should say it is wrong
                    }

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    String quizScore = "Din score: " +correct;
                    score.setText(quizScore);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                }
            }
        });


        Button btn4 = (Button)findViewById(R.id.buttonNeste);       //The button is created "Neste"
        btn4.setOnClickListener(new View.OnClickListener() {    //The button´s action is created
            public void onClick(View v) {
                //Jumps to the next image but stops at the last one
                if (i < cats.size()-1) {
                    i++;

                    image.setImageBitmap(cats.get(i).getBilde()); //Finds a new image

                    editText.getText().clear();    //Empty the editText field


                }
            }
        });


    }
}