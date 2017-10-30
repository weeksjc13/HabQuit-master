package com.aquamorph.habquit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aquamorph.habquit.R;

import com.aquamorph.habquit.model.UserAchievement;

import java.util.List;

/**
 * Created by shawnkelly on 1/20/17.
 */

//The adapter takes the data and binds it to the recyclerview.

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder>{

    private List<UserAchievement> userAchievements;

    public AchievementAdapter(List<UserAchievement> userAchievements){
        this.userAchievements = userAchievements;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_achievement,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserAchievement userAchievement = userAchievements.get(position);
        if(userAchievement != null){
            //load view holder
            holder.title.setText("TITLE");
            holder.points.setText(String.valueOf(userAchievement.getAchieveLists().getPoints()));
            holder.description.setText(userAchievement.getAchieveLists().getDescription());

        }
    }

    @Override
    public int getItemCount() {
        return userAchievements != null ? userAchievements.size() : 0;
    }


    public static final class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        TextView points;

        public ViewHolder(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            description = (TextView) v.findViewById(R.id.description);
            points = (TextView) v.findViewById(R.id.points);
        }
    }
}
