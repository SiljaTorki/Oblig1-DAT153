package com.example.oblig1;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.oblig1.domain.Cat;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.util.EnumSet.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class AddTest {
    @Rule
    public ActivityScenarioRule<Add> activityRule =
            new ActivityScenarioRule<>(Add.class);

    @Test
    public void addButton() {
       // onView(withId(R.id.buttonVelgBilde))
         //       .perform(click());

        Intent resultData = new Intent();
        String catName = "Katy Perry";
        String catImage = "android.resource://com.example.oblig1/drawable/cat_one";
        resultData.putExtra("kitten", catName);
        resultData.putExtra("image",catImage);

        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        intending(toPackage("com.example.oblig1")).respondWith(result);



      //  onData(allOf(is(instanceOf(.class)), hasEntry(equalTo("STR"), is("item: 50"))))
      //          .perform(click());


        onView(withId(R.id.buttonAdd))
                .perform(click());
        //onView(withId(R.id.editTextName))
          //      .check(matches(isDisplayed()));

    }


}
