package com.example.hwfive;

import android.content.Context;

public class Workout {
    private int workoutType;
    private int legDay;
    private int backDay;
    private int day;
    private int week;

    public int getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(int workoutType) {
        this.workoutType = workoutType;
    }

    public int getLegDay() {
        return legDay;
    }

    public void setLegDay(int legDay) {
        this.legDay = legDay;
    }

    public int getBackDay() {
        return backDay;
    }

    public void setBackDay(int backDay) {
        this.backDay = backDay;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }


    public Workout(int workoutType, int day, int week){
        this.workoutType = workoutType;
        this.day = day;
        this.week = week;

    }
    public Workout(Context context){
        this.workoutType = Integer.parseInt(context.getString((R.string.first_workout_type)));
        this.day = Integer.parseInt(context.getString((R.string.first_workout_day)));
        this.week = Integer.parseInt(context.getString((R.string.first_workout_week)));

    }
    public void newWorkout(int newWorkoutType, int newDay, int newWeek){
        if(newWorkoutType <= 0){
            this.workoutType = 1;
        }else{
            this.workoutType = newWorkoutType;
        }
        if(newDay<=0){
            this.day = 1;
        }else{
            this.day = newDay;
        }if(newWeek<=0){
            this.week = 1;
        }else{
            this.week = newWeek;
        }
    }
    /*public void remodel(int newBathrooms, int newRooms){
        if(newBathrooms <= 0){
            this.bathrooms = 1;
        }else{
            this.bathrooms = newBathrooms;
        }
        if(newRooms<=0){
            this.rooms = 1;
        }else{
            this.rooms = newRooms;
        }
    }*/
}
