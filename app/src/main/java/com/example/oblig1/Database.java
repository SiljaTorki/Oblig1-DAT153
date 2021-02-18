package com.example.oblig1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.helpers.DatabaseHelper;
import java.util.List;

public class Database extends AppCompatActivity {

    ListView listView;
    private List<Cat> cats;  // A list to store the cat-photos in
    private int deleted = 0;
    //private DatabaseClient clientDB;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);


        //Creates access to the database
        dbHelper = new DatabaseHelper (getApplicationContext());
        cats = dbHelper.getAllCats();


        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        //Create the view dynamic
        listView = findViewById(R.id.listView);

        // Creates array adapter
        CustomAdapter adapter = new CustomAdapter(cats,Database.this);

        //Lists all the cats
        listView.setAdapter(adapter);


        //The remove- and add-button are created
        Button btnAdd = (Button)findViewById(R.id.buttonToAdd);
        Button btnRemove = (Button)findViewById(R.id.buttonToRemove);


        //The add-button is created with lambda expression
        btnAdd.setOnClickListener((View v) -> {
            //this should send the user to the add-activity
            Intent intent = new Intent(Database.this, Add.class);
            startActivity(intent);
        });


        //The remove action is created with lambda expression
        btnRemove.setOnClickListener((View v) -> {

            // Remove the Cat from the database list
            int count = listView.getCount();  //number of ListView items

            dbHelper.deleteCatsDB(adapter, count);

            //Update the view
            listView.setAdapter(adapter);

        });
    }


}