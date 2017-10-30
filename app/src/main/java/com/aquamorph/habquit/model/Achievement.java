package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by shawnkelly on 1/20/17.
 */

public class Achievement {
    @Expose
    @SerializedName("user_name")
    private String userName;
    @Expose
    @SerializedName("achieve_title")
    private String achievementTitle;
    @Expose
    @SerializedName("points")
    private int points;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("achieved_time")
    private String achievedTime;
    @Expose
    @SerializedName("achieve_id")
    private int achieveId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAchievementTitle() {
        return achievementTitle;
    }

    public void setAchievementTitle(String achievementTitle) {
        this.achievementTitle = achievementTitle;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAchievedTime() {
        return achievedTime;
    }

    public void setAchievedTime(String achievedTime) {
        this.achievedTime = achievedTime;
    }

    public int getAchieveId() {
        return achieveId;
    }

    public void setAchieveId(int achieveId) {
        this.achieveId = achieveId;
    }
}
