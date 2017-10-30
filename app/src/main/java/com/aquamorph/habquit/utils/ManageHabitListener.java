package com.aquamorph.habquit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.aquamorph.habquit.activities.ManageHabitActivityTwo;

/**
 * Created by ryansummerlin on 3/9/17.
 */

public class ManageHabitListener implements View.OnClickListener {

    private Context context;
    private int habitID;
    private boolean isAddHabit;
    private String habitName;

    public ManageHabitListener(Context context, int habitID, boolean isAddHabit, String habitName) {
        this.context = context;
        this.habitID = habitID;
        this.isAddHabit = isAddHabit;
        this.habitName = habitName;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ManageHabitActivityTwo.class);
        intent.putExtra("HabitID", habitID);
        intent.putExtra("isAddHabit", isAddHabit);
        intent.putExtra("habitName", habitName);
        context.startActivity(intent);
	    ((Activity) context).finish();
    }
}
