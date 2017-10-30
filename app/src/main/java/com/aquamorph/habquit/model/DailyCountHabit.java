package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shawnkelly on 3/26/17.
 */

public class DailyCountHabit {
    @Expose
    @SerializedName("counts")
     private List<DailyCounts> counts;

    public List<DailyCounts> getCounts() {
        return counts;
    }

    public void setCounts(List<DailyCounts> counts) {
        this.counts = counts;
    }
}
