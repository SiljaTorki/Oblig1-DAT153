package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.hjelpeklasse.*;

import java.util.List;

public class Quiz extends AppCompatActivity {

    private List<Cat> cats;
    private QuizHelp quizh = new QuizHelp(); //Calling for the quiz-helping class
    TextView score;
    TextView count;

    private int counter = 1;
    private int i = 0; // Position in the list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        EditText editText = (EditText) findViewById(R.id.editText1);

        cats = quizh.randomList();          //Creates a randomlist with the cat images
        int max = cats.size();

        // Tracking question
        count = findViewById(R.id.quizCounter);
        String quizCount = counter + "/" + max;
        count.setText(quizCount);

        //Provides a score at the top of the application
        score = findViewById(R.id.quizScore);
        String quizScore = "Din score: " +quizh.getCorrect();
        score.setText(quizScore);

        ImageView image = (ImageView) findViewById(R.id.imageView);

        if(max > 0)
            image.setImageBitmap(cats.get(i).getBilde());       // Gets the first image from the list


        Button btnCheckAnswer = (Button)findViewById(R.id.buttonSvar);       //The button is created´"Sjekk svar"

        //Edited to use a lambda expression
        btnCheckAnswer.setOnClickListener(v -> {
            CharSequence response = null;

            if(editText != null) {
                String answer = editText.getText().toString();                  //gets the text from editText field
                response = quizh.checkAnswer(answer, cats.get(i).getNavn());    // checking the user´s answer to
            }

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                String quizScore1 = "Din score: " + quizh.getCorrect();       //Updating the score by calling getCorrect() from QuizHelper
                score.setText(quizScore1);
                Toast toast = Toast.makeText(context, response, duration);
                toast.show();

        });


        Button btnNext = (Button)findViewById(R.id.buttonNeste);    //The button is created "Neste"
        //Edited to use lambda expression
        btnNext.setOnClickListener(v -> {

            if (i < cats.size()-1) {         //Jumps to the next image but stops at the last one
                i++;
                counter++;
                String quizCount1 = counter + "/" + max;     //Updates the counter
                count.setText(quizCount1);

                image.setImageBitmap(cats.get(i).getBilde());       //Finds a new image

                editText.getText().clear();          //Empty the editText field
            }
        });
    }
}