package com.example.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Parcelable {
    private String title, description, releaseDate, poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.releaseDate);
        dest.writeString(this.poster);
    }

    public Movie() {

    }

    public Movie(JSONObject jsonObject) {
        try {
            String title = jsonObject.getString("title");
            String description = jsonObject.getString("overview");
            String releaseDate = jsonObject.getString("release_date");
            String poster = jsonObject.getString("poster_path");

            this.title = title;
            this.description = description;
            this.releaseDate = releaseDate;
            this.poster = "https://image.tmdb.org/t/p/w185" + poster;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Movie(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.releaseDate = in.readString();
        this.poster = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
