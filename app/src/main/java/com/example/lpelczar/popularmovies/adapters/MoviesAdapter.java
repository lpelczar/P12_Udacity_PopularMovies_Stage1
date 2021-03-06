package com.example.lpelczar.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lpelczar.popularmovies.R;
import com.example.lpelczar.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpelczar on 17.02.18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>  {

    final private ListItemClickListener onClickListener;
    private List<Movie> movieList;
    private LayoutInflater inflater;
    private Context context;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public MoviesAdapter(Context context, ListItemClickListener listener) {
        this.movieList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Picasso.with(context)
                .load(movie.getPosterURL())
                .placeholder(R.color.accent)
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return (movieList == null) ? 0 : movieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList.clear();
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView imageView;

        MovieViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.movieImage);
            itemView.setOnClickListener(this);
        }

        ImageView getImageView() {
            return imageView;
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onClickListener.onListItemClick(position);
        }
    }
}
