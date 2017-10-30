package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shawnkelly on 3/26/17.
 */

public class DailyCounts {

    @Expose
    @SerializedName("count")
    int count;
    @Expose
    @SerializedName("date")
    String date;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
