package com.aquamorph.habquit.provider;

import com.aquamorph.habquit.model.StatAvg;
import com.aquamorph.habquit.service.StatAvgService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shawnkelly on 3/25/17.
 */

public class StatAvgServiceProvider {

	StatAvgService statAvgService;
	int id;
	int habitId;
	int daysback;

	public StatAvgServiceProvider(int id, int habitId, int daysback) {
		statAvgService = new Retrofit.Builder()
				.baseUrl("http://habquit.azurewebsites.net/")
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build().create(StatAvgService.class);
	}

	private int getDaysBack() {
		return daysback;
	}

	private int getHabitId() {
		return habitId;
	}

	private int getUserId() {
		return id;
	}

	public void getStatAvg(final StatAvgService.OnStatAvgListener listener) {
		Call<StatAvg> call = statAvgService.getStatAvg(id, habitId, daysback);
		String str = "";
		call.enqueue(new Callback<StatAvg>() {
			@Override
			public void onResponse(Call<StatAvg> call, Response<StatAvg> response) {
				StatAvg statAvg = response.body();
				listener.onSuccess(statAvg);
			}

			@Override
			public void onFailure(Call<StatAvg> call, Throwable t) {

			}
		});
	}
}
