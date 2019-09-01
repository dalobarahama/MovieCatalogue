package com.example.moviecatalogue.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.TvShowDetailActivity;
import com.example.moviecatalogue.adapter.TvShowAdapter;
import com.example.moviecatalogue.database.FavoriteHelper;
import com.example.moviecatalogue.model.TvShow;
import com.example.moviecatalogue.utils.ItemClickSupport;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavoriteFragment extends Fragment {
    private TvShowAdapter tvShowAdapter;
    private ArrayList<TvShow> tvShowArrayList;
    private ProgressBar progressBar;
    private FavoriteHelper favoriteHelper;

    public TvShowFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show_favorite, container, false);

        progressBar = view.findViewById(R.id.progress_bar);

        favoriteHelper = FavoriteHelper.getInstance(getActivity());
        tvShowArrayList = favoriteHelper.getAllFavoriteTvShows();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvShowAdapter = new TvShowAdapter(getActivity());
        tvShowAdapter.setTvShows(tvShowArrayList);
        tvShowAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(tvShowAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                goToDetails(position);
            }
        });

        return view;
    }

    private void goToDetails(int position) {
        TvShow tvShow = new TvShow();
        tvShow.setId(tvShowArrayList.get(position).getId());
        tvShow.setTitle(tvShowArrayList.get(position).getTitle());
        tvShow.setReleaseDate(tvShowArrayList.get(position).getReleaseDate());
        tvShow.setDescription(tvShowArrayList.get(position).getDescription());
        tvShow.setPoster(tvShowArrayList.get(position).getPoster());

        Intent intent = new Intent(getActivity(), TvShowDetailActivity.class);
        intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW, tvShow);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}
