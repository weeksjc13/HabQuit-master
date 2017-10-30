package com.aquamorph.habquit.application;

import android.app.Application;

import com.aquamorph.habquit.utils.HabitParameter;

/**
 * Created by Shawn on 2/4/2017.
 */

public class HabitApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        //initialize shared prefs
        HabitParameter.getInstance().init(getApplicationContext());
    }
}
