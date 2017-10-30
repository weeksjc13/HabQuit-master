package com.aquamorph.habquit.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.fragments.AssistantFragment;
import com.aquamorph.habquit.model.UserRecord;
import com.aquamorph.habquit.model.Habit;
import com.aquamorph.habquit.provider.LoginServiceProvider;
import com.aquamorph.habquit.provider.TrackHabitServiceProvider;
import com.aquamorph.habquit.utils.Counter;
import com.aquamorph.habquit.utils.HabitIcon;

import java.util.List;

/**
 * Created by Shawn on 2/4/2017.
 */

public class SelectHabitsAdapter extends RecyclerView.Adapter<SelectHabitsAdapter.ViewHolder> {

	private List<Habit> habits;
	private SharedPreferences preferences;
	private Context context;
	private LoginServiceProvider provider;
	private UserRecord userInfo;

	public SelectHabitsAdapter(Context context, List<Habit> habits, SharedPreferences preferences) {
        this.context = context;
		this.habits = habits;
		this.preferences = preferences;
        this.provider = new LoginServiceProvider(this.context);
        this.userInfo = new UserRecord();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.row_habit_grid, parent, false);
		return new GridViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final Habit habit = habits.get(position);

		if (habit != null) {
			holder.habit = habit;

			final Counter counter = Counter.getInstance();
			final TrackHabitServiceProvider serviceProvider = new TrackHabitServiceProvider(this.context);
			final GridViewHolder gridViewHolder = (GridViewHolder) holder;
			gridViewHolder.habitName.setText(preferences.getString("habitName" +
					habit.getHabitId(), habit.getType()));

			HabitIcon.setIcon(habit, gridViewHolder.habitImage);

			gridViewHolder.habitCount.setText(String.valueOf(counter.getCountFor(habit.getType())));
			gridViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int count = counter.getCountFor(habit.getType());
					AssistantFragment.changeMood(-5.0);
					count++;
					userInfo.setHabitId(habit.getHabitId());
					userInfo.setUserId(preferences.getInt("userID", 0));
					counter.setCountFor(habit.getType(), count);
					serviceProvider.postTrackHabit(userInfo);
					SelectHabitsAdapter.this.notifyDataSetChanged();
				}
			});
			gridViewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					userInfo.setHabitId(habit.getHabitId());
					userInfo.setUserId(provider.getUserId());
					int count = counter.getCountFor(habit.getType());
					AssistantFragment.changeMood(5.0);
					count--;
					if (count < 0) count = 0;
					counter.setCountFor(habit.getType(), count);
					serviceProvider.deleteTrackHabit(userInfo);
					SelectHabitsAdapter.this.notifyDataSetChanged();
					return true;
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return habits != null ? habits.size() : 0;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {

		Habit habit;

		ViewHolder(View v) {
			super(v);
		}
	}

	public static class GridViewHolder extends ViewHolder {
		TextView habitName;
		ImageView habitImage;
		TextView habitCount;
		CardView cardView;

		GridViewHolder(View v) {
			super(v);
			habitName = (TextView) v.findViewById(R.id.habitName);
			habitImage = (ImageView) v.findViewById(R.id.habitIcon);
			habitCount = (TextView) v.findViewById(R.id.habitCount);
			cardView = (CardView) v.findViewById(R.id.habit_cardview);
		}
	}
}