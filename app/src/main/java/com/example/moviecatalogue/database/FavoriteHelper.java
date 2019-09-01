package com.example.moviecatalogue.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.model.TvShow;

import java.util.ArrayList;

import static com.example.moviecatalogue.database.DatabaseContract.MovieColumns.DESCRIPTION_MOVIE;
import static com.example.moviecatalogue.database.DatabaseContract.MovieColumns.ID_MOVIE;
import static com.example.moviecatalogue.database.DatabaseContract.MovieColumns.POSTER_MOVIE;
import static com.example.moviecatalogue.database.DatabaseContract.MovieColumns.RELEASE_DATE_MOVIE;
import static com.example.moviecatalogue.database.DatabaseContract.MovieColumns.TITLE_MOVIE;
import static com.example.moviecatalogue.database.DatabaseContract.TABLE_MOVIE;
import static com.example.moviecatalogue.database.DatabaseContract.TABLE_TVSHOW;
import static com.example.moviecatalogue.database.DatabaseContract.TvShowColumns.DESCRIPTION_TVSHOW;
import static com.example.moviecatalogue.database.DatabaseContract.TvShowColumns.ID_TVSHOW;
import static com.example.moviecatalogue.database.DatabaseContract.TvShowColumns.POSTER_TVSHOW;
import static com.example.moviecatalogue.database.DatabaseContract.TvShowColumns.RELEASE_DATE_TVSHOW;
import static com.example.moviecatalogue.database.DatabaseContract.TvShowColumns.TITLE_TVSHOW;

public class FavoriteHelper {

    public static final String DATABASE_MOVIE = TABLE_MOVIE;
    public static final String DATABASE_TVSHOW = TABLE_TVSHOW;
    private static DatabaseHelper databaseHelper;
    private static FavoriteHelper INSTANCE;

    private static SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()) database.close();
    }

    public ArrayList<Movie> getAllFavoriteMovies() {
        database = databaseHelper.getReadableDatabase();
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        Cursor cursor = database.query(DATABASE_MOVIE, null, null,
                null,
                null,
                null,
                ID_MOVIE + " ASC",
                null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndex(ID_MOVIE)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(TITLE_MOVIE)));
                movie.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION_MOVIE)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(RELEASE_DATE_MOVIE)));
                movie.setPoster(cursor.getString(cursor.getColumnIndex(POSTER_MOVIE)));

                movieArrayList.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return movieArrayList;
    }

    public ArrayList<TvShow> getAllFavoriteTvShows() {
        database = databaseHelper.getReadableDatabase();
        ArrayList<TvShow> tvShowArrayList = new ArrayList<>();

        Cursor cursor = database.query(DATABASE_TVSHOW, null, null,
                null,
                null,
                null,
                ID_TVSHOW + " ASC",
                null);
        cursor.moveToFirst();
        TvShow tvShow;
        if (cursor.getCount() > 0) {
            do {
                tvShow = new TvShow();
                tvShow.setId(cursor.getInt(cursor.getColumnIndex(ID_TVSHOW)));
                tvShow.setTitle(cursor.getString(cursor.getColumnIndex(TITLE_TVSHOW)));
                tvShow.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION_TVSHOW)));
                tvShow.setReleaseDate(cursor.getString(cursor.getColumnIndex(RELEASE_DATE_TVSHOW)));
                tvShow.setPoster(cursor.getString(cursor.getColumnIndex(POSTER_TVSHOW)));

                tvShowArrayList.add(tvShow);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return tvShowArrayList;
    }

    public void insertFavoriteMovie(Movie movie) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_MOVIE, movie.getId());
        contentValues.put(TITLE_MOVIE, movie.getTitle());
        contentValues.put(DESCRIPTION_MOVIE, movie.getDescription());
        contentValues.put(RELEASE_DATE_MOVIE, movie.getReleaseDate());
        contentValues.put(POSTER_MOVIE, movie.getPoster());

        database.insert(DATABASE_MOVIE, null, contentValues);
    }

    public void insertFavoriteTvShow(TvShow tvShow) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_TVSHOW, tvShow.getId());
        contentValues.put(TITLE_TVSHOW, tvShow.getTitle());
        contentValues.put(DESCRIPTION_TVSHOW, tvShow.getDescription());
        contentValues.put(RELEASE_DATE_TVSHOW, tvShow.getReleaseDate());
        contentValues.put(POSTER_TVSHOW, tvShow.getPoster());

        database.insert(DATABASE_TVSHOW, null, contentValues);
    }

    public void deleteFavoriteMovie(int id) {
        database = databaseHelper.getReadableDatabase();
        database.delete(DATABASE_MOVIE, ID_MOVIE + " = " + id, null);
    }

    public void deleteFavoriteTvShow(int id) {
        database = databaseHelper.getReadableDatabase();
        database.delete(DATABASE_TVSHOW, ID_TVSHOW + " = " + id, null);
    }

    public boolean searchFavoriteMovie(String movie) {
        String selection = TITLE_MOVIE + " =?";
        String[] selectionArgs = {movie};
        String limit = "1";
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(DATABASE_MOVIE, null,
                selection,
                selectionArgs,
                null,
                null,
                null,
                limit);
        boolean favorite = (cursor.getCount() > 0);
        cursor.close();
        return favorite;
    }

    public boolean searchFavoriteTvShow(String tvShow) {
        String selection = TITLE_TVSHOW + " =?";
        String[] selectionArgs = {tvShow};
        String limit = "1";
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(DATABASE_TVSHOW, null,
                selection,
                selectionArgs,
                null,
                null,
                null,
                limit);
        boolean favorite = (cursor.getCount() > 0);
        cursor.close();
        return favorite;
    }
}
