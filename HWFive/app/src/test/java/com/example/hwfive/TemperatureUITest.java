package com.example.hwfive;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TemperatureUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFoodServiceGoodTipGeneration() {

        onView(withId(R.id.txtbx_svc_amt))
                .perform(typeText("10.00"));

        // Set the value of the service type
        // spinner
        onView(withId(R.id.spn_svc_type))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Food server")))
                .perform(click());

        // Set the value of the quality
        // spinner
        onView(withId(R.id.spn_quality))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Good")))
                .perform(click());

        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.tip_output)).check(matches(withText("$2.00")));
    }
}