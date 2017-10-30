package com.aquamorph.habquit.provider;

import android.content.Context;
import android.util.Log;


import com.aquamorph.habquit.model.UserReg;
import com.aquamorph.habquit.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shawn on 1/21/2017.
 */

public class UserServiceProvider {
    UserService achievementService;
    Context context;
    LoginServiceProvider provider = new LoginServiceProvider(this.context);
    /**
     * implementation of service is created here.
     * base url for service is defined here
     * basic service configuration is done here
     */
    public UserServiceProvider(Context context){
        achievementService = new Retrofit.Builder()
        .baseUrl("http://habquit.azurewebsites.net/")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(UserService.class);
        this.context = context;
    }

    /**
     * dispatches a GET request to remote server and informs listener of response.  If successful,
     * a List of Achievement is returned to listener.
     * @param listener
     */
    public void  getUserRegs (final UserService.OnAchievementListener listener){
        Call<UserReg> call = achievementService.getUserReg(provider.getUserId()); //THis is where we'll user id
        call.enqueue(new Callback<UserReg>() {
            @Override
            public void onResponse(Call<UserReg> call, Response<UserReg> response) {
                UserReg userReg = response.body();
                listener.onSuccess(userReg);
            }

            @Override
            public void onFailure(Call<UserReg> call, Throwable t) {
                Log.d("Shit",t.getMessage());
                listener.onError();
            }
        });
    }
}
