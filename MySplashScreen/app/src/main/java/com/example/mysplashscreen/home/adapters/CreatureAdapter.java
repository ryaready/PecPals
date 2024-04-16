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
import com.example.mysplashscreen.home.models.Creature1;
import com.example.mysplashscreen.home.models.Creature2;

import java.util.List;


public class CreatureAdapter extends RecyclerView.Adapter<CreatureAdapter.CreatureViewHolder> {

public class CreatureAdapter extends RecyclerView.Adapter<CreatureAdapter.CreatureViewHolder> implements UserObserver {

    private List<Object> creatureList;
    private User user = User.getInstance();

    public CreatureAdapter(List<Object> creatureList) {
        this.creatureList = creatureList;
    }


    @NonNull
    @Override
    public CreatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.creature_image, parent, false);
        return new CreatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatureViewHolder holder, int position) {

//        holder.textViewCoins.setText(String.valueOf(creatureList.get(position).getCoins()));
        holder.textViewName.setText(creatureList.get(position).getName());
        holder.imageView.setImageResource(creatureList.get(position).getImage());
//        holder.textViewXP.setText(String.valueOf((creatureList.get(position).getXP())));
//        holder.taskButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityWithIntent(getAppContext(), TaskActivity.class);
//           }
//       });

        if (creatureList.get(position) instanceof Creature) {
            Creature creature = (Creature) creatureList.get(position);
            holder.textViewName.setText(creature.getName());
            holder.imageView.setImageResource(creature.getImage());
        } else if (creatureList.get(position) instanceof Creature1) {
            Creature1 creature = (Creature1) creatureList.get(position);
            holder.textViewName.setText(creature.getName());
            holder.imageView.setImageResource(creature.getImage());
        } else if (creatureList.get(position) instanceof Creature2) {
            Creature2 creature = (Creature2) creatureList.get(position);
            holder.textViewName.setText(creature.getName());
            holder.imageView.setImageResource(creature.getImage());
        }

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

    public void onUserUpdated(User user) {
        // Update the user reference
        this.user = user;
        // Notify the adapter that the data has changed
        notifyDataSetChanged();
    }

    public class CreatureViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageView;
        private TextView textViewName;

        public CreatureViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.creatureImage);

//            textViewCoins = itemView.findViewById(R.id.userCoins);
            textViewName = itemView.findViewById(R.id.creatureName2);
//            textViewXP = itemView.findViewById(R.id.creatureXP);
//            taskButton = itemView.findViewById(R.id.taskButton);

            textViewName = itemView.findViewById(R.id.creatureName2);

        }
    }

    public Object getItem(int position) {
        return creatureList.get(position);
    }
}
