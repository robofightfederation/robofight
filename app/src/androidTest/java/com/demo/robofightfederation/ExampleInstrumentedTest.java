package com.demo.robofightfederation;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.accessibility.AccessibilityChecks;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<RobotBuilderActivity> activityTestRule = new ActivityTestRule<>(RobotBuilderActivity.class);

    @BeforeClass
    public static void enableAccessibilityChecks() {
        AccessibilityChecks.enable();
    }

    @Test
    public void testLoads() {

//        onView(withId(R.id.text_hello))
//                .check(matches(isDisplayed()));
//
//        onView(withId(R.id.button_buildabot))
//                .perform(click());
//
        onView(withId(R.id.edit_callsign))
                .perform(clearText())
                .perform(typeText("hello?"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.demo.robofightfederation", appContext.getPackageName());
    }
}
