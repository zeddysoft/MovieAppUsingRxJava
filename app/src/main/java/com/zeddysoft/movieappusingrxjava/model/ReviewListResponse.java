package com.zeddysoft.movieappusingrxjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Azeez.Taiwo on 8/27/2017.
 */

public class ReviewListResponse {

    @SerializedName("results")
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
