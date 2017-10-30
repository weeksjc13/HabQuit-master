package com.aquamorph.habquit.service;

import com.aquamorph.habquit.model.StatAvg;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shawnkelly on 3/25/17.
 */

public interface StatAvgService {

    /**
     * this maps a service implementation (created in the constructor of StatAvgServiceProvider)
     * to the correct endpoint on a remote server
     * @return
     */

    @GET("STATS/PERDAY/{id}/{habit_id}/{days_back}")
    Call<StatAvg> getStatAvg(@Path("id") int id, @Path("habit_id") int habitId, @Path("days_back") int daysback);


    interface OnStatAvgListener {
        void onSuccess(StatAvg statAvg);
        void onError();
    }
}
