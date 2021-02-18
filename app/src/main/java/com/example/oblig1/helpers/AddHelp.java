package com.example.oblig1.helpers;

/**
* This class is responsible for checking that the user adds both image and name
* if either are missing then nothing should be added to the database
 */
public class AddHelp {
    private boolean addingOK = false;

    //Checking that the user has chosen both an image and a name
    public String responseUser(String name, String image){
       String response;
       //Method for adding the new cat-image
        if(!name.trim().equals("") && !image.trim().equals("")) {
            response = "Image is added!";                //Toast-text if image is added
            addingOK = true;
        }else{
            addingOK = false;
            response = "Please add text and/or image";   //Toast-text if name or image is missing
        }
        return response;
    }

    public boolean readyForAdding(){
        return addingOK;
    }

}
