package com.example.mysplashscreen.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysplashscreen.R;
import com.example.mysplashscreen.home.models.Creature;

import java.util.List;

public class CreatureAdapter extends RecyclerView.Adapter<CreatureAdapter.CreatureViewHolder> {

    private List<Creature> creatureList;
    public CreatureAdapter(List<Creature> creatureList){
        this.creatureList = creatureList;
    }

    @NonNull
    @Override
    public CreatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.creature_layout, parent, false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.creature, parent, false);
        return new CreatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatureViewHolder holder, int position) {
        holder.textViewCoins.setText(String.valueOf(creatureList.get(position).getCoins()));
        holder.textViewName.setText(creatureList.get(position).getName());
        holder.imageView.setImageResource(creatureList.get(position).getImage());
        holder.textViewXP.setText(String.valueOf((creatureList.get(position).getXP())));
    }

    @Override
    public int getItemCount() {
        return creatureList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class CreatureViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textViewName;
        private TextView textViewCoins;
        private TextView textViewXP;

        public CreatureViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.creatureImage);
            textViewCoins = itemView.findViewById(R.id.userCoins);
            textViewName = itemView.findViewById(R.id.creatureName);
            textViewXP = itemView.findViewById(R.id.creatureXP);

        }
    }

    public Creature getItem(int position){
        return creatureList.get(position);
    }

}
