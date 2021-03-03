package com.example.oblig1;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AddActivityTest {
    @Rule
    public  ActivityScenarioRule<AddActivity> activityRule =
            new ActivityScenarioRule<>(AddActivity.class);

    AddActivity activity = null;

    @Before
    public void setUp() {
        Intents.init();
        activityRule.getScenario().onActivity(addActivity -> { activity = addActivity;});
    }
    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void addButton() throws ExecutionException, InterruptedException {
        Intent resultData = new Intent();
        String catName = "Katy Perry";
        Uri catImageURI = Uri.parse("content://com.android.providers.media.documents/document/image%3A32");
        resultData.setData(catImageURI);
        // TODO do we need the next two lines
        resultData.putExtra("kitten", catName);
        resultData.putExtra("image", catImageURI);

        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        //intending(not(isInternal())).respondWith(result);
        intending(hasAction(Intent.ACTION_OPEN_DOCUMENT)).respondWith(result);

        AddActivity addActivity = activity;

            int listSize = addActivity.getListSize();
            onView(withId(R.id.editTextAddClass)).perform(typeText("volker"), closeSoftKeyboard());
            onView(withId(R.id.buttonChooseImageAdd)).perform(click());
            onView(withId(R.id.buttonAdd))
                    .perform(click());

            int newListSize = addActivity.getListSize();

            //Checking that the previous listSize is smaller than newListSize
            assertTrue(listSize < newListSize);

    }


}
