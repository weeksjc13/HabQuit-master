package com.aquamorph.habquit.activities;

import android.os.Bundle;

import com.aquamorph.habquit.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

//
//Bar Graph creates a bar graph using user data from DB
//***NOTE*** DB CONNECTION HAS NOT BEEN SETUP***
//

public class BarGraphActivity extends GraphDisplayActivity {
	BarChart barChart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bar_graph);

		barChart = (BarChart) findViewById(R.id.bar_graph);

		ArrayList<BarEntry> barEntries = new ArrayList<>();
		barEntries.add(new BarEntry(1f, 4));
		barEntries.add(new BarEntry(2f, 6));
		barEntries.add(new BarEntry(3f, 8));
		barEntries.add(new BarEntry(4f, 2));
		barEntries.add(new BarEntry(5f, 1));
		barEntries.add(new BarEntry(6f, 3));
		barEntries.add(new BarEntry(7f, 5));

		BarDataSet barDataSet = new BarDataSet(barEntries, "Faps per day");


		BarData theData = new BarData(barDataSet);
		barChart.setData(theData);
		barChart.setDragEnabled(true);
		barChart.setTouchEnabled(true);
		barChart.setScaleEnabled(true);
		barChart.animateXY(2000, 3420);
	}
}
