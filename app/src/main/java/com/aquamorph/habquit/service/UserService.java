package com.aquamorph.habquit.service;



import com.aquamorph.habquit.model.UserReg;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Shawn on 1/21/2017.
 */

public interface UserService {

    /**
     * this maps a service implementation (created in the constructor of UserServiceProvider)
     * to the correct endpoint on a remote server
     * @return
     */
    @GET ("/api/user_reg/{id}")
    Call<UserReg> getUserReg(@Path("id") int id);

    /**
     * this is an interface designed to allow communication between activity and service provider
     * it is used to promote loose coupling and reuse of services.  AchievemntActivity implents this
     * so UserServiceProvider knows what properties are available
     */
    interface OnAchievementListener {
        void onSuccess(UserReg userReg);
        void onError();
    }
}
