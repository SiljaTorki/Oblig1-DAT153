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
import com.example.oblig1.sqlLite.AppDatabase;
import com.example.oblig1.sqlLite.DatabaseClient;
import com.example.oblig1.viewModels.ViewModelDatabase;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
* The class is used to show the cat-images, and to update the score if the user guess correctly
* We have now progressed from using multiple new Threads, to use LivData
 */

public class QuizActivity extends AppCompatActivity {

    TextView score;
    TextView count;

    private static String catName;  //Used for testing only
    private QuizHelp quizh = new QuizHelp(); //Calling for the quiz-helping class

    private int counter = 1;
    private int max;

    private Button btnCheckAnswer;
    private Button btnNext;

    private ImageView image;
    private EditText editText;

    private Iterator<Cat> catIterator;
    private ViewModelDatabase vmd;
    private Cat cat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        theToolbar();

        //Finding the user´s input
        editText = (EditText) findViewById(R.id.editTextGuess);

        //Getting the ImageView
        image = (ImageView) findViewById(R.id.imageViewQuiz);

        //Implementing the buttons
        btnCheckAnswer = (Button)findViewById(R.id.buttonCheckAnswer);
        btnNext = (Button)findViewById(R.id.buttonNext);

        //Unable the buttons until the quiz is ready
        btnCheckAnswer.setEnabled(false);
        btnNext.setEnabled(false);

        vmd = new ViewModelDatabase(getApplication());
        vmd.getAllLive().observe(this, (List<Cat> obs) -> {
            Collections.shuffle(obs);
            catIterator = obs.iterator();
            // Good thing we'll be doing this on the UI thread
            findCat();
            // Now we're ready:
            max = obs.size();
            scoreAndCount();
            btnCheckAnswer.setEnabled(true);
            btnNext.setEnabled(true);
        });


        /*
        * Getting the next image in the list
         * using the method updateQuiz()
         */
        btnNext.setOnClickListener((View v) -> {
            findCat();
            updateQuiz();
        });
    }
    /*
    Finding the new cat to be shown in the quiz
     */
    private void findCat(){
        if( catIterator.hasNext()){
            cat = catIterator.next();
            image.setImageURI(Uri.parse(cat.getImage()));

            //Only for testing
            catName = cat.getName();
        }
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

        if(counter < max){
            counter++;

            //Updates the counter
            String quizCount1 = counter + "/" + max;
            count.setText(quizCount1);

            //Finding the new image
            image.setImageURI(Uri.parse(cat.getImage()));

            //Empty the editText field
            editText.getText().clear();
        }
    }

    /*
    * Checking the users answer, with help from QuizHelp.java
    * Updates the score if correct guess
    * Also provides a response in form of a Toast
    */
    public void answerAndResponse(View v){
        CharSequence response = null;
        /*
         * if not empty, gets the text from editText field
         * and checking the user´s answer
         */
        if(editText != null) {
            String answer = editText.getText().toString();
            response = quizh.checkAnswer(answer, cat.getName());
        }

        //Updating the score by calling getCorrect() from QuizHelper
        String quizScore1 = "Your score: " + quizh.getCorrect();
        score.setText(quizScore1);

        //Showing the toast
      //  Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, response, duration);
        toast.show();
    }

    //Only used for testing
    public static String getCatName() {
        return catName;
    }
}


