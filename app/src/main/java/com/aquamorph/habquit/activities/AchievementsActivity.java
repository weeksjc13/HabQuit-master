package com.aquamorph.habquit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.adapter.AchievementAdapter;
import com.aquamorph.habquit.model.UserReg;
import com.aquamorph.habquit.provider.UserServiceProvider;
import com.aquamorph.habquit.service.UserService;

/**
 * Achievement class page for listing achievements won by user.
 *
 * Created by Shawn on 1/17/2017.
 */

public class AchievementsActivity extends AppCompatActivity implements UserService.OnAchievementListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.achievements);

        recyclerView = (RecyclerView) findViewById(R.id.achievements);
        getAchievement();
    }

    /**
     *   Calls achievementServiceProvider passing reference of self (implements OnAchievementListener)
     *   to communicate properly with service
     */
    private void getAchievement(){
        UserServiceProvider userServiceProvider = new UserServiceProvider(this);
        userServiceProvider.getUserRegs(this);
    }

    /**
     * this function is called when the achievementServiceProvider successfully makes a service call
     * to get a list of achievements from remote server (http status 200)
     * @param userReg user id
     */
    @Override
    public void onSuccess(UserReg userReg) {
        AchievementAdapter achievementAdapter = new AchievementAdapter(userReg.getUserAchievements());
        recyclerView.setAdapter(achievementAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onError() {}
}