package com.example.moviecatalogue.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

<<<<<<< HEAD
import com.example.moviecatalogue.fragment.MovieFragment;
import com.example.moviecatalogue.fragment.TvShowFragment;
import com.example.moviecatalogue.R;
=======
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.fragment.MovieFragment;
import com.example.moviecatalogue.fragment.TvShowFragment;
>>>>>>> API

public class SectionPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_movie, R.string.tab_tvshow};
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
