package com.example.oblig1.sqlDAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.oblig1.domain.Cat;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface CatDao {
    @Query("SELECT * FROM cat")
    List<Cat> getAll();

    @Query("SELECT * FROM cat")
    LiveData<List<Cat>> getAllLive();

    @Query("SELECT COUNT(*) FROM cat")
    ListenableFuture<Integer> getSize();

    @Insert
    void insert(Cat cat);

    @Delete
    void delete(Cat cat);


}

