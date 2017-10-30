package com.aquamorph.habquit.provider;

import android.util.Log;

import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.service.HabitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shawn on 2/3/2017.
 */

public class HabitServiceProvider {
	private HabitService habitService;

	/**
	 * implementation of service is created here.
	 * base url for service is defined here
	 * basic service configuration is done here
	 */
	public HabitServiceProvider() {
		habitService = new Retrofit.Builder()
				.baseUrl("http://habquit.azurewebsites.net/")
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build().create(HabitService.class);
	}

	/**
	 * dispatches a GET request to remote server and informs listener of response.  If successful,
	 * a List of Achievement is returned to listener.
	 *
	 * @param listener
	 */
	public void getHabits(final HabitService.OnHabitListener listener) {
		Call<List<Habit>> call = habitService.getHabit();
		call.enqueue(new Callback<List<Habit>>() {
			@Override
			public void onResponse(Call<List<Habit>> call, Response<List<Habit>> response) {
				List<Habit> habits = response.body();
				listener.onSuccess(habits);
			}


			@Override
			public void onFailure(Call<List<Habit>> call, Throwable t) {
				Log.d("HabitService Fail", t.getMessage());
				listener.onError();
			}
		});
	}
}
