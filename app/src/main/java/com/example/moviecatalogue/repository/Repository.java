package com.example.moviecatalogue.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.moviecatalogue.dao.MovieDAO;
import com.example.moviecatalogue.dao.TvShowDAO;
import com.example.moviecatalogue.database.MovieDatabase;
import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.model.TvShow;

import java.util.List;

public class Repository {
    private MovieDAO movieDAO;
    private TvShowDAO tvShowDAO;
    private LiveData<List<Movie>> allMovies;
    private LiveData<List<TvShow>> allTvShows;

    public Repository(Application application) {
        MovieDatabase database = MovieDatabase.getInstance(application);
        movieDAO = database.movieDAO();
        tvShowDAO = database.tvShowDAO();
        allMovies = movieDAO.getAllMovies();
        allTvShows = tvShowDAO.getAllTvShows();
    }

    public void insertMovie(Movie movie) {
        new InsertMovieAsnycTask(movieDAO).execute(movie);
    }

    public void updateMovie(Movie movie) {
        new UpdateMovieAsnycTask(movieDAO).execute(movie);
    }

    public void deleteMovie(Movie movie) {
        new DeleteMovieAsnycTask(movieDAO).execute(movie);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }

    public void insertTvShow(TvShow tvShow) {
        new InsertTvShowAsnycTask(tvShowDAO).execute(tvShow);
    }

    public void updateTvShow(TvShow tvShow) {
        new UpdateTvShowAsnycTask(tvShowDAO).execute(tvShow);
    }

    public void deleteTvShow(TvShow tvShow) {
        new DeleteTvShowAsnycTask(tvShowDAO).execute(tvShow);
    }

    public LiveData<List<TvShow>> getAllTvShows() {
        return allTvShows;
    }

    private static class InsertMovieAsnycTask extends AsyncTask<Movie, Void, Void> {
        private MovieDAO movieDAO;

        private InsertMovieAsnycTask(MovieDAO movieDAO) {
            this.movieDAO = movieDAO;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDAO.insert(movies[0]);
            return null;
        }
    }

    private static class UpdateMovieAsnycTask extends AsyncTask<Movie, Void, Void> {
        private MovieDAO movieDAO;

        private UpdateMovieAsnycTask(MovieDAO movieDAO) {
            this.movieDAO = movieDAO;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDAO.update(movies[0]);
            return null;
        }
    }

    private static class DeleteMovieAsnycTask extends AsyncTask<Movie, Void, Void> {
        private MovieDAO movieDAO;

        private DeleteMovieAsnycTask(MovieDAO movieDAO) {
            this.movieDAO = movieDAO;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDAO.delete(movies[0]);
            return null;
        }
    }

    private static class InsertTvShowAsnycTask extends AsyncTask<TvShow, Void, Void> {
        private TvShowDAO tvShowDAO;

        private InsertTvShowAsnycTask(TvShowDAO tvShowDAO) {
            this.tvShowDAO = tvShowDAO;
        }

        @Override
        protected Void doInBackground(TvShow... tvShows) {
            tvShowDAO.insert(tvShows[0]);
            return null;
        }
    }

    private static class UpdateTvShowAsnycTask extends AsyncTask<TvShow, Void, Void> {
        private TvShowDAO tvShowDAO;

        private UpdateTvShowAsnycTask(TvShowDAO tvShowDAO) {
            this.tvShowDAO = tvShowDAO;
        }

        @Override
        protected Void doInBackground(TvShow... tvShows) {
            tvShowDAO.update(tvShows[0]);
            return null;
        }
    }

    private static class DeleteTvShowAsnycTask extends AsyncTask<TvShow, Void, Void> {
        private TvShowDAO tvShowDAO;

        private DeleteTvShowAsnycTask(TvShowDAO tvShowDAO) {
            this.tvShowDAO = tvShowDAO;
        }

        @Override
        protected Void doInBackground(TvShow... tvShows) {
            tvShowDAO.delete(tvShows[0]);
            return null;
        }
    }

}
