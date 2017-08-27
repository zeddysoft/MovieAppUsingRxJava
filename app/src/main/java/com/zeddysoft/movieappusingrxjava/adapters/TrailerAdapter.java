package com.zeddysoft.movieappusingrxjava.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zeddysoft.movieappusingrxjava.R;
import com.zeddysoft.movieappusingrxjava.model.Trailer;

import java.util.List;

/**
 * Created by azeez on 6/19/17.
 */

public class TrailerAdapter  extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    List<Trailer> trailers;
    TrailerPlayListener listener;
    Context context;

    public TrailerAdapter(Context context,List<Trailer> trailers, TrailerPlayListener listener){
        this.trailers = trailers;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trailer_item, parent, false);

        return new TrailerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        String thumbnail_url = "https://img.youtube.com/vi/" + (trailer.getKey())+ "/mqdefault.jpg";
        Picasso.with(context).load(thumbnail_url).into(holder.trailerThumbnail);
        holder.trailerTitle.setText(trailer.getName());

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView trailerTitle;
        public ImageView trailerThumbnail;
        ViewGroup container;

        public TrailerViewHolder(View view) {
            super(view);
            trailerTitle = (TextView) view.findViewById(R.id.trailer_title);
            trailerThumbnail = (ImageView) view.findViewById(R.id.trailer_thumbnail);
            container = (ViewGroup) view.findViewById(R.id.container);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onTrailerClicked(trailers.get(getLayoutPosition()));
        }
    }

    public interface TrailerPlayListener{
       void onTrailerClicked(Trailer trailer);
    }
}