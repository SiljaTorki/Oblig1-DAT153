package com.example.oblig1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.helpers.*;

import java.util.List;

/**
* The class is used to show the cat-images, and to update the score if the user guess correctly
*
 */

public class Quiz extends AppCompatActivity {

    TextView score;
    TextView count;

    private List<Cat> cats;
    private static String catName;  //Used for testing only
    private QuizHelp quizh = new QuizHelp(); //Calling for the quiz-helping class

    private int counter = 1;
    private int i = 0; // Position in the list
    private int max;

    private boolean empty = false;

    private Button btnCheckAnswer;
    private Button btnNext;

    private ImageView image;
    private EditText editText;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //gets access to the database
        dbHelper = new DatabaseHelper(getApplicationContext());

        theToolbar();

        getFromDB();

        scoreAndCount();

        //Finding the user´s input
        editText = (EditText) findViewById(R.id.editTextGuess);

        //Getting the ImageView
        image = (ImageView) findViewById(R.id.imageViewQuiz);

        //Implementing the buttons
        btnCheckAnswer = (Button)findViewById(R.id.buttonCheckAnswer);
        btnNext = (Button)findViewById(R.id.buttonNext);

        //Setting the first image
        image.setImageURI(Uri.parse(cats.get(i).getImage()));


        /*
        * Checking the answer of the user´s input, by using a lambda expression
        * using the method answerAndResponse()
         */
        btnCheckAnswer.setOnClickListener((View v) -> {
            answerAndResponse();
        });

        /*
        * Getting the next image in the list
         * using the method updateQuiz()
         */
        btnNext.setOnClickListener((View v) -> {
            updateQuiz();

        });

    }

    /*
    * Setting the score and count the first time
    * Everything except max is set to zero
     */
    private void scoreAndCount(){
        // Tracking question
        count = findViewById(R.id.quizCounter);
        String quizCount = counter + "/" + max;

        //Provides a score at the top of the application
        score = findViewById(R.id.quizScore);
        String quizScore = "Your score: " + quizh.getCorrect();

        count.setText(quizCount);
        score.setText(quizScore);
    }


    /*
     * Gets the catList from the database with help from DatabaseHelper.java
     * Generates a random list of cats with help from QuizHelp.java
     */
    private void getFromDB(){
        //Getting the list of cats from the database
        cats = dbHelper.getAllCats();
        cats = quizh.randomList(cats);
        max = cats.size();

        //Only used for testing
        catName = cats.get(0).getName();
    }


    //Make it possible for the user to get back to the MainActivity.class
    private void theToolbar(){
        // my_toolbar_quiz is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar_quiz);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

    }


    //Finds and show the next image in the list
    private void updateQuiz(){
        //Jumps to the next image but stops at the last one
        if (i < cats.size()-1) {
            i++;
            counter++;

            //Updates the counter
            String quizCount1 = counter + "/" + max;
            count.setText(quizCount1);

            //Finding the new image
            image.setImageURI(Uri.parse(cats.get(i).getImage()));

            //Empty the editText field
            editText.getText().clear();
        }
    }


    /*
    * Checking the users answer, with help from QuizHelp.java
    * Updates the score if correct guess
    * Also provides a response in form of a Toast
    */
    private void answerAndResponse(){
        CharSequence response = null;
        /*
         * if not empty, gets the text from editText field
         * and checking the user´s answer
         */
        if(editText != null) {
            String answer = editText.getText().toString();
            response = quizh.checkAnswer(answer, cats.get(i).getName());
        }

        //Updating the score by calling getCorrect() from QuizHelper
        String quizScore1 = "Your score: " + quizh.getCorrect();
        score.setText(quizScore1);

        //Showing the toast
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, response, duration);
        toast.show();

    }


    //Only used for testing
    public static String getCatName() {
        return catName;
    }

}


