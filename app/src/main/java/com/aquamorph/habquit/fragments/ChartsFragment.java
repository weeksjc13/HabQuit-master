package com.aquamorph.habquit.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.DailyCountHabit;
import com.aquamorph.habquit.model.DailyCounts;
import com.aquamorph.habquit.model.StatAvg;
import com.aquamorph.habquit.provider.DailyHabitCountServiceProvider;
import com.aquamorph.habquit.provider.StatAvgServiceProvider;
import com.aquamorph.habquit.service.DailyHabitCountService;
import com.aquamorph.habquit.service.StatAvgService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * <p></p>
 *
 * @author Josh Derr
 * @version 2/2/2017
 */

public class ChartsFragment extends Fragment implements StatAvgService.OnStatAvgListener, DailyHabitCountService.OnDailyHabitCountListener {

	private BarChart barChart;
	private SharedPreferences sharedPreferences;

	@Override

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bar_graph, container, false);
		barChart = (BarChart) view.findViewById(R.id.bar_graph);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

		getStatAvg();
		getDailyHabitCounts();
		return view;
	}

	public void getStatAvg() {
		StatAvgServiceProvider statAvgServiceProvider = new StatAvgServiceProvider
				(sharedPreferences.getInt("userID", 0), 1, 30);
		statAvgServiceProvider.getStatAvg(this);
	}

	public void getDailyHabitCounts() {
		DailyHabitCountServiceProvider dailyHabitCountServiceProvider = new
				DailyHabitCountServiceProvider(sharedPreferences.getInt("userID", 0), 1, 30);
		dailyHabitCountServiceProvider.getDailyHabitCounts(this);

	}


	@Override
	public void onSuccess(StatAvg statAvg) {
	}

	@Override
	public void onSuccess(DailyCountHabit dailycounts) {
		ArrayList<BarEntry> barEntries = new ArrayList<>();
		if (dailycounts.getCounts() != null) {
			for (int i = 0; i < dailycounts.getCounts().size(); i++) {
				DailyCounts dc = dailycounts.getCounts().get(i);
				barEntries.add(new BarEntry((i + 1) / 1.0f, dc.getCount()));
			}
			BarDataSet barDataSet = new BarDataSet(barEntries, "Cigarettes This Week");
			BarData theData = new BarData(barDataSet);
			barChart.setData(theData);
		}

	}

	@Override
	public void onError() {

	}
}
