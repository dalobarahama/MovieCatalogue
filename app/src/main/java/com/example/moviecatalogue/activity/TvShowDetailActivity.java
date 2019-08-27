package com.example.moviecatalogue.activity;

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
import com.example.moviecatalogue.database.FavoriteHelper;
import com.example.moviecatalogue.model.TvShow;

public class TvShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW =
            "com.example.moviecatalogue.EXTRA_TVSHOW";

    private TextView textViewTitle, textViewReleaseDate, textViewDescription;
    private ImageView imageViewPoster;
    private ProgressBar progressBar;

    private boolean favorited;
    private TvShow tvShow;
    private FavoriteHelper favoriteHelper;

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

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());

        addTvShow();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.movie_detail));
        }
    }

    private void addTvShow() {
        tvShow = getIntent().getParcelableExtra(EXTRA_TVSHOW);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        favorited = favoriteHelper.searchFavoriteTvShow(tvShow.getTitle());
        if (favorited) {
            menu.findItem(R.id.favorite_menu).setIcon(R.drawable.ic_favorite);
        } else {
            menu.findItem(R.id.favorite_menu).setIcon(R.drawable.ic_favorite_border);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favorite_menu) {
            favoriteHelper.open();
            if (favorited) {
                favoriteHelper.deleteFavoriteTvShow(tvShow.getId());
                item.setIcon(R.drawable.ic_favorite_border);
                Toast.makeText(this, "Unfavorited", Toast.LENGTH_SHORT).show();
            } else {
                favoriteHelper.insertFavoriteTvShow(tvShow);
                item.setIcon(R.drawable.ic_favorite);
                Toast.makeText(this, "Favorited", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
