package com.aquamorph.habquit.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ryankurdewan on 2/11/17.
 */
public class Counter {
    private static Counter ourInstance = new Counter();
    private int count;
    private Map<String,Integer> habitMap;

    public static Counter getInstance() {
        return ourInstance;
    }

    private Counter() {
        habitMap = new HashMap<>();
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountFor(String habit){
        if(habitMap.containsKey(habit)){
            return habitMap.get(habit);
        }
        return 0;
    }

    public void setCountFor(String habit, Integer count){
        habitMap.put(habit,count);
    }
}
