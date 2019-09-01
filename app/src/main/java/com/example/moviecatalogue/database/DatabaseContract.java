package com.example.moviecatalogue.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_MOVIE = "movie";
    public static String TABLE_TVSHOW = "tvshow";

    static final class MovieColumns implements BaseColumns {

        static String ID_MOVIE = "id";
        static String TITLE_MOVIE = "title";
        static String DESCRIPTION_MOVIE = "description";
        static String RELEASE_DATE_MOVIE = "releaseDate";
        static String POSTER_MOVIE = "poster";
    }

    static final class TvShowColumns implements BaseColumns {

        static String ID_TVSHOW = "id";
        static String TITLE_TVSHOW = "title";
        static String DESCRIPTION_TVSHOW = "description";
        static String RELEASE_DATE_TVSHOW = "releaseDate";
        static String POSTER_TVSHOW = "poster";
    }
}
