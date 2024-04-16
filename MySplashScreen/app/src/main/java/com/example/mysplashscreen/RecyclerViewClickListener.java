package com.example.mysplashscreen;

import android.view.View;

/*
https://stackoverflow.com/questions/28296708/get-clicked-item-and-its-position-in-recyclerview
Use this listener to get the items in the RecyclerView
 */
public interface RecyclerViewClickListener {
    public void recyclerViewListClicked(View v, int pos);
}
