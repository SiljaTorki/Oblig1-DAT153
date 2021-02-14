package com.example.oblig1;


import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class AddTest {
    @Rule
    public ActivityScenarioRule<Add> activityRule =
            new ActivityScenarioRule<>(Add.class);

    @Test
    public void addButton() {
        onView(withId(R.id.buttonVelgBilde))
                .perform(click());

        onView(withId(R.id.buttonVelgBilde))
                .perform(click());
        //onView(withId(R.id.editTextName))
          //      .check(matches(isDisplayed()));

    }


}
