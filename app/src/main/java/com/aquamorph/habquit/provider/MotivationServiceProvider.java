package com.aquamorph.habquit.provider;

import android.content.Context;
import android.util.Log;

import com.aquamorph.habquit.model.Motivation;
import com.aquamorph.habquit.service.MotivationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shawn on 2/7/2017.
 */

public class MotivationServiceProvider {

	private MotivationService motivationService;
	Context context;

	/**
	 * implementation of service is created here.
	 * base url for service is defined here
	 * basic service configuration is done here
	 */

	public MotivationServiceProvider(Context context){
		motivationService = new Retrofit.Builder()
				.baseUrl("http://habquit.azurewebsites.net/")
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build().create(MotivationService.class);
        this.context = context;
	}
	/**
	 * dispatches a GET request to remote server and informs listener of response.  If successful,
	 * a List of Achievement is returned to listener.
	 * @param listener
	 */

	public void getMotivations (final MotivationService.OnMotivationListener listener) {
		Call<List<Motivation>> call = motivationService.getMotivation();

		call.enqueue(new Callback<List<Motivation>>() {
			@Override
			public void onResponse(Call<List<Motivation>> call, Response<List<Motivation>> response) {
				List<Motivation> motivation = response.body();
				listener.onSuccess(motivation);
			}

			@Override
			public void onFailure(Call<List<Motivation>> call, Throwable t) {
				Log.d("Motivational Fail", t.getMessage());
				listener.onError();
			}
		});
	}
}
