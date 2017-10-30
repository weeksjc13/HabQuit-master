package com.aquamorph.habquit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.MotivationAdapter;
import com.aquamorph.habquit.model.Motivation;
import com.aquamorph.habquit.provider.MotivationServiceProvider;
import com.aquamorph.habquit.service.MotivationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawn on 2/7/2017.
 */

public class MotivationActivity extends AppCompatActivity implements MotivationService.OnMotivationListener {
	private String TAG = "MotivationActivity";
	private RecyclerView recyclerView;
	private List<Motivation> motivations = new ArrayList<>();
	private MotivationAdapter motivationAdapter;

	@Override
	protected void onCreate(Bundle savedInstancestate) {
		super.onCreate(savedInstancestate);
		setContentView(R.layout.motivations);

		recyclerView = (RecyclerView) findViewById(R.id.motivations);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		motivationAdapter = new MotivationAdapter(null);
		recyclerView.setAdapter(motivationAdapter);
		getMotivation();
	}

	/**
	 * Calls achievementServiceProvider passing reference of self (implements OnMotivationListener)
	 * to communicate properly with service
	 */

	private void getMotivation() {
		MotivationServiceProvider motivationServiceProvider = new MotivationServiceProvider(this);
		motivationServiceProvider.getMotivations(this);
	}

	/**
	 * this function is called when the motivationServiceProvider successfully makes a service call
	 * to get a list of motivations from remote server (http status 200)
	 *
	 * @param motivation
	 */




	@Override
	public void onSuccess(List<Motivation> motivation) {

		motivationAdapter.setMotivations(motivation);
		motivationAdapter.notifyDataSetChanged();
	}

	@Override
	public void onError() {

	}
}