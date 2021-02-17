package com.example.oblig1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

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

import java.util.List;


public class Quiz extends AppCompatActivity {

    TextView score;
    TextView count;

    private List<Cat> cats;
    private static String catName;
    private QuizHelp quizh = new QuizHelp(); //Calling for the quiz-helping class

    private int counter = 1;
    private int i = 0; // Position in the list
    private int max;

    private boolean empty = false;

    private Button btnCheckAnswer;
    private Button btnNext;
    private ImageView image;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
/*
        DatabaseClient clientDB = DatabaseClient.getInstance(getApplicationContext());
        AppDatabase catDatabase= clientDB.getAppDatabase();
*/
        dbHelper = new DatabaseHelper(getApplicationContext());
        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        //Finding the user´s input
        EditText editText = (EditText) findViewById(R.id.editText1);

        //Getting the image
        image = (ImageView) findViewById(R.id.imageView);

        //Finding the "Sjekk svar" and "Neste" button i the view
        btnCheckAnswer = (Button)findViewById(R.id.buttonSvar);       //The button´"Sjekk svar"
        btnNext = (Button)findViewById(R.id.buttonNeste);            //The button "Neste"

        //Getting the list of cats from the database
        cats = dbHelper.getAllCats();
        cats = quizh.randomList(cats);
        max = cats.size();
        catName = cats.get(0).getName();

        // Tracking question
        count = findViewById(R.id.quizCounter);
        String quizCount = counter + "/" + max;


        //Provides a score at the top of the application
        score = findViewById(R.id.quizScore);
        String quizScore = "Your score: " + quizh.getCorrect();

        //Used for the toasts
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        String noImage = "No quiz available!";

        count.setText(quizCount);
        score.setText(quizScore);

        showOrHide();

        //Checking the answer of the user´s input, by using a lambda expression
        btnCheckAnswer.setOnClickListener((View v) -> {

           //A response to the user, correct/wrong answer
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
                image.setImageURI(Uri.parse(cats.get(i).getImage()));


                //Empty the editText field
                editText.getText().clear();
            }
        });

    }

    public void showOrHide(){
        // Gets the first image from the list
        if(max > 0)

            image.setImageURI(Uri.parse(cats.get(i).getImage()));

    }
    public static String getCatName() {
        return catName;
    }

}


