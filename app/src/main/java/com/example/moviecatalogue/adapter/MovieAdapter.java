package com.example.moviecatalogue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies = new ArrayList<>();

    public MovieAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.title.setText(movies.get(i).getTitle());
        movieViewHolder.releaseDate.setText(movies.get(i).getReleaseDate());
        movieViewHolder.description.setText(movies.get(i).getDescription());

        Glide.with(context)
                .load(movies.get(i).getPoster())
                .into(movieViewHolder.poster);
    }

    @Override
    public int getItemCount() {
        return getMovies().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView title, releaseDate, description;
        private ImageView poster, favorite;

        MovieViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.movie_title);
            releaseDate = view.findViewById(R.id.movie_release_date);
            description = view.findViewById(R.id.movie_description);
            poster = view.findViewById(R.id.movie_poster);
            favorite = view.findViewById(R.id.favorite_image);
        }
    }
}
