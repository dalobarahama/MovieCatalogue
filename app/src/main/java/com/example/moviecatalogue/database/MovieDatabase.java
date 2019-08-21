package com.example.moviecatalogue.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.moviecatalogue.dao.MovieDAO;
import com.example.moviecatalogue.dao.TvShowDAO;
import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.model.TvShow;

@Database(entities = {Movie.class, TvShow.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;

    public abstract MovieDAO movieDAO();

    public abstract TvShowDAO tvShowDAO();

    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
