package com.example.oblig1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oblig1.domain.Cat;
//import com.example.oblig1.domain.CatList;
import com.example.oblig1.helpers.AddHelp;
import com.example.oblig1.sqlLite.AppDatabase;
import com.example.oblig1.sqlLite.DatabaseClient;

import java.io.IOException;
import java.util.List;

//import static com.example.oblig1.MainActivity.DATABASE;

public class Add extends AppCompatActivity {
    private List<Cat> catList;  // A list to store the cats photos in
    private static final int PICK_IMAGE_REQUEST = 100; // the request code defined as an instance variable
    private ImageView iv;
    private String name;
    private Bitmap image;
    private AddHelp ah = new AddHelp();
    private Uri selectedImage;
    private DatabaseClient clientDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar4);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        DatabaseClient clientDB = DatabaseClient.getInstance(getApplicationContext());
        AppDatabase catDatabase= clientDB.getAppDatabase();

        //Getting imageView and TextEdit
        iv = (ImageView) findViewById(R.id.imageView4);
        EditText editText = (EditText)findViewById(R.id.editTextName);

        //The buttons for choosing and adding an image is created
        Button btnChoose = (Button)findViewById(R.id.buttonVelgBilde);
        Button btnAdd = (Button)findViewById(R.id.buttonAdd);

        /*
        *The choose-action is created, by using lambda expression
        * This methods makes it possible for the user to find and add an image
        */
        btnChoose.setOnClickListener((View v) -> {
           Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
           intent.setType("image/*");
           startActivityForResult(intent, PICK_IMAGE_REQUEST);
       });

        //The add-action is created
        btnAdd.setOnClickListener((View v) -> {

            name = editText.getText().toString();
            Cat cat = new Cat(name, selectedImage.toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        catDatabase.catDao().insert(cat);

                    }finally{
                        catDatabase.close();
                    }
                }}).start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Creates an response to the user, by using responseUser() from AddHelp.java
            String response = ah.responseUser(name,image);

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;               //Says how long the Toast should last
            Toast toast = Toast.makeText(context, response, duration);  //creating the Toast

            toast.show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {
                    selectedImage = data.getData();

                    // method 1
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        image = bitmap;
                        iv.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    getContentResolver().takePersistableUriPermission(selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                break;
        }}
    }