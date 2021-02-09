package com.example.oblig1.sqlLite;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.sqlDAOs.CatDao;


@Database(entities = {Cat.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CatDao catDao();

}
