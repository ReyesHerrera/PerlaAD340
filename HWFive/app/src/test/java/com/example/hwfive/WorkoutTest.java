package com.example.hwfive;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;
import android.content.Context;



public class WorkoutTest {
    Workout ourWorkout;

    @Mock
    Context mockContext;

    @Before
    public void setUp(){
        ourWorkout = new Workout(2,2,2);

        MockitoAnnotations.initMocks((this));

        when(mockContext.getString(R.string.first_workout_type))
                .thenReturn("1");
        when(mockContext.getString(R.string.first_workout_day))
                .thenReturn("2");
        when(mockContext.getString(R.string.first_workout_week))
                .thenReturn("2");
    }
    @Test
    public void workoutChangesForNextWeek(){
        ourWorkout = new Workout(1,1,1);
        ourWorkout.newWorkout(2,2, 2);
        assertThat(ourWorkout.getWorkoutType()).isEqualTo(2);
    }
    /*@Test
    public void houseChangesRoomsAndBathroomsOnRemodel(){
        ourHouse = new House(300000,2,1);
        ourHouse.remodel(3,5);
        assertThat(ourHouse.getBathrooms()).isEqualTo(3);
    }*/

    @Test
    public void workoutWasRepeatedWeDoNotWantThat(){
        ourWorkout.newWorkout(1, 1, 1);
        assertThat(ourWorkout.getWorkoutType()).isEqualTo(1);
    }
    @Test
    public void workoutIsTheSameSameWeek(){
        Workout sameWorkout = new Workout(mockContext);
        assertThat(sameWorkout.getWorkoutType()).isEqualTo(1);

    }
}

