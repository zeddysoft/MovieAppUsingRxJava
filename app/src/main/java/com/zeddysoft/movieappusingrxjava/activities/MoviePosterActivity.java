package com.zeddysoft.movieappusingrxjava.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.zeddysoft.movieappusingrxjava.R;
import com.zeddysoft.movieappusingrxjava.adapters.MovieAdapter;
import com.zeddysoft.movieappusingrxjava.model.Movie;
import com.zeddysoft.movieappusingrxjava.model.MovieListResponse;
import com.zeddysoft.movieappusingrxjava.network.MovieClient;
import com.zeddysoft.movieappusingrxjava.util.MovieFilter;
import com.zeddysoft.movieappusingrxjava.util.NetworkUtils;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MoviePosterActivity extends AppCompatActivity {

    private GridView moviePosterView;
    private List<Movie> movies;
    private ProgressBar progressBar;
    private AlertDialog alertDialog;
    private Subscription subscription;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviePosterView = (GridView) findViewById(R.id.movie_posters_view);
        moviePosterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie selectedMovie = movies.get(position);

                Intent intent = new Intent(MoviePosterActivity.this, MovieDetailActivity.class);
                intent.putExtra(getString(R.string.movie_intent_key), selectedMovie);

                startActivity(intent);
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (!isPhoneConnectedToInternet()) {
            showMessage(R.string.no_network_message);
        } else {
            if (savedInstanceState != null) {
                if (savedInstanceState.containsKey(getString(R.string.movie_poster_data_key))) {
                    movies = savedInstanceState.getParcelableArrayList(getString(R.string.movie_poster_data_key));
                    showMovies();
                }
            } else {
                showMovies(MovieFilter.POPULAR);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_poster_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_most_popular:
                showMovies(MovieFilter.POPULAR);
                return true;
            case R.id.sort_by_highest_rated:
                showMovies(MovieFilter.HIGH_RATED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isPhoneConnectedToInternet() {
        return NetworkUtils.isPhoneConnectedToInternet();
    }

    public void showMovies() {
        movieAdapter = new MovieAdapter(this, movies);
        moviePosterView.setAdapter(movieAdapter);
    }

    private void showMovies(@MovieFilter.movieFilter int filter) {
        progressBar.setVisibility(View.VISIBLE);
        subscription = MovieClient.getInstance()
                .getMovies(filter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieListResponse>() {
                    @Override
                    public void onCompleted() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(MovieListResponse movieListResponse) {
                        movies = movieListResponse.getMovies();
                        showMovies();
                    }
                });
    }


    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    private void showMessage(int messageId) {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(getString(messageId));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
