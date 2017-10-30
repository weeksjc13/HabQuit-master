package com.aquamorph.habquit.activities;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.WidgetHabitAdapter;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.provider.HabitServiceProvider;
import com.aquamorph.habquit.service.HabitService;
import com.aquamorph.habquit.utils.HabitParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * The configuration screen for the {@link WidgetActivity widget} AppWidget.
 */
public class WidgetConfigureActivity extends Activity implements HabitService.OnHabitListener {

	public static final String PREFS_NAME = "layout.widget";
	public static final String PREF_PREFIX_KEY = "appwidget_";
	int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	EditText mAppWidgetText;

	private RecyclerView recyclerView;
	private boolean isAddHabit = false;

//	View.OnClickListener mOnClickListener = new View.OnClickListener() {
//		public void onClick(View v) {
//			final Context context = WidgetConfigureActivity.this;
//
//			// Toast.makeText(context, "HelloWidgetConfig.onClick(): " + String.valueOf(mAppWidgetId) , Toast.LENGTH_LONG).show();
//
//			// When the button is clicked, store the string locally
//			String widgetText = mAppWidgetText.getText().toString();
//			saveTitlePref(context, mAppWidgetId, widgetText);
//
//			// It is the responsibility of the configuration activity to update the app widget
//			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//			WidgetActivity.updateAppWidget(context, appWidgetManager, mAppWidgetId);
//
//			// Make sure we pass back the original appWidgetId
//			Intent resultValue = new Intent();
//			resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
//			setResult(RESULT_OK, resultValue);
//			finish();
//		}
//	};

	public WidgetConfigureActivity() {
		super();
	}



	// Read the prefix from the SharedPreferences object for this widget.
	// If there is no preference saved, get the default from a resource
	static String loadTitlePref(Context context, int appWidgetId) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		String titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
		if (titleValue != null) {
			return titleValue;
		} else {
			return context.getString(R.string.app_widget_title);
		}
	}

	static void deleteTitlePref(Context context, int appWidgetId) {
		SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
		prefs.remove(PREF_PREFIX_KEY + appWidgetId);
		prefs.apply();
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Set the result to CANCELED.  This will cause the widget host to cancel
		// out of the widget placement if the user presses the back button.
		setResult(RESULT_CANCELED);

		setContentView(R.layout.manage_habit_page_one);

		recyclerView = (RecyclerView) findViewById(R.id.manage_recycler_view);
		getHabits();
//        mAppWidgetText = (EditText) findViewById(R.id.app_widget_title);
//        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);

		// Find the widget id from the intent.
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(
					AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

//        mAppWidgetText.setText(loadTitlePref(WidgetConfigureActivity.this, mAppWidgetId));
	}

	private void getHabits() {
		HabitServiceProvider provider = new HabitServiceProvider();
		provider.getHabits(this);
	}

	@Override
	public void onSuccess(List<Habit> habits) {
		List<Habit> currentlyTracked = new ArrayList<>();
		HabitParameter habitParameter = HabitParameter.getInstance();

		for (Habit habit : habits) {
			if (habitParameter.getHabitIds().contains(habit.getHabitId()) != isAddHabit)
				currentlyTracked.add(habit);
		}

		WidgetHabitAdapter adapter = new WidgetHabitAdapter(currentlyTracked,
				PreferenceManager.getDefaultSharedPreferences(getApplicationContext()),
				isAddHabit, mAppWidgetId);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView
				.getContext(), linearLayoutManager.getOrientation());
		recyclerView.addItemDecoration(dividerItemDecoration);
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void onError() {

	}
}

