package com.aquamorph.habquit.adapter;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.utils.HabitIcon;
import com.aquamorph.habquit.utils.ManageWidgetHabitListener;

import java.util.List;

/**
 * <p></p>
 *
 * @author Christian Colglazier
 * @version 4/18/2017
 */

public class WidgetHabitAdapter extends RecyclerView.Adapter<ManageHabitsAdapter.ManageHabitViewHolder> {

	private List<Habit> habitsToManage;
	private SharedPreferences preferences;
	private boolean isAddHabit;
	private int mAppWidgetId;

	// Creation of this class assumes the list passed is not null!
	public WidgetHabitAdapter(List<Habit> habitsToManage, SharedPreferences preferences,
	                           boolean isAddHabit, int mAppWidgetId) {
		if (habitsToManage == null) {
			System.err.println("Passed invalid parameter to constructor of ManageHabitsAdapter. " +
					"The parameter should be a non-null list.");
			System.exit(1);
		}
		this.isAddHabit = isAddHabit;
		this.preferences = preferences;
		this.habitsToManage = habitsToManage;
		this.mAppWidgetId = mAppWidgetId;
	}

	@Override
	public ManageHabitsAdapter.ManageHabitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_habit_item,
				parent, false);
		return new ManageHabitsAdapter.ManageHabitViewHolder(view, parent);
	}

	@Override
	public void onBindViewHolder(ManageHabitsAdapter.ManageHabitViewHolder holder, int position) {
		Habit habit = habitsToManage.get(position);
		ManageWidgetHabitListener listener = new ManageWidgetHabitListener(holder.parent
				.getContext(),
				habit.getHabitId(), isAddHabit, habit.getType(), mAppWidgetId);
		holder.habitName.setText(preferences.getString("habitName" + habit.getHabitId(),
				habit.getType()));
		HabitIcon.setIcon(habit, holder.habitIcon);
		holder.habitItem.setOnClickListener(listener);
	}

	@Override
	public int getItemCount() {
		return habitsToManage.size();
	}

	static class ManageHabitViewHolder extends RecyclerView.ViewHolder {
		TextView habitName;
		ImageView habitIcon;
		ViewGroup habitItem;
		ViewGroup parent;

		ManageHabitViewHolder(View itemView, ViewGroup parent) {
			super(itemView);
			habitName = (TextView) itemView.findViewById(R.id.manage_habit_name);
			habitIcon = (ImageView) itemView.findViewById(R.id.habitIcon);
			habitItem = (ViewGroup) itemView.findViewById(R.id.habitItem);
			this.parent = parent;
		}
	}
}
