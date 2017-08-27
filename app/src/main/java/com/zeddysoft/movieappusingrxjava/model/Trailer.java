package com.zeddysoft.movieappusingrxjava.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by azeez on 6/19/17.
 */

public class Trailer {

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}