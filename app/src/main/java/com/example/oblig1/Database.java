package com.example.oblig1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.helpers.DatabaseHelper;
import com.example.oblig1.sqlLite.AppDatabase;
import com.example.oblig1.sqlLite.DatabaseClient;
//import com.example.oblig1.domain.CatList;

import java.util.ArrayList;
import java.util.List;

//import static com.example.oblig1.MainActivity.DATABASE;


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
        //DatabaseClient clientDB = DatabaseClient.getInstance(getApplicationContext());
        //AppDatabase catDatabase= clientDB.getAppDatabase();


        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cats = catDatabase.catDao().getAll();

                }finally{
                    catDatabase.close();
                }
            }}).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/


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
            //CheckBox selectCat;
           // selectCat = (CheckBox) findViewById(R.id.checkBox1);

            // Remove the Cat from the database list
            int count = listView.getCount();  //number of ListView items

            dbHelper.deleteCatsDB(adapter, count,getApplicationContext());


            /*
            * This new thread is deleting the images
            * from the view and the database

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                    for (
                            int i = 0;
                            i < count; i++) {
                        if (adapter.getCheckBoxStates()[i]) {
                            Cat cat = cats.get(i - deleted);
                            catDatabase.catDao().delete(cat);
                            cats.remove(cats.get(i - deleted));
                            deleted++;
                        }
                    }
                }finally{
                        catDatabase.close();
                    }
                }
            }).start();

            /*
            *Forcing the UI thread to sleep
            * the new Thread will get time to update
            * the database before the UI do anything else

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/

            //adapter.getCheckBoxStates();
            //Update the view
            listView.setAdapter(adapter);

        });
    }


}