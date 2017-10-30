package com.aquamorph.habquit.provider;

import com.aquamorph.habquit.model.DailyCountHabit;
import com.aquamorph.habquit.service.DailyHabitCountService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shawnkelly on 3/26/17.
 */

public class DailyHabitCountServiceProvider {

    private DailyHabitCountService dailyHabitCountService;
    private int id;
    private int habitId;
    private int daysBack;

	public DailyHabitCountServiceProvider(int id, int habitId, int daysBack) {
		dailyHabitCountService = new Retrofit.Builder()
				.baseUrl("http://habquit.azurewebsites.net/")
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build().create(DailyHabitCountService.class);
		this.id = id;
		this.habitId = habitId;
		this.daysBack = daysBack;
	}

    public DailyHabitCountService getDailyHabitCountService() {
        return dailyHabitCountService;
    }

    public void setDailyHabitCountService(DailyHabitCountService dailyHabitCountService) {
        this.dailyHabitCountService = dailyHabitCountService;
    }

    public int getUserId() {
        return id;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public int getDaysBack() {
        return daysBack;
    }

    public void setDaysBack(int daysBack) {
        this.daysBack = daysBack;
    }


    public void getDailyHabitCounts(final DailyHabitCountService.OnDailyHabitCountListener listener) {
        Call<DailyCountHabit> call = dailyHabitCountService.getDailyCounts(id, habitId, daysBack);
        call.enqueue(new Callback<DailyCountHabit>() {
            @Override
            public void onResponse(Call<DailyCountHabit> call, Response<DailyCountHabit> response) {
                DailyCountHabit dailyCounts = response.body();
                listener.onSuccess(dailyCounts);
            }

            @Override
            public void onFailure(Call<DailyCountHabit> call, Throwable t) {

            }
        });
    }
}
