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
import com.example.oblig1.repository.Repository;
import com.example.oblig1.sqlLite.AppDatabase;
import com.example.oblig1.sqlLite.DatabaseClient;
import com.example.oblig1.viewModels.ViewModelDatabase;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
* This class is responsible for providing a visual of all elements in the database.
* It also makes it possible for the users to delete selected images
* and also to add new images, by redirecting them to the Add.class.
*
* The view is updated, when a image is deleted og added.
 */

public class Database extends AppCompatActivity {

    ListView listView;
    private List<Cat> cats;  // A list to store the cat-photos in
    private int deleted = 0;
    private DatabaseHelper dbHelper;
    private CustomAdapter adapter;
    private ViewModelDatabase vmd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        //The remove- and add-button are created
        Button btnAdd = (Button)findViewById(R.id.buttonToAdd);
        Button btnRemove = (Button)findViewById(R.id.buttonToRemove);

        btnAdd.setEnabled(false);
        btnRemove.setEnabled(false);

        vmd = new ViewModelDatabase(getApplication());
        vmd.getAllLive().observe(this, (List<Cat> obs) ->{
            adapter = new CustomAdapter(obs,Database.this);
            //Lists all the cats
            listView.setAdapter(adapter);

            btnAdd.setEnabled(true);
            btnRemove.setEnabled(true);

        });


        //Creates access to the database
        dbHelper = new DatabaseHelper (getApplicationContext());
        cats = dbHelper.getAllCats();

        theToolbar();

        //Create the view dynamic
        listView = findViewById(R.id.listView);

        //The add-button is created with lambda expression
        btnAdd.setOnClickListener((View v) -> {
            //this should send the user to the add-activity
            Intent intent = new Intent(Database.this, Add.class);
            startActivity(intent);
        });


        //The remove action is created with lambda expression
        btnRemove.setOnClickListener((View v) -> {
            deleteCat();
        });
    }

    //Makes it possible for the user to go back to the MainActivity.class
    private void theToolbar(){
        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /*
    * Deletes the selected cats and updates the ListView
     */

    public void deleteCat(){
        // Remove the Cat from the database list
        int count = listView.getCount();  //number of ListView items

        //dbHelper.deleteCatsDB(adapter, count);
        int deleted = 0;

        for (int i = 0; i < count; i++) {

            if (adapter.getCheckBoxStates()[i]) {
                adapter.getCheckBoxStates()[i] = false;         //to prevent the next checkboxes from being checked
                Cat cat = cats.get(i - deleted);
                vmd.delete(cat);;
                cats.remove(cats.get(i - deleted));
                deleted++;

            }
        }


        //Update the view
        listView.setAdapter(adapter);
    }


}