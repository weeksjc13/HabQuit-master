package com.aquamorph.habquit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.model.Motivation;

import java.util.List;

/**
 * Created by Shawn on 2/7/2017.
 */

public class MotivationAdapter extends RecyclerView.Adapter<MotivationAdapter.ViewHolder> {

	private List<Motivation> motivations;

	public MotivationAdapter(List<Motivation> motivations) {
		this.motivations = motivations;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.row_motivation, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Motivation motivation = motivations.get(position);
		if (motivation != null) {
			holder.title.setText(motivation.getTitle());
			holder.description.setText(motivation.getDescription());
		}
	}

	@Override
	public int getItemCount() {
		return motivations != null ? motivations.size() : 0;
	}

	public static final class ViewHolder extends RecyclerView.ViewHolder {

		TextView title;
		TextView description;


		public ViewHolder(View v) {
			super(v);
			title = (TextView) v.findViewById(R.id.title);
			description = (TextView) v.findViewById(R.id.description);

		}

	}

	public void setMotivations(List<Motivation> motivations) {
		this.motivations = motivations;
	}
}

