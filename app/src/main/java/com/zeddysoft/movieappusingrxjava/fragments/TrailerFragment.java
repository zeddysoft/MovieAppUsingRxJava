package com.zeddysoft.movieappusingrxjava.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.zeddysoft.movieappusingrxjava.R;
import com.zeddysoft.movieappusingrxjava.adapters.TrailerAdapter;
import com.zeddysoft.movieappusingrxjava.model.Trailer;
import com.zeddysoft.movieappusingrxjava.model.TrailerListResponse;
import com.zeddysoft.movieappusingrxjava.network.MovieClient;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrailerFragment extends Fragment implements TrailerAdapter.TrailerPlayListener {


    private RecyclerView trailersView;
    private ProgressBar loadingBar;
    private List<Trailer> trailers;
    private Subscription subscription;

    public TrailerFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailer, container, false);

        long movieId = getArguments().getLong(getString(R.string.trailer_data_key));
        trailersView = (RecyclerView) view.findViewById(R.id.trailers_RV);
        loadingBar = (ProgressBar) view.findViewById(R.id.loadingBar);
        fetchMovieTrailers(movieId);

        return view;
    }

    private void fetchMovieTrailers(long id) {

        subscription = MovieClient.getInstance()
                .getTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrailerListResponse>() {
                    @Override
                    public void onCompleted() {
                        loadingBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(TrailerListResponse trailerListResponse) {
                        trailers = trailerListResponse.getTrailers();
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        trailersView.setLayoutManager(mLayoutManager);
                        trailersView.setItemAnimator(new DefaultItemAnimator());
                        trailersView.setAdapter(new TrailerAdapter( TrailerFragment.this.getActivity(),trailers,TrailerFragment.this));
                    }
                });
    }

    @Override
    public void onTrailerClicked(Trailer trailer) {
        viewAndPlayTrailer(trailer);
    }

    private void viewAndPlayTrailer(Trailer trailer) {
        String url = "https://www.youtube.com/watch?v=" + trailer.getKey();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    @Override
    public void onDestroyView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroyView();
    }
}
