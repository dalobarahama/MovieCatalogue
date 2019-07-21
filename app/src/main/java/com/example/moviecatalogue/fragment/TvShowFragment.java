package com.example.moviecatalogue.fragment;


<<<<<<< HEAD
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
=======
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
>>>>>>> API
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD

import com.example.moviecatalogue.activity.DetailActivity;
import com.example.moviecatalogue.adapter.MovieAdapter;
import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.utils.ItemClickSupport;
=======
import android.widget.ProgressBar;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.TvShowDetailActivity;
import com.example.moviecatalogue.adapter.TvShowAdapter;
import com.example.moviecatalogue.model.TvShow;
import com.example.moviecatalogue.utils.ItemClickSupport;
import com.example.moviecatalogue.viewModel.MainViewModel;
>>>>>>> API

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
<<<<<<< HEAD
    private String[] tvShowTitle, tvShowReleaseDate, tvShowDescription;
    private TypedArray tvShowPoster;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movieArrayList;
    RecyclerView recyclerView;
=======
    private TvShowAdapter tvShowAdapter;
    private ArrayList<TvShow> tvShowArrayList;
    private ProgressBar progressBar;
>>>>>>> API

    public TvShowFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

<<<<<<< HEAD
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieAdapter = new MovieAdapter(getActivity());
        recyclerView.setAdapter(movieAdapter);
=======
        progressBar = view.findViewById(R.id.progress_bar);

        MainViewModel mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getTvShow().observe(getActivity(), getTvShow);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvShowAdapter = new TvShowAdapter(getActivity());
        recyclerView.setAdapter(tvShowAdapter);
>>>>>>> API

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                goToDetails(position);
            }
        });

<<<<<<< HEAD
        prepare();
        addMovie();
=======
        mainViewModel.setMovies("tvShow");
        showLoading(true);
>>>>>>> API

        return view;
    }

<<<<<<< HEAD
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
=======
    private Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShows) {
            tvShowArrayList = new ArrayList<>();

            if (tvShows != null) {
                tvShowAdapter.setTvShows(tvShows);
                tvShowArrayList.addAll(tvShows);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


    private void goToDetails(int position) {
        TvShow tvShow = new TvShow();
        tvShow.setTitle(tvShowArrayList.get(position).getTitle());
        tvShow.setReleaseDate(tvShowArrayList.get(position).getReleaseDate());
        tvShow.setDescription(tvShowArrayList.get(position).getDescription());
        tvShow.setPoster(tvShowArrayList.get(position).getPoster());

        Intent intent = new Intent(getActivity(), TvShowDetailActivity.class);
        intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW, tvShow);
>>>>>>> API
        startActivity(intent);
    }

}
