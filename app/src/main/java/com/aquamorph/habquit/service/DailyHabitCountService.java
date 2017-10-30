package com.aquamorph.habquit.service;

import com.aquamorph.habquit.model.DailyCountHabit;
import com.aquamorph.habquit.model.DailyCounts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shawnkelly on 3/26/17.
 */

public interface DailyHabitCountService {


    @GET("TRACK/ME/{id}/{habit_id}/{days_back}")
    Call<DailyCountHabit> getDailyCounts(@Path("id") int id, @Path("habit_id") int habitId, @Path("days_back") int daysBack);


    interface OnDailyHabitCountListener {
        void onSuccess(DailyCountHabit dailycounts);
        void onError();
    }

}
