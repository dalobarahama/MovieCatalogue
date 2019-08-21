package com.example.moviecatalogue.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.moviecatalogue.model.Movie;

import java.util.List;

@Dao
public interface MovieDAO {

    @Insert
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM movie_table ORDER BY title ASC")
    LiveData<List<Movie>> getAllMovies();
}
