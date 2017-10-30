package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Shawn on 2/3/2017.
 */

public class UserReg {
    @Expose
    @SerializedName("user_id")
    private int userId;
    @Expose
    @SerializedName("user_name")
    private String userName;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("phone_number")
    private String phoneNumber;
    @Expose
    @SerializedName("date_of_birth")
    private String dateOfBirth;
    @Expose
    @SerializedName("city")
    private String city;
    @Expose
    @SerializedName("state")
    private String state;
    @Expose
    @SerializedName("user_achievements")
    private List<UserAchievement> userAchievements;
    @Expose
    @SerializedName("track_habits")
    private List<TrackHabit> trackHabits;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<UserAchievement> getUserAchievements() {
        return userAchievements;
    }

    public void setUserAchievements(List<UserAchievement> userAchievements) {
        this.userAchievements = userAchievements;
    }

    public List<TrackHabit> getTrackHabits() {
        return trackHabits;
    }

    public void setTrackHabits(List<TrackHabit> trackHabits) {
        this.trackHabits = trackHabits;
    }
}
