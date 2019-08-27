package com.example.moviecatalogue.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.moviecatalogue.BuildConfig;
import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.model.TvShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends AndroidViewModel {
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;
    private MutableLiveData<ArrayList<TvShow>> listTvShows = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

    public MainViewModel(Application application) {
        super(application);
    }

    public void setMovies(String type) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShow> listTvShow = new ArrayList<>();
        final ArrayList<Movie> listMovie = new ArrayList<>();
        String urlTvShow = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";
        String urlMovie = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US";

        if (type.equals("tvShow")) {
            client.get(urlTvShow, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String result = new String(responseBody);
                        JSONObject responseObject = new JSONObject(result);
                        JSONArray list = responseObject.getJSONArray("results");

                        for (int i = 0; i < list.length(); i++) {
                            JSONObject tvShow = list.getJSONObject(i);
                            TvShow tvShowItem = new TvShow(tvShow);
                            listTvShow.add(tvShowItem);
                        }
                        listTvShows.postValue(listTvShow);
                    } catch (JSONException e) {
                        Log.d("Execption", e.getMessage());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("onFailure", error.getMessage());
                }
            });
        } else {
            client.get(urlMovie, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String result = new String(responseBody);
                        JSONObject responseObject = new JSONObject(result);
                        JSONArray list = responseObject.getJSONArray("results");

                        for (int i = 0; i < list.length(); i++) {
                            JSONObject movie = list.getJSONObject(i);
                            Movie movieItem = new Movie(movie);
                            listMovie.add(movieItem);
                        }
                        listMovies.postValue(listMovie);
                    } catch (JSONException e) {
                        Log.d("Execption", e.getMessage());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("onFailure", error.getMessage());
                }
            });
        }
    }

    public LiveData<ArrayList<TvShow>> getTvShow() {
        return listTvShows;
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }

}
