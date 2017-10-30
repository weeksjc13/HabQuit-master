package com.aquamorph.habquit.utils;

import android.widget.ImageView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.Habit;

/**
 * Manages setting habit icon
 *
 * @author Christian Colglazier
 * @version 3/20/2017
 */

public class HabitIcon {
	public static void setIcon(Habit habit, ImageView view) {
		switch (habit.getHabitId()) {
			case 1:
				view.setImageResource(R.drawable.ic_smoking);
				break;
			case 2:
				view.setImageResource(R.drawable.ic_smoke_free_tobacco);
				break;
			case 3:
				view.setImageResource(R.drawable.ic_booze);
				break;
			case 4:
				view.setImageResource(R.drawable.ic_soda);
				break;
			default:
				view.setImageResource(R.drawable.ic_star);
				break;
		}
	}
}
