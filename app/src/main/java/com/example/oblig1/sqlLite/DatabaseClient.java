package com.example.oblig1.sqlLite;

import android.content.Context;

import androidx.room.Room;

/**
* The class is creating access to the database
 *
* It is used in the databaseHelper.java
 */

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    //Constructor which creates the appDatabase
    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "catDatabase").build();
    }
    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }


}
