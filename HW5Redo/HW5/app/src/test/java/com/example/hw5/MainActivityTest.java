package com.example.hw5;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowToast;

import static junit.framework.TestCase.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {



    @Test
    public void MainActivityTestingRadio() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.findViewById(R.id.radioButtonC2F).performClick();

        MainActivity activity2 = Robolectric.setupActivity(MainActivity.class);
        activity2.findViewById(R.id.radioButtonF2C).performClick();
/*
        assertEquals("data missing  ", ShadowToast.getTextOfLatestToast());
*/
    }
}
