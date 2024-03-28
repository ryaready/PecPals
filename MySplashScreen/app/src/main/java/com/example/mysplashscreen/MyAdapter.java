package com.example.mysplashscreen;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<com.example.mysplashscreen.CoinItems> coinItemsArrayList;

    public MyAdapter(Context context, ArrayList<CoinItems> coinItemsArrayList) {
        this.context = context;
        this.coinItemsArrayList = coinItemsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CoinItems coinitems = coinItemsArrayList.get(position);
        holder.description.setText(coinitems.description);
        holder.price.setText(coinitems.price);
        holder.image.setImageResource(coinitems.image);

    }

    @Override
    public int getItemCount() {
        return coinItemsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView description;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            itemView.findViewById(R.id.buybutton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("button", "onClick user wants to buy" + description );

                }
            });
        }
    }
}
