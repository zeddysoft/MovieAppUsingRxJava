package com.zeddysoft.movieappusingrxjava.network;

import com.zeddysoft.movieappusingrxjava.model.MovieListResponse;
import com.zeddysoft.movieappusingrxjava.model.ReviewListResponse;
import com.zeddysoft.movieappusingrxjava.model.TrailerListResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Azeez.Taiwo on 8/22/2017.
 */

public interface MovieService {

    @GET("movie/popular")
    Observable<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieListResponse> getHighRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Observable<TrailerListResponse> getTrailers(@Path("id") long movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Observable<ReviewListResponse> getReviews(@Path("id") long movieId, @Query("api_key") String apiKey);
}
