package com.zeddysoft.movieappusingrxjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Azeez.Taiwo on 8/27/2017.
 */


public class MovieListResponse {

    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
