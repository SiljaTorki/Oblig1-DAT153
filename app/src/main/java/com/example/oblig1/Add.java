package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Add extends AppCompatActivity {
    private ArrayList<Cat> catList;  // A list to store the cats photos in
    private static final int PICK_IMAGE_REQUEST = 100;
    private ImageView iv;
    private String name;
    private Bitmap image;
    //private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        iv = (ImageView) findViewById(R.id.imageView4);

        //TODO: Liste av navn/bilder som er generert i tilfeldig rekkefølge (random)
     //   ArrayList<Cat> catList = (ArrayList<Cat>) getIntent().getSerializableExtra("liste");
        catList = CatList.getCatList();


        Button btn = (Button)findViewById(R.id.buttonVelgBilde);       //The button is created´"Velg bilde"
        btn.setOnClickListener(new View.OnClickListener() {            //The action is created

                 public void onClick(View v) {                         //Make it possible for the user to add an image
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_IMAGE_REQUEST);
                }
        });

        Button btn2 = (Button)findViewById(R.id.buttonAdd);       //The button is created´"Legg til"
        btn2.setOnClickListener(new View.OnClickListener() {      //The action is created

            public void onClick(View v) {

                EditText editText = (EditText)findViewById(R.id.editTextNavn);
                name = editText.getText().toString();

                String text;

                if(name != null && image != null) {                   //Checking that name and image have values
                    CatList.addCat(name, image);                      //Adding image and name of cat to the arrayList
                    System.out.println("Antall: " + catList.size());  //Used for testing

                    text = "Bilde er lagt til!";                       //Toast-text if image is added

                }else{
                text = "Legg til text eller bilde";                    //Toast-text if name or image is missing
                }
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;                      //Says how long the Toast should last
                Toast toast = Toast.makeText(context, text, duration);  //creating the Toast

                toast.show();

                editText.getText().clear();                              //emptying the editText
                iv.setImageResource(0);                                   //removing image
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    //uri = selectedImage;
                    // method 1
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        image = bitmap;
                        iv.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // method 2

                    //try {
                    //    InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                    //    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                    //    imageStream.close(;
                    //   iv.setImageBitmap(yourSelectedImage);
                    //} catch (FileNotFoundException e) {
                    //    e.printStackTrace();
                    //}

                    // method 3
                    // iv.setImageURI(selectedImage);
                }
                break;
        }}

        //Method to add new cat-photos and names
        public void addCat (Cat cat){
            catList.add(cat);
        }

        //TODO: Add Cat button:
        // Create a new Cat object with the Picture and name
        // Add the Cat to the database list

        //TODO: Choose picture button:
        // Use the built in feature
        // Add the picture to the ImageView
    }