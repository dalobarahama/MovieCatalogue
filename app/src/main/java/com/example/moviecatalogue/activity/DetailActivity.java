package com.example.moviecatalogue.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.R;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE =
            "com.example.moviecatalogue.EXTRA_MOVIE";

    private TextView textViewTitle, textViewReleaseDate, textViewDescription;
    private ImageView imageViewPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewTitle = findViewById(R.id.detail_movie_title);
        textViewReleaseDate = findViewById(R.id.detail_movie_release_date);
        textViewDescription = findViewById(R.id.detail_movie_description);
        imageViewPoster = findViewById(R.id.detail_movie_poster);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String title = movie.getTitle();
        String releaseDate = movie.getReleaseDate();
        String description = movie.getDescription();
        int poster = movie.getPoster();

        textViewTitle.setText(title);
        textViewReleaseDate.setText(releaseDate);
        textViewDescription.setText(description);
        imageViewPoster.setImageResource(poster);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.movie_detail));
        }
    }
}
