package com.zeddysoft.movieappusingrxjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zeddysoft.movieappusingrxjava.R;
import com.zeddysoft.movieappusingrxjava.model.Movie;

import java.util.List;

/**
 * Created by azeez on 6/7/17.
 */

public class MovieAdapter extends BaseAdapter {

    private final LayoutInflater mLayoutInflater;
    List<Movie> movies;
    Context context;

    public MovieAdapter(Context context, List<Movie> movies){
        this.movies = movies;
        this.context= context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        if (convertView == null) {
            gridView = mLayoutInflater.inflate(
                    R.layout.image_post_item, parent, false);

        } else {
            gridView = convertView;
        }

        Movie movie = (Movie) getItem(position);
        ImageView imageView = (ImageView)gridView.findViewById(R.id.poster_item_view);
        Picasso.with(context).load(context.getString(R.string.movie_image_base_url)+movie.getPosterPath()).into(imageView);

        return gridView;
    }

    public void update(List<Movie> movies){
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }
}
