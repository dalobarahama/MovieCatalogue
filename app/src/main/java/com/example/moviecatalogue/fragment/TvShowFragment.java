package com.example.moviecatalogue.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogue.activity.DetailActivity;
import com.example.moviecatalogue.adapter.MovieAdapter;
import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.utils.ItemClickSupport;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private String[] tvShowTitle, tvShowReleaseDate, tvShowDescription;
    private TypedArray tvShowPoster;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movieArrayList;
    RecyclerView recyclerView;

    public TvShowFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieAdapter = new MovieAdapter(getActivity());
        recyclerView.setAdapter(movieAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                goToDetails(position);
            }
        });

        prepare();
        addMovie();

        return view;
    }

    private void addMovie() {
        movieArrayList = new ArrayList<>();

        for (int i = 0; i < tvShowTitle.length; i++) {
            Movie movie = new Movie();
            movie.setPoster(tvShowPoster.getResourceId(i, -1));
            movie.setTitle(tvShowTitle[i]);
            movie.setReleaseDate(tvShowReleaseDate[i]);
            movie.setDescription(tvShowDescription[i]);
            movieArrayList.add(movie);
        }
        movieAdapter.setMovies(movieArrayList);
    }

    private void prepare() {
        tvShowTitle = getResources().getStringArray(R.array.tvshow_title);
        tvShowReleaseDate = getResources().getStringArray(R.array.tvshow_release_date);
        tvShowDescription = getResources().getStringArray(R.array.tvshow_description);
        tvShowPoster = getResources().obtainTypedArray(R.array.tvshow_poster);
    }

    private void goToDetails(int position) {
        Movie movie = new Movie();
        movie.setTitle(movieArrayList.get(position).getTitle());
        movie.setReleaseDate(movieArrayList.get(position).getReleaseDate());
        movie.setDescription(movieArrayList.get(position).getDescription());
        movie.setPoster(movieArrayList.get(position).getPoster());

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

}
