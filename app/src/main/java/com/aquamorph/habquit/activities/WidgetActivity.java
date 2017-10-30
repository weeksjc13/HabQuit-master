package com.aquamorph.habquit.activities;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.content.Intent;
import android.app.PendingIntent;

import com.aquamorph.habquit.R;


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link WidgetConfigureActivity WidgetConfigureActivity}
 */

//Widget activity added by JCW
public class WidgetActivity extends AppWidgetProvider {

    private static final String SYNC_CLICKED    = "AppWidgetManager.ACTION_APPWIDGET_UPDATE";
    public static int incCount = 0;

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Toast.makeText(context, "updateAppWidget(): widgetId = " + String.valueOf(appWidgetId) + "... Count : " +  String.valueOf(incCount), Toast.LENGTH_LONG).show();

        // set up remote view and set/update text views
        RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        updateViews.setTextViewText(R.id.widget_count, String.valueOf(incCount));

        // updateViews.setTextViewText(R.id.app_widget_title, "app widget id: [" + String.valueOf(appWidgetId) + "]");

        ComponentName thisWidget = new ComponentName(context, WidgetActivity.class);

        Intent intent = new Intent(context, WidgetActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("ID", appWidgetId);
        intent.setAction(SYNC_CLICKED);
        intent.putExtras(extras);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        updateViews.setOnClickPendingIntent(R.id.widget, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(thisWidget, updateViews);

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (SYNC_CLICKED.equals(intent.getAction())) {

            Bundle extras = intent.getExtras();

            if(extras!=null) {

                incCount++;

                int widgetId = extras.getInt("ID", 0);

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                //Toast.makeText(context, "onReceive(): widgetId = " + String.valueOf(widgetId) + "... Count : " +  String.valueOf(incCount), Toast.LENGTH_LONG).show();

                updateAppWidget(context, appWidgetManager, widgetId);

            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.

        for (int appWidgetId : appWidgetIds) {

            WidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Toast.makeText(context, "onEnabled()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDisabled(Context context) {
        // Toast.makeText(context, "onDisabled()", Toast.LENGTH_LONG).show();
    }


}

