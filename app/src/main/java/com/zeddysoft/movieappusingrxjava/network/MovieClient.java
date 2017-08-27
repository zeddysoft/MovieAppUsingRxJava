package com.zeddysoft.movieappusingrxjava.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeddysoft.movieappusingrxjava.App;
import com.zeddysoft.movieappusingrxjava.R;
import com.zeddysoft.movieappusingrxjava.model.MovieListResponse;
import com.zeddysoft.movieappusingrxjava.model.ReviewListResponse;
import com.zeddysoft.movieappusingrxjava.model.TrailerListResponse;
import com.zeddysoft.movieappusingrxjava.util.MovieFilter;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Azeez.Taiwo on 8/22/2017.
 */

public class MovieClient {

    private static MovieClient instance;
    private MovieService movieService;
    private String apiKey;

    private MovieClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(App.getContext().getString(R.string.base_url))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        movieService = retrofit.create(MovieService.class);
        apiKey = App.getContext().getString(R.string.api_key);
    }

    public static MovieClient getInstance() {
        if (instance == null) {
            instance = new MovieClient();
        }
        return instance;
    }

    public Observable<MovieListResponse> getMovies(MovieFilter filter) {
        if(filter == MovieFilter.HIGH_RATED){
            return movieService.getHighRatedMovies(apiKey);
        }
        return movieService.getPopularMovies(apiKey);
    }

    public Observable<TrailerListResponse> getTrailers(long movieId){
        return movieService.getTrailers(movieId, apiKey);
    }

    public Observable<ReviewListResponse> getReviews(long movieId){
        return movieService.getReviews(movieId, apiKey);
    }
}
