package com.example.oblig1;
/*
import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.sqlDAOs.CatDao;
import com.example.oblig1.sqlLite.AppDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.example.oblig1.MainActivity.DATABASE;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)

public class DatabaseTest {

    private static final String DRAWABLE = "catDatabase";
    private AppDatabase catDatabase = Room.databaseBuilder(
            getApplicationContext(),
            AppDatabase.class,
            DATABASE).build();
    private List<Cat> catDao = catDatabase.catDao().getAll();
    private Cat newCat = new Cat("Cat One", DRAWABLE + "cat_one");

    @Rule
    public ActivityScenarioRule<Database> activityRule =
            new ActivityScenarioRule<>(Database.class);

    @Test
    public void addTest() {
        // Checks if Cat One was addet
        catDao.add(newCat);
        assertThat(catDao.size(), equalTo(1));
    }

    @Test
    public void removeTest() {
        // Checks if Cat One was deleted
        catDao.remove(newCat);
        assertThat(catDao.size(), equalTo(1));
    }


}
*/