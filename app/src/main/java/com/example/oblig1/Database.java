package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class Database extends AppCompatActivity {

    ListView listView;
    private ArrayList<Cat> cats;  // A list to store the cats photos in


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        //Create the view dynamic
        listView = findViewById(R.id.listView);

        // gats all the cats in a list
        cats = CatList.getCatList();

        // Creates array adapter
        CustomAdapter adapter = new CustomAdapter(cats,Database.this);

        //Lists all the cats
        listView.setAdapter(adapter);


        //Send the list with the button
        Button btn1 = (Button)findViewById(R.id.buttonToAdd);       //The first button is created´"Legg til"
        btn1.setOnClickListener(new View.OnClickListener() {        //The first buttons action is created
            public void onClick(View v) {
                //this should send the user to the add-activity
                Intent intent = new Intent(Database.this, Add.class);
                startActivity(intent);
            }
        });

        //Delete the selected cats
        Button btn2 = (Button)findViewById(R.id.buttonToRemove);       //The button is created´"Fjern"
        btn2.setOnClickListener(new View.OnClickListener() {           //The button´s action is created
            public void onClick(View v) {
                CheckBox selectCat;
                selectCat = (CheckBox) findViewById(R.id.checkBox1);

                // Remove the Cat from the database list
                int count = listView.getCount();  //number of my ListView items
                int deleted = 0;
                for (int i = 0; i < count; i++) {
                    if(adapter.getCheckBoxStates()[i]) {
                        cats.remove(cats.get(i-deleted));
                        deleted++;
                    }
                }


                CustomAdapter adapter2 = new CustomAdapter(cats, Database.this);
                adapter2.getCheckBoxStates();
                //Update the view
                listView.setAdapter(adapter2);
                Intent intent = new Intent(Database.this, Database.class);
                startActivity(intent);
            }
        });
    }


}