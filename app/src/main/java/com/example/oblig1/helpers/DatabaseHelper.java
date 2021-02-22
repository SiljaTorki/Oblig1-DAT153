package com.example.oblig1.helpers;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.oblig1.CustomAdapter;
import com.example.oblig1.domain.Cat;
import com.example.oblig1.sqlLite.AppDatabase;
import com.example.oblig1.sqlLite.DatabaseClient;

import java.util.List;
/**
* This class is meant to help setting, inserting, getting or deleting images in the database
* In the comments, the main thread is referred to as the UI-thread
 */

public class DatabaseHelper {
    public final static String DATABASE = "catDatabase";
    final String DRAWABLE = "android.resource://com.example.oblig1/drawable/";
    private AppDatabase catDatabase;
    private List<Cat> cats;
    private boolean isDone;

    /**
    *  The constructor gets access to the database
    *  with help from the DatabaseClient.java
    */
    public DatabaseHelper(Context mCtx){
        DatabaseClient clientDB = DatabaseClient.getInstance(mCtx);
        catDatabase = clientDB.getAppDatabase();
    }

    /**
    * Initializes setUp, to add the three original images to the database
    * This method does not contain Thread.sleep(),
    * since the UI thread does not have to wait for it to finish in MainActivity.class
     *
     * used in MainActivity.class
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


    /**
     * Gets the complete list of all the cats from the database
     *
     * Is used in quiz.java and in database.java
     */
    public List<Cat> getAllCats(){
        getListFromDB();
        return cats;
    }

    /**
    * This method is getting the cat-names and -images from the database
    * It uses a while-loop in case the new Thread is not done,
    * and must unfortunately wait to prevent an app-crash.
    * Thread.sleep() is not ideally
     *
     * used by the method getAllCats() in this class
     */
    private void getListFromDB(){
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

    /**
    * Inserts the cat-name- and -image into the database
    *
    * It is used in Add.class
     */
    public void insertCatsDB(String name, String image){
        Cat cat = new Cat(name, image);
        new Thread(new Runnable() {
            @Override
            public void run() {
                catDatabase.catDao().insert(cat);

            }}).start();
    }

    /**
    * Deletes the selected images and names in the database
    * It gets the status of the checkboxes
    * then it deletes the checked cats
    * cats.get(i-deleted) is used in order to find the correct cat
    *
    * It also has a while-loop, to force the UI-thread to wait
    * until the new Thread is done --> not ideally
    *
    * It is used in Database.java
     */
    public void deleteCatsDB(CustomAdapter adapter, int count){
        isDone = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                    int deleted = 0;

                    for (int i = 0; i < count; i++) {

                        if (adapter.getCheckBoxStates()[i]) {
                            adapter.getCheckBoxStates()[i] = false;         //to remove number of checked boxes
                            Cat cat = cats.get(i - deleted);
                            catDatabase.catDao().delete(cat);
                            cats.remove(cats.get(i - deleted));
                            deleted++;
                        }
                    }
                    isDone = true;
            }
        }).start();

        while(!isDone){
             try {
                  Thread.sleep(100);
             } catch (InterruptedException e) {
               e.printStackTrace();
             }
        }
    }


}
