package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ryankurdewan on 3/27/17.
 */

public class UserRecord {
    @Expose
    @SerializedName("user_id")
    int userId;
    @Expose
    @SerializedName("habit_id")
    int habitId;
    int daysBack;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public int getDaysBack(){ return daysBack;}

    public void setDaysBack(int daysBack){
        this.daysBack = daysBack;
    }

    public UserRecord(int habitId){
        this.habitId = habitId;
    }

    public  UserRecord(){
        this.userId = userId;
        this.habitId = habitId;
    }
    public UserRecord(int userId, int habitId, int daysBack){
        this.userId = userId;
        this.habitId = habitId;
        this.daysBack = daysBack;
    }
}
