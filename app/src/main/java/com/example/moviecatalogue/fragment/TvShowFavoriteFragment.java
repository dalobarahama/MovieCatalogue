package com.example.moviecatalogue.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.TvShowAdapter;
import com.example.moviecatalogue.model.TvShow;
import com.example.moviecatalogue.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavoriteFragment extends Fragment {
    private TvShowAdapter tvShowAdapter;
    private ArrayList tvShowArrayList;
    private ProgressBar progressBar;

    public TvShowFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show_favorite, container, false);

        progressBar = view.findViewById(R.id.progress_bar);

        MainViewModel mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getLocalTvShowList().observe(getActivity(), new Observer<List<TvShow>>() {
            @Override
            public void onChanged(@Nullable List<TvShow> tvShows) {
                tvShowArrayList = new ArrayList();

                if (tvShows != null){
                    tvShowAdapter.setTvShows(tvShows);
                    tvShowArrayList.addAll(tvShows);
                }
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvShowAdapter = new TvShowAdapter(getActivity());
        recyclerView.setAdapter(tvShowAdapter);

        return view;
    }

}
