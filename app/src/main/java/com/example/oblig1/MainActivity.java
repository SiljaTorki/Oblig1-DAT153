package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.helpers.BitMapHelp;
import com.example.oblig1.sqlLite.AppDatabase;

import java.util.List;

//import com.example.oblig1.domain.CatList;

public class MainActivity extends AppCompatActivity {

    final String DRAWABLE = "android.resource://com.example.oblig1/drawable/";

    public final static String DATABASE = "catDatabase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //If catList is empty, the three original cat-images should be added to the catList

        AppDatabase catDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                DATABASE).build();

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        List<Cat> cats = catDatabase.catDao().getAll();
                        if(cats.size() == 0) {
                            System.out.println("KITTENS ADDED");
                            System.out.println();
                            System.out.println();

                            Cat cat1 = new Cat("Cat one", DRAWABLE + "cat_one");
                            Cat cat2 = new Cat("Cat two", DRAWABLE + "cat_two");
                            Cat cat3 = new Cat("Cat three", DRAWABLE + "cat_three3");

                            /* resize the bitmap to 150x100 (width x height)
                            Bitmap scaled1 = Bitmap.createScaledBitmap(b1, 150, 100, true);
                            Bitmap scaled2 = Bitmap.createScaledBitmap(b2, 150, 100, true);
                            Bitmap scaled3 = Bitmap.createScaledBitmap(b3, 150, 100, true);
                             */

                            catDatabase.catDao().insert(cat1);
                            catDatabase.catDao().insert(cat2);
                            catDatabase.catDao().insert(cat3);
                        }
                    } finally {
                        catDatabase.close();
                    }
                }
            }).start();

        Button btn1 = (Button) findViewById(R.id.buttonQuiz);       //The first button is created´"Ta quiz"
        btn1.setOnClickListener(new View.OnClickListener() {       //The first buttons action is created
            public void onClick(View v) {
                //this should send the user to the quiz-activity
                Intent intent = new Intent(MainActivity.this, Quiz.class);
                startActivity(intent);
            }
        });


        Button btn2 = (Button) findViewById(R.id.buttonBilde);       //The second button is created "Legg til bilde"
        btn2.setOnClickListener(new View.OnClickListener() {    //The second button´s action is created
            public void onClick(View v) {
                // this should make it possible for user to add images from the phone´s library
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });

        Button btn3 = (Button) findViewById(R.id.buttonDatabase);       //The third button is created "Legg til bilde"
        btn3.setOnClickListener(new View.OnClickListener() {    //The third button´s action is created
            public void onClick(View v) {
                // this should make it possible for user to look at all the images added to the quiz
                Intent intent = new Intent(MainActivity.this, Database.class);
                startActivity(intent);
            }
        });
    }
}