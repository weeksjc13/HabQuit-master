package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shawn on 2/3/2017.
 */

public class AchieveList {
    @Expose
    @SerializedName("achievement_id")
    private int achievementId;
    @Expose
    @SerializedName("habit_id")
    private int habitId;
    @Expose
    @SerializedName("points")
    private int points;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("condition")
    private String condition;
    @Expose
    @SerializedName("habit")
    private Habit habit;
    @Expose
    @SerializedName("user_achievements")
    private List<UserAchievement> userAchievements;

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public List<UserAchievement> getUserAchievements() {
        return userAchievements;
    }

    public void setUserAchievements(List<UserAchievement> userAchievements) {
        this.userAchievements = userAchievements;
    }
}
