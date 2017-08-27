package com.zeddysoft.movieappusingrxjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Azeez.Taiwo on 8/27/2017.
 */

public class TrailerListResponse {

    @SerializedName("results")
    private List<Trailer> trailers;

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }
}
