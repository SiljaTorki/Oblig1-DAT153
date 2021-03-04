package com.example.oblig1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.viewModels.ViewModelDatabase;

import java.util.List;

/**
* This class is responsible for providing a visual of all elements in the database.
* It also makes it possible for the users to delete selected images
* and also to add new images, by redirecting the user to the AddActivity.class.
*
* The view is updated, when a image is deleted og added.
 */

public class DatabaseActivity extends AppCompatActivity {

    ListView listView;
    private CustomAdapter adapter;
    private ViewModelDatabase vmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        //The remove- and add-button are created
        Button btnAdd = (Button)findViewById(R.id.databaseButtonAdd);
        Button btnRemove = (Button)findViewById(R.id.databaseButtonRemove);

        btnAdd.setEnabled(false);
        btnRemove.setEnabled(false);

        vmd = new ViewModelProvider(this).get(ViewModelDatabase.class);
        vmd.getAllLive().observe(this, (List<Cat> obs) ->{
            adapter = new CustomAdapter(obs, DatabaseActivity.this);
            //Lists all the cats
            listView.setAdapter(adapter);

            btnAdd.setEnabled(true);
            btnRemove.setEnabled(true);

        });

        theToolbar();

        //Create the view dynamic
        listView = findViewById(R.id.listView);

        //The add-button is created with lambda expression
        btnAdd.setOnClickListener((View v) -> {
            //this should send the user to the add-activity
            Intent intent = new Intent(DatabaseActivity.this, AddActivity.class);
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

       //gets the list to delete the selected cats
        vmd.getAllLive().observe(this, (List<Cat> obs) -> {
            int count = listView.getCount();  //number of ListView items
            int deleted = 0;

            for (int i = 0; i < count; i++) {

                if (adapter.getCheckBoxStates()[i]) {
                    adapter.getCheckBoxStates()[i] = false;         //to prevent the next checkboxes from being checked
                    Cat cat = obs.get(i - deleted);
                    // Remove the Cat from the database list
                    vmd.delete(cat);
                    obs.remove(obs.get(i-deleted));
                    deleted++;
                }
            }

            //Update the view
            listView.setAdapter(adapter);
        });

    }
}