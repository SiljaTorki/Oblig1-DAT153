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
                DATABASE).build();


            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        List<Cat> kittens = catDatabase.catDao().getAll();
                        if(kittens.size() == 0) {
                            System.out.println("KITTENS ADDED");
                            System.out.println();
                            System.out.println();

                            Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.cat_one);
                            Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.cat_two);
                            Bitmap b3 = BitmapFactory.decodeResource(getResources(), R.drawable.cat_three3);


                            Uri uri1 = Uri.parse("android.resource://com.example.oblig1/drawable/cat_one.png");
                            Uri uri2 = Uri.parse("android.resource://com.example.oblig1/drawable/cat_two.png");
                            Uri uri3 = Uri.parse("android.resource://com.example.oblig1/drawable/cat_three3.png");

                            Cat cat1 = new Cat("Cat one", uri1.toString());
                            Cat cat2 = new Cat("Cat two", uri2.toString());
                            Cat cat3 = new Cat("Cat three", uri3.toString());


                            /* resize the bitmap to 150x100 (width x height)
                            Bitmap scaled1 = Bitmap.createScaledBitmap(b1, 150, 100, true);
                            Bitmap scaled2 = Bitmap.createScaledBitmap(b2, 150, 100, true);
                            Bitmap scaled3 = Bitmap.createScaledBitmap(b3, 150, 100, true);
                             */

                           // Cat cat1 = new Cat("Cat one", BitMapHelp.getBytes(scaled1));
                            // Cat cat2 = new Cat("Cat one", BitMapHelp.getBytes(scaled2));
                            //Cat cat3 = new Cat("Cat one", BitMapHelp.getBytes(scaled3));

                           // Cat cat2 = new Cat("Cat two", BitMapHelp.getBytes(BitmapFactory.decodeResource(getResources(), R.drawable.cat_two)));
                            //Cat cat3 = new Cat("Cat three", BitMapHelp.getBytes(BitmapFactory.decodeResource(getResources(), R.drawable.cat_three3)));


                            //Cat cat3 = new Cat("Cat three", BitMapHelp.getBytes(BitmapFactory.decodeResource(getResources(), R.drawable.cat_three3)));


                            catDatabase.catDao().insert(cat1);
                            catDatabase.catDao().insert(cat2);
                            catDatabase.catDao().insert(cat3);

                        kittens = catDatabase.catDao().getAll();

                        }
                        System.out.println("HER ER KATTEN DU VENTET PÅ:" + kittens.get(0).getName() + " KAAAAAAAATTTTTTEN");
                    } finally {
                        catDatabase.close();

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