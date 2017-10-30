package com.aquamorph.habquit.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Shawn on 2/4/2017.
 */
public class HabitParameter {
    private static HabitParameter ourInstance = new HabitParameter();

    private static final String HABITS_PREFS = "habitPreferences";
    private static final String HABITS = "habits";

    public static final int SMOKING_ID = 1;
    public static final int SMOKELESS_ID = 2;
    public static final int DRINKING_ID = 3;
    public static final int SODA_ID = 4;

    public static final int CUSTOM_ID = -1;

    private SharedPreferences sharedPreferences;

    public static HabitParameter getInstance() {
        return ourInstance;
    }



    private HabitParameter() {

    }

    public void init(Context context){
        sharedPreferences = context.getSharedPreferences(HABITS_PREFS,Context.MODE_PRIVATE);
    }

    public void addHabit(int habitId){
        Set<String> habits = sharedPreferences.getStringSet(HABITS, new HashSet<String>());
        Set<String> in = new HashSet<>(habits);
        in.add(String.valueOf(habitId));
        sharedPreferences.edit().putStringSet(HABITS,in).apply();
    }

    public void removeHabit(int habitId){
        Set<String> habits = sharedPreferences.getStringSet(HABITS, new HashSet<String>());
        Set<String> in = new HashSet<>(habits);
        in.remove(String.valueOf(habitId));
        sharedPreferences.edit().putStringSet(HABITS,in).apply();
    }

    public List<Integer> getHabitIds(){
        List<Integer> ids = new ArrayList<>();
        Set<String> habits = sharedPreferences.getStringSet(HABITS, new HashSet<String>());
        for(String habit : habits){
            ids.add(Integer.parseInt(habit));
        }
        return ids;
    }
}
