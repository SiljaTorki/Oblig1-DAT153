package com.example.oblig1.helpers;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.oblig1.CustomAdapter;
import com.example.oblig1.domain.Cat;
import com.example.oblig1.sqlLite.AppDatabase;
import com.example.oblig1.sqlLite.DatabaseClient;

import java.util.List;
/*
* This class is meant to help setting, getting or deleting images in the database
* In the methods is Thread.sleep used, which is not ideal.
* The main thread would not wait for the new threads to be done,
* if Thread.sleep() was not initilized
 */

public class DatabaseHelper {
    public final static String DATABASE = "catDatabase";
    final String DRAWABLE = "android.resource://com.example.oblig1/drawable/";
    //DatabaseClient clientDB;
    AppDatabase catDatabase;
    private List<Cat> cats;

    /*
    *  The constructor gets access to the database
    */
    public DatabaseHelper(Context mCtx){
        DatabaseClient clientDB = DatabaseClient.getInstance(mCtx);
        catDatabase = clientDB.getAppDatabase();
    }
    /*
    * Initilize setUp, to add the three original images to the database
    * This method does not contain Thread.sleep(),
    * since the UI thread does not have to wait for it to finish in MainActivity.class
     */
    public void setUp(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                   cats = catDatabase.catDao().getAll();
                    if(cats.size() == 0) {

                        Cat cat1 = new Cat("Cat one", DRAWABLE + "cat_one");
                        Cat cat2 = new Cat("Cat two", DRAWABLE + "cat_two");
                        Cat cat3 = new Cat("Cat three", DRAWABLE + "cat_three3");

                        catDatabase.catDao().insert(cat1);
                        catDatabase.catDao().insert(cat2);
                        catDatabase.catDao().insert(cat3);

                    }
            }
        }).start();
    }


    //This method is getting the catnames and images from the database
    public void getListFromDB(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                    cats = catDatabase.catDao().getAll();

            }}).start();

        while(cats == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertCatsDB(String name, String image){
        Cat cat = new Cat(name, image);
        new Thread(new Runnable() {
            @Override
            public void run() {
                catDatabase.catDao().insert(cat);

            }}).start();

      /*  try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    /*
    * The methods deletes the selected images in the database
    * It gets the status of the checked check-boxes
    * then it deletes the images and names of the cats that are checked
     */
    public void deleteCatsDB(CustomAdapter adapter, int count){

        new Thread(new Runnable() {
            @Override
            public void run() {
                    int deleted = 0;

                    for (int i = 0; i < count; i++) {

                        if (adapter.getCheckBoxStates()[i]) {
                            Cat cat = cats.get(i - deleted);
                            catDatabase.catDao().delete(cat);
                            cats.remove(cats.get(i - deleted));
                            deleted++;
                        }
                    }


            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Gets the complete list of cats from the database
    public List<Cat> getAllCats(){
        getListFromDB();
        return cats;
    }
}
