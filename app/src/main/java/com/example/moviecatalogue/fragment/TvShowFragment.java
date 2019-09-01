package com.example.moviecatalogue.fragment;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.activity.TvShowDetailActivity;
import com.example.moviecatalogue.adapter.TvShowAdapter;
import com.example.moviecatalogue.model.TvShow;
import com.example.moviecatalogue.utils.ItemClickSupport;
import com.example.moviecatalogue.viewModel.MainViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TvShowAdapter tvShowAdapter;
    private ArrayList<TvShow> tvShowArrayList;
    private ProgressBar progressBar;

    public TvShowFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        progressBar = view.findViewById(R.id.progress_bar);

        MainViewModel mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getTvShow().observe(getActivity(), getTvShow);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvShowAdapter = new TvShowAdapter(getActivity());
        recyclerView.setAdapter(tvShowAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                goToDetails(position);
            }
        });

        mainViewModel.setMovies("tvShow");
        showLoading(true);

        return view;
    }

    private Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShows) {
            tvShowArrayList = new ArrayList<>();

            if (tvShows != null) {
                tvShowAdapter.setTvShows(tvShows);
                tvShowArrayList.addAll(tvShows);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
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

}
