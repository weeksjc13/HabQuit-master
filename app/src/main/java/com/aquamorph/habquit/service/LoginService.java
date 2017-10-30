package com.aquamorph.habquit.service;

import com.aquamorph.habquit.model.UserReg;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ryansummerlin on 3/23/17.
 */

public interface LoginService {

    @FormUrlEncoded
    @POST("/api/login")
    Call<UserReg> postLoginInfo(@Field("google_id") double googleID, @Field("user_name") String userName,
                                @Field("email") String email);
}
