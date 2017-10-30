package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Shawn on 2/3/2017.
 */

public class Motivation {
    @Expose
    @SerializedName("motivation_id")
    private int motivationId;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("habit_id")
    private int habitId;
    @Expose
    @SerializedName("type_id")
    private int typeId;
    @Expose
    @SerializedName("habit")
    private Habit habit;

    public int getMotivationId() {
        return motivationId;
    }

    public void setMotivationId(int motivationId) {
        this.motivationId = motivationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
