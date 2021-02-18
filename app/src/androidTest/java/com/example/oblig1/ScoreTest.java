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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ScoreTest {

    /*
    * @Rule is used to determine which class is tested
     */
    @Rule
    public ActivityScenarioRule<Quiz> activityRule =
            new ActivityScenarioRule<>(Quiz.class);


    /*
    * A test to check that the score in Quiz.java is updated
    * when guessing the correct cat-name of the cat-image in the quiz
    *
    * First getCatName() from Quiz.java file is called
    * Then checking the score is 0 before guessing
    * After clicking "check answer" button, the score should be updated to 1
     */
    @Test
    public void scoreIsCorrect() {

        String rightName = Quiz.getCatName();
        onView(withId(R.id.quizScore)).check(matches(withText("Your score: " + 0 )));
        onView(withId(R.id.editTextGuess)).perform(typeText(rightName), closeSoftKeyboard());
        onView(withId(R.id.buttonCheckAnswer)).perform(click());
        onView(withId(R.id.quizScore)).check(matches(withText("Your score: " + 1 )));

    }

    /*
     * A test to check that the score in Quiz.java is NOT updated
     * when providing a wrong guess for the cat-name of the cat-image in the quiz
     *
     * First getCatName() from Quiz.java file is called
     * Then checking the score is 0 before guessing
     * After clicking "check answer" button, the score should stay at 0
     */
    @Test
    public void scoreIsWrong() {
        onView(withId(R.id.quizScore)).check(matches(withText("Your score: " + 0 )));
        onView(withId(R.id.editTextGuess)).perform(typeText("Hei"), closeSoftKeyboard());
        onView(withId(R.id.buttonCheckAnswer)).perform(click());
        onView(withId(R.id.quizScore)).check(matches(withText("Your score: " + 0 )));
    }

}
