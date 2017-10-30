package com.aquamorph.habquit.utils;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.aquamorph.habquit.activities.WidgetActivity;
import com.aquamorph.habquit.activities.WidgetConfigureActivity;

import static android.app.Activity.RESULT_OK;

/**
 * <p></p>
 *
 * @author Christian Colglazier
 * @version 4/18/2017
 */

public class ManageWidgetHabitListener implements View.OnClickListener {

	private Context context;
	private int habitID;
	private boolean isAddHabit;
	private String habitName;
	private int mAppWidgetId;

	public ManageWidgetHabitListener(Context context, int habitID, boolean isAddHabit, String
			habitName, int mAppWidgetId) {
		this.context = context;
		this.habitID = habitID;
		this.isAddHabit = isAddHabit;
		this.habitName = habitName;
		this.mAppWidgetId = mAppWidgetId;
	}

	// Write the prefix to the SharedPreferences object for this widget
	static void saveTitlePref(Context context, int appWidgetId, String text) {
		SharedPreferences.Editor prefs = context.getSharedPreferences(WidgetConfigureActivity
				.PREFS_NAME, 0).edit();
		prefs.putString(WidgetConfigureActivity.PREF_PREFIX_KEY + appWidgetId, text);
		prefs.apply();
	}

	@Override
	public void onClick(View v) {
		// When the button is clicked, store the string locally
		String widgetText = habitName;
		saveTitlePref(context, mAppWidgetId, widgetText);

		// It is the responsibility of the configuration activity to update the app widget
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		WidgetActivity.updateAppWidget(context, appWidgetManager, mAppWidgetId);

		// Make sure we pass back the original appWidgetId
		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		((Activity) context).setResult(RESULT_OK, resultValue);
		((Activity) context).finish();
	}
}