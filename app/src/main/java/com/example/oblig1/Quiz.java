package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.helpers.*;

import java.util.List;

public class Quiz extends AppCompatActivity {

    TextView score;
    TextView count;
    private List<Cat> cats;
    private QuizHelp quizh = new QuizHelp(); //Calling for the quiz-helping class
    private int counter = 1;
    private int i = 0; // Position in the list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Finding the user´s input
        EditText editText = (EditText) findViewById(R.id.editText1);

        //Getting the image
        ImageView image = (ImageView) findViewById(R.id.imageView);

        //Finding the "Sjekk svar" and "Neste" button i the view
        Button btnCheckAnswer = (Button)findViewById(R.id.buttonSvar);       //The button´"Sjekk svar"
        Button btnNext = (Button)findViewById(R.id.buttonNeste);            //The button "Neste"

        //Creates a randomlist with the cat images
        cats = quizh.randomList();
        int max = cats.size();

        // Tracking question
        count = findViewById(R.id.quizCounter);
        String quizCount = counter + "/" + max;
        count.setText(quizCount);

        //Provides a score at the top of the application
        score = findViewById(R.id.quizScore);
        String quizScore = "Din score: " + quizh.getCorrect();
        score.setText(quizScore);

        //Used for the toasts
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        String noImage = "No quiz available!";


        // Gets the first image from the list
        if(max > 0)
            image.setImageBitmap(cats.get(i).getBilde());
        else {
            //Removing all the visible elements on the screen if there are no images available
            btnCheckAnswer.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            image.setVisibility(View.GONE);
            count.setVisibility(View.GONE);
            score.setVisibility(View.GONE);

            Toast toast = Toast.makeText(context, noImage, duration);
            toast.show();

        }

        //Checking the answer of the user´s input, by using a lambda expression
        btnCheckAnswer.setOnClickListener((View v) -> {

           //A response to the user, correct/wrong answer
            CharSequence response = null;

            //if not empty, gets the text from editText field
            //and checking the user´s answer
            if(editText != null) {
                String answer = editText.getText().toString();
                response = quizh.checkAnswer(answer, cats.get(i).getNavn());
            }

                //Updating the score by calling getCorrect() from QuizHelper
                String quizScore1 = "Din score: " + quizh.getCorrect();
                score.setText(quizScore1);

                //Showing the toast
                Toast toast = Toast.makeText(context, response, duration);
                toast.show();

        });


        //The click-method goes to the next image, by using lambda expression
        btnNext.setOnClickListener((View v) -> {

            //Jumps to the next image but stops at the last one
            if (i < cats.size()-1) {
                i++;
                counter++;

                //Updates the counter
                String quizCount1 = counter + "/" + max;
                count.setText(quizCount1);

                //Finding the new image
                image.setImageBitmap(cats.get(i).getBilde());

                //Empty the editText field
                editText.getText().clear();
            }
        });
    }
}