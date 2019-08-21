package com.example.moviecatalogue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.TvShow;
import com.example.moviecatalogue.utils.CustomOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private Context context;
    private ArrayList<TvShow> tvShows = new ArrayList<>();
    private boolean favorited = false;

    public TvShowAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<TvShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<TvShow> tvShows) {
        this.tvShows.clear();
        this.tvShows.addAll(tvShows);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvShowViewHolder tvShowViewHolder, int i) {
        tvShowViewHolder.title.setText(tvShows.get(i).getTitle());
        tvShowViewHolder.releaseDate.setText(tvShows.get(i).getReleaseDate());
        tvShowViewHolder.description.setText(tvShows.get(i).getDescription());

        Glide.with(context)
                .load(tvShows.get(i).getPoster())
                .into(tvShowViewHolder.poster);
        /*
        tvShowViewHolder.favorite.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                if (favorited) {
                    Toast.makeText(context, "Unfavorited", Toast.LENGTH_SHORT).show();
                    tvShowViewHolder.favorite.setBackground(view.getResources().getDrawable(R.drawable.ic_favorite_border));
                    favorited = false;
                } else {
                    Toast.makeText(context, "Favorited", Toast.LENGTH_SHORT).show();
                    tvShowViewHolder.favorite.setBackground(view.getResources().getDrawable(R.drawable.ic_favorite));
                    favorited = true;
                }
            }
        })); */
    }

    @Override
    public int getItemCount() {
        return getTvShows().size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        private TextView title, releaseDate, description;
        private ImageView poster, favorite;

        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            releaseDate = itemView.findViewById(R.id.movie_release_date);
            description = itemView.findViewById(R.id.movie_description);
            poster = itemView.findViewById(R.id.movie_poster);
            favorite = itemView.findViewById(R.id.favorite_image);
        }
    }
}
