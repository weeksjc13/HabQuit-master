package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
/**
 * Created by Shawn on 2/3/2017.
 */

public class TrackHabit {
    @Expose
    @SerializedName("user_id")
    private int userId;
    @Expose
    @SerializedName("habit_id")
    private int habit_id;
    @Expose
    @SerializedName("last_activity")
    private String last_activity;
    @Expose
    @SerializedName("track_habit_id")
    private int trackHabitId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHabit_id() {
        return habit_id;
    }

    public void setHabit_id(int habit_id) {
        this.habit_id = habit_id;
    }

    public String getLast_activity() {
        return last_activity;
    }

    public void setLast_activity(String last_activity) {
        this.last_activity = last_activity;
    }

    public int getTrackHabitId() {
        return trackHabitId;
    }

    public void setTrackHabitId(int trackHabitId) {
        this.trackHabitId = trackHabitId;
    }
}
