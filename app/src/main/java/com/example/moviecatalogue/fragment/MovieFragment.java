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
import com.example.moviecatalogue.activity.MovieDetailActivity;
import com.example.moviecatalogue.adapter.MovieAdapter;
import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.utils.ItemClickSupport;
import com.example.moviecatalogue.viewModel.MainViewModel;
>>>>>>> API

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
<<<<<<< HEAD
    private String[] movieTitle, movieReleaseDate, movieDescription;
    private TypedArray moviePoster;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movieArrayList;
    RecyclerView recyclerView;
=======
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movieArrayList;
    private ProgressBar progressBar;
>>>>>>> API

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

<<<<<<< HEAD
        recyclerView = view.findViewById(R.id.recyclerview);
=======
        progressBar = view.findViewById(R.id.progress_bar);

        MainViewModel mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getMovies().observe(getActivity(), getMovie);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
>>>>>>> API
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

<<<<<<< HEAD
        prepare();
        addMovie();
        return view;
    }

    private void addMovie() {
        movieArrayList = new ArrayList<>();

        for (int i = 0; i < movieTitle.length; i++) {
            Movie movie = new Movie();
            movie.setPoster(moviePoster.getResourceId(i, -1));
            movie.setTitle(movieTitle[i]);
            movie.setReleaseDate(movieReleaseDate[i]);
            movie.setDescription(movieDescription[i]);
            movieArrayList.add(movie);
        }
        movieAdapter.setMovies(movieArrayList);
    }

    private void prepare() {
        movieTitle = getResources().getStringArray(R.array.movie_title);
        movieReleaseDate = getResources().getStringArray(R.array.movie_release_date);
        movieDescription = getResources().getStringArray(R.array.movie_description);
        moviePoster = getResources().obtainTypedArray(R.array.movie_poster);
=======
        mainViewModel.setMovies("movie");
        showLoading(true);

        return view;
    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movie> movies) {
            movieArrayList = new ArrayList<>();

            if (movies != null) {
                movieAdapter.setMovies(movies);
                movieArrayList.addAll(movies);
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
>>>>>>> API
    }

    private void goToDetails(int position) {
        Movie movie = new Movie();
        movie.setTitle(movieArrayList.get(position).getTitle());
        movie.setReleaseDate(movieArrayList.get(position).getReleaseDate());
        movie.setDescription(movieArrayList.get(position).getDescription());
        movie.setPoster(movieArrayList.get(position).getPoster());

<<<<<<< HEAD
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
=======
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
>>>>>>> API
        startActivity(intent);
    }

}
