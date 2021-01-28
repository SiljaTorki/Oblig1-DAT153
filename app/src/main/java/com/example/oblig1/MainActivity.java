package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle extras = new Bundle();

        //TODO: A list to store the cats (photo and name)
        ArrayList<Cat> catList = new ArrayList<Cat>();

        //TODO: Put the 3 cats in the list (database)
        catList.add(new Cat("Cat one", R.drawable.cat_one));
        catList.add(new Cat("Cat two", R.drawable.cat_two));
        catList.add(new Cat("Cat three", R.drawable.cat_three3));

       // extras.putParcelableArrayList("liste", catList);
        //Send the list with the buttons

        Button btn1 = (Button)findViewById(R.id.buttonQuiz);       //The first button is created´"Ta quiz"
        btn1.setOnClickListener(new View.OnClickListener() {    //The first buttons action is created
            public void onClick(View v) {
                //this should send the user to the quiz-activity
                Intent intent = new Intent(MainActivity.this, Quiz.class);
                intent.putExtra("liste", catList);
                startActivity(intent);
            }
        });


        Button btn2 = (Button)findViewById(R.id.buttonBilde);       //The second button is created "Legg til bilde"
        btn2.setOnClickListener(new View.OnClickListener() {    //The second button´s action is created
            public void onClick(View v) {
                // this should make it possible for user to add images from the phone´s library
                Intent intent = new Intent(MainActivity.this, Add.class);
                intent.putExtra("list", catList);
                startActivity(intent);
            }
        });

        Button btn3 = (Button)findViewById(R.id.buttonDatabase);       //The third button is created "Legg til bilde"
        btn3.setOnClickListener(new View.OnClickListener() {    //The third button´s action is created
            public void onClick(View v) {
                // this should make it possible for user to add images from the phone´s library
                Intent intent = new Intent(MainActivity.this, Database.class);
                intent.putExtra("list", catList);
                startActivity(intent);
            }
        });


    }
}