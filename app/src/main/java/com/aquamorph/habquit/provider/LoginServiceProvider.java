package com.aquamorph.habquit.provider;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.aquamorph.habquit.model.UserReg;
import com.aquamorph.habquit.service.LoginService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shawn on 2/4/2017.
 */
public class LoginServiceProvider {
	private LoginService loginService;
	Context context;

	private static final String TAG = "LoginServiceProvider";

	public Integer getUserId() {
		return 1;//this is shawn for now
	}


	public LoginServiceProvider(Context context) {
		loginService = new Retrofit.Builder()
				.baseUrl("http://habquit.azurewebsites.net/")
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build().create(LoginService.class);
		this.context = context;
	}

	public void postLoginInfo(double googleID, String userName, String email) {
		Call<UserReg> call = loginService.postLoginInfo(googleID, userName, email);
		call.enqueue(new Callback<UserReg>() {
			@Override
			public void onResponse(Call<UserReg> call, Response<UserReg> response) {
				Log.i(TAG, call.request().body().toString());
				if (response.isSuccessful()) {
					Log.i(TAG, "OK Response Code!");
					PreferenceManager.getDefaultSharedPreferences(context).edit()
							.putInt("userID", response.body().getUserId()).
							apply();
				} else {
					Log.i(TAG, "Bad Response Code!");
				}
			}

			@Override
			public void onFailure(Call<UserReg> call, Throwable t) {
				Log.i(TAG, "Failed to call to REST API successfully");
			}
		});
	}
}
