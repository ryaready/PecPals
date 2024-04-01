package com.example.mysplashscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

    Context context;
    ArrayList<RealItems> realItemsArrayList;

    public MyAdapter1(Context context, ArrayList<RealItems> realItemsArrayList) {
        this.context = context;
        this.realItemsArrayList = realItemsArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.real_list_item, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        com.example.mysplashscreen.RealItems realitems = realItemsArrayList.get(position);
        holder.reward.setText(realitems.reward);
        holder.description.setText(realitems.rewarddescription);
        holder.image.setImageResource(realitems.rewardimage);

    }

    @Override
    public int getItemCount() {
        return realItemsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView reward;
        TextView description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.rewardimage);
            description = itemView.findViewById(R.id.rewarddescription);
            reward = itemView.findViewById(R.id.reward);
        }
    }
}
