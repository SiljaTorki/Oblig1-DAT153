package com.example.oblig1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oblig1.helpers.AddHelp;
import com.example.oblig1.helpers.DatabaseHelper;

/**
*   This class is allowing a user to add their own chosen image to the database
 */

public class Add extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 100; // the request code defined as an instance variable
    private ImageView iv;
    private String name;
    private AddHelp ah = new AddHelp();
    private Uri selectedImage;
    private DatabaseHelper dbHelper;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        theToolbar();

        //connects to the database
        dbHelper = new DatabaseHelper(getApplicationContext());

        //Getting imageView and TextEdit
        iv = (ImageView) findViewById(R.id.imageViewAddClass);
        editText = (EditText)findViewById(R.id.editTextAddClass);

        //The buttons for choosing and adding an image are created
        Button btnChoose = (Button)findViewById(R.id.buttonChooseImageAdd);
        Button btnAdd = (Button)findViewById(R.id.buttonAdd);

        /*
        * The choose-action is created, by using lambda expression
        * This methods makes it possible for the user to find and add an image
        */
        btnChoose.setOnClickListener((View v) -> {
           Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
           intent.setType("image/*");
           startActivityForResult(intent, PICK_IMAGE_REQUEST);
       });

        //The add-action is created
        btnAdd.setOnClickListener((View v) -> {
            addAndResponse();

        });
    }

    /*
    * The method makes it possible for the users to choose an image from their phone
    * When the image is chosen, it is shown in app.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {
                    selectedImage = data.getData();

                    iv.setImageURI(selectedImage);
                    getContentResolver().takePersistableUriPermission(selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                break;
        }
    }


    /*
    * the action that happens when the AddButton is clicked
    * checking that the user has provided both an image and name with help from AddHelp.java
     */
    private void addAndResponse(){
        //Creates an response to the user, by using responseUser() from AddHelp.java
        name = editText.getText().toString();
        String response = ah.responseUser(name,selectedImage.toString());

        //checking that the image and name can be added, with help from AddHelp.java
        if(ah.readyForAdding())
            dbHelper.insertCatsDB(name,selectedImage.toString());

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;               //Says how long the Toast should last
        Toast toast = Toast.makeText(context, response, duration);  //creating the Toast
        //provides a response for the user
        toast.show();

        //Removing the text
        editText.getText().clear();
    }

    //make it possible for the user to go back to MainActivity.java
    private void theToolbar() {
        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar_AddClass);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }
}