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
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {
    private Cat cat;

    private int correct = 0;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        EditText editText = (EditText) findViewById(R.id.editText1);

        int counter = 0; //Counter for the quiz number, used to get correct image
        //TODO: Liste av navn/bilder som er generert i tilfeldig rekkefølge (random)
        ArrayList<Cat> catList = (ArrayList<Cat>) getIntent().getSerializableExtra("liste");
        //TODO: Update to first picture

        // load a bitmap from the drawable folder
        final int min = 0;
        final int max = catList.size();



        Bitmap b = BitmapFactory.decodeResource(getResources(), catList.get(i).getBilde());

        // resize the bitmap to 150x100 (width x height)
        Bitmap scaled = Bitmap.createScaledBitmap(b, 150, 100, true);

        // loads the resized Bitmap into an ImageView
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageBitmap(scaled);


        Button btn3 = (Button)findViewById(R.id.buttonSvar);       //The third button is created´"Sjekk svar"
        btn3.setOnClickListener(new View.OnClickListener() {    //The third buttons action is created
            public void onClick(View v) {
                if(editText != null){
                    //TODO: If som sjekker om svaret er riktig
                    String svar = editText.getText().toString();

                    CharSequence text;
                    if(catList.get(i).getNavn().equals(svar)){
                        text = "Correct Answer!";
                        correct++;
                    }else{
                        text = "Wrong Answer!";
                    }

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();



                    //check if editText equals the text connected with the photo
                    //if it is correct, then a toast saying its correct, is going to appear
                    //if incorrect, the toast should say it is wrong

                }
            }
        });


        Button btn4 = (Button)findViewById(R.id.buttonNeste);       //The fourth button is created "Neste"
        btn4.setOnClickListener(new View.OnClickListener() {    //The fourth button´s action is created
            public void onClick(View v) {
                //Jumps to the next image
                if (i < catList.size()) {
                    i++;
                    Bitmap b = BitmapFactory.decodeResource(getResources(), catList.get(i).getBilde());

                    // resize the bitmap to 150x100 (width x height)
                    Bitmap scaled = Bitmap.createScaledBitmap(b, 150, 100, true);

                    // loads the resized Bitmap into an ImageView
                    ImageView image = (ImageView) findViewById(R.id.imageView);
                    image.setImageBitmap(scaled);
                    // on the last image, it should provide a score
                    //TODO: Update to next picture
                    //TODO: Empty the editText field
                    //TODO: if counter == size of list - Provide a score
                }
            }
        });
    }
}