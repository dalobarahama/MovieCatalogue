package com.example.moviecatalogue.fragment;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.MovieDetailActivity;
import com.example.moviecatalogue.adapter.MovieAdapter;
import com.example.moviecatalogue.database.FavoriteHelper;
import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.utils.ItemClickSupport;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment {
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movieArrayList;
    private ProgressBar progressBar;
    private FavoriteHelper favoriteHelper;


    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_favorite, container, false);

        progressBar = view.findViewById(R.id.progress_bar);

        favoriteHelper = FavoriteHelper.getInstance(getActivity());
        movieArrayList = favoriteHelper.getAllFavoriteMovies();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieAdapter = new MovieAdapter(getActivity());
        movieAdapter.setMovies(movieArrayList);
        movieAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(movieAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                goToDetails(position);
            }
        });

        return view;
    }

    private void goToDetails(int position) {
        Movie movie = new Movie();
        movie.setId(movieArrayList.get(position).getId());
        movie.setTitle(movieArrayList.get(position).getTitle());
        movie.setReleaseDate(movieArrayList.get(position).getReleaseDate());
        movie.setDescription(movieArrayList.get(position).getDescription());
        movie.setPoster(movieArrayList.get(position).getPoster());

        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}
