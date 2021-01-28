package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends AppCompatActivity {


    private ArrayList<Cat> catList = new ArrayList<>();  // A list to store the cats photos in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        // HashMap<Image,String> hm = new HashMap<Image,String>(); //Hashmap to find correct name for each image is not in use
        //TODO: Create the view dynamic, maybe we need to use RecyclerView
        // Creating Layouts Programmatically (Lecture 06, page 5)

        //Send the list with the button
        Button btn1 = (Button)findViewById(R.id.buttonToAdd);       //The first button is created´"Legg til"
        btn1.setOnClickListener(new View.OnClickListener() {    //The first buttons action is created
            public void onClick(View v) {
                //this should send the user to the add-activity
                Intent intent = new Intent(Database.this, Add.class);
                startActivity(intent);
            }
        });

        //TODO: Add checkbox/radiobutton in layout
        Button btn2 = (Button)findViewById(R.id.buttonToRemove);       //The second button is created´"Fjern"
        btn2.setOnClickListener(new View.OnClickListener() {    //The second buttons action is created
            public void onClick(View v) {
                //TODO: Remove the Cat from the database list
                //TODO: Update the view
                Intent intent = new Intent(Database.this, Database.class);
                startActivity(intent);
            }
        });
    }

    //Method to add new cat-photos and names
    public void addCat(Cat cat) {
        catList.add(cat);
    }
}