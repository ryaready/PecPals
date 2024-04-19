package com.example.mysplashscreen;

import android.view.View;

/*
Implementation taken from:
https://stackoverflow.com/questions/28296708/get-clicked-item-and-its-position-in-recyclerview
Use this listener to get the items in the RecyclerView, this is used to get the position of the
item that is clicked in the recycler.
 */
public interface RecyclerViewClickListener {
    public void recyclerViewListClicked(View v, int pos);
}
