package com.example.oblig1;

//import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.common.collect.Iterables;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
//import static androidx.test.ext.truth.content.IntentSubject.assertThat;



@RunWith(AndroidJUnit4.class)
public class buttonTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void intentsInit() {
        // initialize Espresso Intents capturing
        Intents.init();
    }

    @After
    public void intentsTeardown() {
        // release Espresso Intents capturing
        Intents.release();
    }


    @Test
    public void pushButton() {
        //onView(withText("Hello world!")).check(matches(isDisplayed()));
        onView(withId(R.id.buttonQuiz))
                .check(matches((isDisplayed())));

        onView(withId(R.id.buttonQuiz))
                .perform(click());

        intended(
                hasComponent(Quiz.class.getName())
                //assertThat(Iterables.getOnlyElement(Intents.getIntents())).hasComponentClass(Quiz.class);
        );



        //assertTrue(withId(R.id.buttonQuiz).hasComponany(Quiz.class));

        // An intent is fired to launch a different Activity. Robolectric doesn't currently
        // support launching a new Activity, so use Espresso Intents to verify intent was sent
       // assertThat(Iterables.getOnlyElement(Intents.getIntents())).hasComponentClass(Quiz.class);
    }
}
