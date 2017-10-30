package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.BottomNavPagerAdapter;
import com.aquamorph.habquit.fragments.AssistantFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

	private String TAG = "MainActivity";
	private ViewPager viewPager;
	private GoogleApiClient googleApiClient;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		BottomNavigationView bottomNavigationView = (BottomNavigationView)
				findViewById(R.id.bottom_navigation);

		sharedPreferences = PreferenceManager.getDefaultSharedPreferences
				(getApplicationContext());
		GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions
				.DEFAULT_SIGN_IN).requestEmail().build();
		googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi
				(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

		// Creates fragments and allows switching between them
		viewPager = (ViewPager) findViewById(R.id.fragment);
		BottomNavPagerAdapter bottomNavPagerAdapter =
				new BottomNavPagerAdapter(getSupportFragmentManager());
		viewPager.setOffscreenPageLimit(bottomNavPagerAdapter.getCount());
		viewPager.setAdapter(bottomNavPagerAdapter);
		// Handles bottom navigation click
		bottomNavigationView.setOnNavigationItemSelectedListener(
				new BottomNavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(@NonNull MenuItem item) {
						switch (item.getItemId()) {
							case R.id.habits_bottom_menu:
								viewPager.setCurrentItem(0);
								break;
							case R.id.charts_bottom_menu:
								viewPager.setCurrentItem(1);
								break;
							case R.id.savings_bottom_menu:
								viewPager.setCurrentItem(2);
								break;
						}
						return true;
					}
				}

		);
		AssistantFragment.sendMessage("Hello, worthless addict " +
				sharedPreferences.getString("firstName", "Human") + "!");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_drop_down_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.logout:
				Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
					@Override
					public void onResult(@NonNull Status status) {
						Intent i = getBaseContext().getPackageManager()
								.getLaunchIntentForPackage(getBaseContext().getPackageName());
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
						finish();
					}
				});
				break;
			case R.id.achievements:
				openAchievement();
				break;
			case R.id.motivations:
				openMotivation();
				break;
			case R.id.graphDisplay:
				openGraphDisplay();
				break;
			case R.id.add_habit:
				openAddHabitActivity();
				break;
			case R.id.manage_habit:
				openManageHabitActivityTwo();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Takes user to achievement page
	 */
	public void openAchievement() {
		startActivity(new Intent(this, AchievementsActivity.class));
	}

	/**
	 * Takes user to motivation page
	 */
	public void openMotivation() {
		startActivity(new Intent(this, MotivationActivity.class));
	}

	public void openGraphDisplay() {
		startActivity(new Intent(this, GraphDisplayActivity.class));
	}

	/**
	 * Starts activity to manage a habit
	 */
	public void openManageHabitActivityTwo() {
		Intent intent = new Intent(this, ManageHabitActivityOne.class);
		intent.putExtra("isAddHabit", false);
		startActivity(intent);
	}

	/**
	 * Starts activity to add a habit
	 */
	public void openAddHabitActivity() {
		Intent intent = new Intent(this, ManageHabitActivityOne.class);
		intent.putExtra("isAddHabit", true);
		startActivity(intent);
	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}
}
