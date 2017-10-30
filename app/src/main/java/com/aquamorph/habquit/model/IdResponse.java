package com.aquamorph.habquit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Shawn on 2/4/2017.
 */

//generic response object to accept all post response with id only
public class IdResponse {
    @Expose
    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
