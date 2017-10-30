package com.aquamorph.habquit.service;

import com.aquamorph.habquit.model.Habit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Shawn on 2/3/2017.
 */

public interface HabitService {

    /**
     * this maps a service implementation (created in the constructor of HabitServiceProvider)
     * to the correct endpoint on a remote server
     * @return
     */
    @GET ("/api/habits/")
    Call<List<Habit>> getHabit();


    /**
     * this is an interface designed to allow communication between activity and service provider
     * it is used to promote loose coupling and reuse of services.  AchievemntActivity implents this
     * so HabitServiceProvider knows what properties are available
     */
    interface OnHabitListener {
        void onSuccess(List<Habit> habits);
        void onError();
    }
}
