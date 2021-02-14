package com.example.oblig1;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ScoreTest {

    @Rule
    public ActivityScenarioRule<Quiz> activityRule =
            new ActivityScenarioRule<>(Quiz.class);

    @Test
    public void scoreIsCorrect() {
        onView(withId(R.id.editText1))
                .perform(typeText("Cat one"), closeSoftKeyboard());

        onView(withId(R.id.buttonSvar))
                .perform(click());

      //  onView(withId(R.id.quizScore))
        //        .check();


    }

}
