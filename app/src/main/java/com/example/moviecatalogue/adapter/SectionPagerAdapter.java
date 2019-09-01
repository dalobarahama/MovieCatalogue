package com.example.moviecatalogue.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.fragment.MovieFavoriteFragment;
import com.example.moviecatalogue.fragment.MovieFragment;
import com.example.moviecatalogue.fragment.TvShowFavoriteFragment;
import com.example.moviecatalogue.fragment.TvShowFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_movie, R.string.tab_tvshow, R.string.tab_movie_favorite, R.string.tab_tvshow_favorite};
    private final Context context;

    public SectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new MovieFragment();
                break;
            case 1:
                fragment = new TvShowFragment();
                break;
            case 2:
                fragment = new MovieFavoriteFragment();
                break;
            case 3:
                fragment = new TvShowFavoriteFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}
