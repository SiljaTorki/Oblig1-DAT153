package com.example.oblig1;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ScoreTest {

    @Rule
    public ActivityScenarioRule<Quiz> activityRule =
            new ActivityScenarioRule<>(Quiz.class);

    @Test
    public void scoreIsCorrect() {

            //Providing an answer for a random image in Quiz.class
            onView(withId(R.id.editText1))
                    .perform(typeText("Cat one"), closeSoftKeyboard());

            //Clicks the button i order to check whether the answer is correct or not
            onView(withId(R.id.buttonSvar))
                    .perform(click());

            /*
            * If it is correct guessed, then the score should update to 1
            * otherwise it should not be updated
            * only the first image is tested
             */
            if(onView(withId(R.id.imageView)).equals("android.resource://com.example.oblig1/drawable/cat_one")){
                assertTrue(onView(withId(R.id.quizScore)).equals("Your score: 1"));
            }else{
                assertFalse(onView(withId(R.id.quizScore)).equals("Your score: 1"));
            }



    }

}
