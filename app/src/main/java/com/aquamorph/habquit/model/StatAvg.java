package com.aquamorph.habquit.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shawnkelly on 3/25/17.
 * CURRENTLY A TEST MODEL FOR BRINGING IN STATS
 */

public class StatAvg {

    @Expose
    @SerializedName("habit_id")
    private int habitId;
    @Expose
    @SerializedName("first_habit_avg")
    private double baseLineavg;
    @Expose
    @SerializedName("current_habit_avg")
    private double currentHabitAvg;

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public double getBaseLineavg() {
        return baseLineavg;
    }

    public void setBaseLineavg(double baseLineavg) {
        this.baseLineavg = baseLineavg;
    }

    public double getCurrentHabitAvg() {
        return currentHabitAvg;
    }

    public void setCurrentHabitAvg(double currentHabitAvg) {
        this.currentHabitAvg = currentHabitAvg;
    }


}
