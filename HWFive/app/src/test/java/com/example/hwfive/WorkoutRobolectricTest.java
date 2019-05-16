package com.example.hwfive;


import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static com.google.common.truth.Truth.assertThat;

//want to use this test runner not default
@RunWith(RobolectricTestRunner.class)
public class WorkoutRobolectricTest {

    @Test
    public void firstWorkoutWithRobolectric(){

        /*Context roboContext = ApplicationProvider.getApplicationContext();

        Workout defaultOurHouse = new Workout(roboContext);
        assertThat(defaultOurHouse.getWeek()).isEqualTo(1);*/

    }

}
