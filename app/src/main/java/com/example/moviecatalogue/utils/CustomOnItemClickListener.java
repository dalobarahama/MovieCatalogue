package com.example.moviecatalogue.utils;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {
    private int postion;
    private OnItemClickCallback onItemClickCallback;

    public CustomOnItemClickListener(int postion, OnItemClickCallback onItemClickCallback) {
        this.postion = postion;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, postion);
    }

    public interface OnItemClickCallback {
        void onItemClicked(View view, int position);
    }
}
