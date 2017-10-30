package com.aquamorph.habquit.service;

import com.aquamorph.habquit.model.UserRecord;
import com.aquamorph.habquit.model.TrackHabit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

/**
 * Created by Shawn on 2/4/2017.
 */

public interface TrackHabitService {

    /**
     * this maps a service implementation (created in the constructor of TrackHabitServiceProvider)
     * to the correct endpoint on a remote server
     * @return
     */

    @GET("/api/track_habits")
    Call<List<TrackHabit>> getTrackHabit();

    @FormUrlEncoded
    @POST("api/track_habits")
    Call<TrackHabit> postTrackHabit(@Field("user_id") int userId, @Field("habit_id") int habitId);
    /**
     * this is an interface designed to allow communication between activity and service provider
     * it is used to promote loose coupling and reuse of services.  TrackHabitActivity implents this
     * so TrackHabitServiceProvider knows what properties are available
     */

    //@FormUrlEncoded
    @HTTP(method = "DELETE", path = "api/track_habits", hasBody = true)
    Call<TrackHabit> deleteTrackHabit(@Body UserRecord toDelete);

    interface OnTrackHabitListener {
        void onSuccess(List<TrackHabit> trackHabits);
        void onError();
    }

}