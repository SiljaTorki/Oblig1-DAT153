package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.helpers.BitMapHelp;
import com.example.oblig1.sqlLite.AppDatabase;

//import com.example.oblig1.domain.CatList;

public class MainActivity extends AppCompatActivity {

    //public CatList catList;

    public final static String DATABASE = "catDatabase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //If catList is empty, the three original cat-images should be added to the catList


        AppDatabase catDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                DATABASE).build();// TODO: Export this string to a constant


            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(catDatabase.catDao().getAll() == null) {
                        Cat cat1 = new Cat("Cat one", BitMapHelp.getBytes(BitmapFactory.decodeResource(getResources(), R.drawable.cat_one)));
                        Cat cat2 = new Cat("Cat two", BitMapHelp.getBytes(BitmapFactory.decodeResource(getResources(), R.drawable.cat_two)));
                        Cat cat3 = new Cat("Cat three", BitMapHelp.getBytes(BitmapFactory.decodeResource(getResources(), R.drawable.cat_three3)));

                        catDatabase.catDao().insert(cat1);
                        catDatabase.catDao().insert(cat2);
                        catDatabase.catDao().insert(cat3);
                    }
                }
            }).start();

       /* if (catList == null) {
            catList = new CatList();
            //The images are stored as Bitmaps in the list
            CatList.addCat("Cat one", (BitmapFactory.decodeResource(getResources(), R.drawable.cat_one)));
            CatList.addCat("Cat two", (BitmapFactory.decodeResource(getResources(), R.drawable.cat_two)));
            CatList.addCat("Cat three", (BitmapFactory.decodeResource(getResources(), R.drawable.cat_three3)));
        }*/


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