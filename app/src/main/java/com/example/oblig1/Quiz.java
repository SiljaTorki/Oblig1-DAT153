package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    private ArrayList<Cat> original;
    private ArrayList<Cat> cats;
    TextView score;
    TextView count;

    private int correct = 0;
    private int counter = 1;
    private int i = 0; // Position in the list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        EditText editText = (EditText) findViewById(R.id.editText1);

        original = CatList.getCatList(); // Get the full list from data structure
        int max = original.size();

        int tall;
        cats = new ArrayList<Cat>(original.size());
        //Loop that generates a random list
        for(int j = 0; j < original.size(); j++) {
            tall = (int) (Math.random()*original.size());
            if(!cats.contains(original.get(tall))) //Check if it already exist
                cats.add(j, original.get(tall)); //Add to the list
            else
                j--; //To secure that you get all cats transferred to the new list
        }

        // Tracking question
        count = findViewById(R.id.quizCounter);
        String quizCount = counter + "/" + max;
        count.setText(quizCount);

        //Provides a score at the top of the application
        score = findViewById(R.id.quizScore);
        String quizScore = "Din score: " +correct;
        score.setText(quizScore);


        ImageView image = (ImageView) findViewById(R.id.imageView);
        if(max > 0)
            image.setImageBitmap(cats.get(i).getBilde()); // Gets the first image from the list

        Button btn3 = (Button)findViewById(R.id.buttonSvar);       //The button is created´"Sjekk svar"
        btn3.setOnClickListener(new View.OnClickListener() {       //The  buttons action is created
            public void onClick(View v) {

                if(editText != null){
                    String svar = editText.getText().toString(); //gets the text from editText field

                    CharSequence text;
                    if(cats.get(i).getNavn().equals(svar)){         //check if editText equals the text connected with the photo
                        text = "Rett svar!";                        //if it is correct, then a toast saying its correct
                        correct++;
                    }else{
                        text = "Feil svar!";                        //if incorrect, the toast should say it is wrong
                    }

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    String quizScore = "Din score: " +correct;       //Updating the score
                    score.setText(quizScore);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });


        Button btn4 = (Button)findViewById(R.id.buttonNeste);   //The button is created "Neste"
        btn4.setOnClickListener(new View.OnClickListener() {    //The button´s action is created
            public void onClick(View v) {

                if (i < cats.size()-1) {                        //Jumps to the next image but stops at the last one
                    i++;
                    counter++;
                    String quizCount = counter + "/" + max;     //Updates the counter
                    count.setText(quizCount);

                    image.setImageBitmap(cats.get(i).getBilde());   //Finds a new image

                    editText.getText().clear();                     //Empty the editText field
                }
            }
        });
    }
}