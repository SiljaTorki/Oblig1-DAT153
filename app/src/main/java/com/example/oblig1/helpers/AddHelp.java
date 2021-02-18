package com.example.oblig1.helpers;

import android.graphics.Bitmap;
import android.net.Uri;

public class AddHelp {
    private boolean addingOK = false;

    public String responseUser(String name, String image){
       String response;
       //Method for adding the new cat-image
        if(!name.trim().equals("") && !image.trim().equals("")) {
            System.out.println("ADDED");
            response = "Image is added!";                //Toast-text if image is added
            addingOK = true;
        }else{
            response = "Please add text and/or image";   //Toast-text if name or image is missing
        }
        return response;
    }

    public boolean readyForAdding(){
        return addingOK;
    }

}
