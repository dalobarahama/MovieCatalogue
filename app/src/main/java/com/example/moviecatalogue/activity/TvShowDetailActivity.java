package com.example.moviecatalogue.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.TvShow;

public class TvShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW =
            "com.example.moviecatalogue.EXTRA_TVSHOW";

    private TextView textViewTitle, textViewReleaseDate, textViewDescription;
    private ImageView imageViewPoster;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewTitle = findViewById(R.id.detail_movie_title);
        textViewReleaseDate = findViewById(R.id.detail_movie_release_date);
        textViewDescription = findViewById(R.id.detail_movie_description);
        imageViewPoster = findViewById(R.id.detail_movie_poster);
        progressBar = findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.VISIBLE);

        addTvShow();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.movie_detail));
        }
    }

    private void addTvShow() {
        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TVSHOW);
        String title = tvShow.getTitle();
        String releaseDate = tvShow.getReleaseDate();
        String description = tvShow.getDescription();
        String poster = tvShow.getPoster();

        textViewTitle.setText(title);
        textViewReleaseDate.setText(releaseDate);
        textViewDescription.setText(description);
        Glide.with(this)
                .load(poster)
                .into(imageViewPoster);

        progressBar.setVisibility(View.GONE);
    }
}
