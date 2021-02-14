package com.example.oblig1.helpers;

import android.graphics.Bitmap;

//import com.example.oblig1.domain.CatList;

public class AddHelp {

    public String responseUser(String name, Bitmap image){
       String response;
       //Method for adding the new cat-image
        if(!name.equals("") && image != null) {
            response = "Image is added!";                //Toast-text if image is added
        }else{
            response = "Please add text and/or image";   //Toast-text if name or image is missing
        }
        return response;
    }

}
