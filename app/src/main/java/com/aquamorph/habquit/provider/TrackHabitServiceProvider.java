package com.aquamorph.habquit.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.aquamorph.habquit.model.UserRecord;
import com.aquamorph.habquit.model.TrackHabit;
import com.aquamorph.habquit.service.TrackHabitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shawn on 2/4/2017.
 */

public class TrackHabitServiceProvider {
    public static String TAG = "TrackHabitServiceProv";
    TrackHabitService trackHabitService;
    SharedPreferences preferences;
    /**
     * implementation of service is created here.
     * base url for service is defined here
     * basic service configuration is done here
     */

    public TrackHabitServiceProvider(Context context){
        trackHabitService = new Retrofit.Builder()
        .baseUrl("http://habquit.azurewebsites.net/")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(TrackHabitService.class);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * dispatches a GET request to remote server and informs listener of response.  If successful,
     * a List of Achievement is returned to listener.
     * @param listener
     */

    public void getTrackHabits (final TrackHabitService.OnTrackHabitListener listener){
        Call<List<TrackHabit>> call = trackHabitService.getTrackHabit();
        call.enqueue(new Callback<List<TrackHabit>>() {
            @Override
            public void onResponse(Call<List<TrackHabit>> call, Response<List<TrackHabit>> response) {
                List<TrackHabit> trackHabit = response.body();
                listener.onSuccess(trackHabit);
            }

            @Override
            public void onFailure(Call<List<TrackHabit>> call, Throwable t) {
                Log.d("Track Habit failure", t.getMessage());
                listener.onError();
            }
        });
    }

    public void postTrackHabit(UserRecord toPost){
        Call<TrackHabit> call = trackHabitService.postTrackHabit(toPost.getUserId(),toPost.getHabitId());
        call.enqueue(new Callback<TrackHabit>() {
            @Override
            public void onResponse(Call<TrackHabit> call, Response<TrackHabit> response) {
                TrackHabit trackResponse = response.body();
            }

            @Override
            public void onFailure(Call<TrackHabit> call, Throwable t) {
                String s = t.getMessage();
            }
        });
    }
    public void deleteTrackHabit(UserRecord toDelete){
        Call<TrackHabit> call = trackHabitService.deleteTrackHabit(toDelete);
        call.enqueue(new Callback<TrackHabit>(){
            @Override
            public void onResponse(Call<TrackHabit> call, Response<TrackHabit> response) {
                Log.d(TAG, "Success");
            }

            @Override
            public void onFailure(Call<TrackHabit> call, Throwable t) {
                Log.d("Shit",t.getMessage());
            }
        });

    }
}