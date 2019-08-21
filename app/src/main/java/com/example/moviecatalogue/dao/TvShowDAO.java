package com.example.moviecatalogue.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.moviecatalogue.model.TvShow;

import java.util.List;

@Dao
public interface TvShowDAO {
    @Insert
    void insert(TvShow tvShow);

    @Update
    void update(TvShow tvShow);

    @Delete
    void delete(TvShow tvShow);

    @Query("SELECT * FROM tvshow_table ORDER BY title ASC")
    LiveData<List<TvShow>> getAllTvShows();
}
