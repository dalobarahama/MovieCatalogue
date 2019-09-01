package com.example.moviecatalogue.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.moviecatalogue.database.DatabaseContract.MovieColumns;
import static com.example.moviecatalogue.database.DatabaseContract.TABLE_MOVIE;
import static com.example.moviecatalogue.database.DatabaseContract.TABLE_TVSHOW;
import static com.example.moviecatalogue.database.DatabaseContract.TvShowColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movieTvShowDatabase";

    public static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_MOVIE,
            MovieColumns.ID_MOVIE,
            MovieColumns.TITLE_MOVIE,
            MovieColumns.DESCRIPTION_MOVIE,
            MovieColumns.RELEASE_DATE_MOVIE,
            MovieColumns.POSTER_MOVIE);

    private static final String CREATE_TABLE_TVSHOW = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_TVSHOW,
            TvShowColumns.ID_TVSHOW,
            TvShowColumns.TITLE_TVSHOW,
            TvShowColumns.DESCRIPTION_TVSHOW,
            TvShowColumns.RELEASE_DATE_TVSHOW,
            TvShowColumns.POSTER_TVSHOW);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE);
        sqLiteDatabase.execSQL(CREATE_TABLE_TVSHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TVSHOW);
        onCreate(sqLiteDatabase);
    }
}
