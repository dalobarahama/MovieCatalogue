package com.example.moviecatalogue.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.viewModel.MainViewModel;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE =
            "com.example.moviecatalogue.EXTRA_MOVIE";

    private TextView textViewTitle, textViewReleaseDate, textViewDescription;
    private ImageView imageViewPoster;
    private ProgressBar progressBar;

    private boolean favorited = false;
    private MainViewModel mainViewModel;
    private Movie movie;

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

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        addMovie();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.movie_detail));
        }
    }

    private void addMovie() {
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String title = movie.getTitle();
        String releaseDate = movie.getReleaseDate();
        String description = movie.getDescription();
        String poster = movie.getPoster();

        textViewTitle.setText(title);
        textViewReleaseDate.setText(releaseDate);
        textViewDescription.setText(description);
        Glide.with(this)
                .load(poster)
                .into(imageViewPoster);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favorite_menu) {
            if (favorited) {
                Toast.makeText(this, "Unfavorited", Toast.LENGTH_SHORT).show();
                item.setIcon(R.drawable.ic_favorite_border);
                favorited = false;

                mainViewModel.deleteMovie(movie);
            } else {
                Toast.makeText(this, "Favorited", Toast.LENGTH_SHORT).show();
                item.setIcon(R.drawable.ic_favorite);
                favorited = true;

                mainViewModel.insertMovie(movie);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
