package com.example.oblig1;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.oblig1.domain.Cat;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.util.EnumSet.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AddTest {
    @Rule
    public ActivityScenarioRule<Add> activityRule =
            new ActivityScenarioRule<>(Add.class);

    @Before
    public void setUp() {
        Intents.init();
    }
    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void addButton() {
        int listSize = Add.getListSize();

        Intent resultData = new Intent();
        String catName = "Katy Perry";
        Uri catImageURI = Uri.parse("android.resource://com.example.oblig1/drawable/cat_one");
        resultData.setData(catImageURI);
        // TODO do we need the next two lines
        resultData.putExtra("kitten", catName);
        resultData.putExtra("image", catImageURI);

        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

      // intending(not(isInternal())).respondWith(result);
        intending(toPackage("com.example.oblig1")).respondWith(result);

        onView(withId(R.id.buttonAdd))
                .perform(click());

        int newListSize = Add.getListSize();

        //Checking that the previous listSize is smaller than newListSize
        assertTrue(listSize < newListSize);
    }


}
