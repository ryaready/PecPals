package com.example.mysplashscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VPAdapter extends RecyclerView.Adapter<VPAdapter.ViewHolder> {

    //ArrayList<ViewPagerItem> viewPagerItemArrayList;
    ArrayList<Exercise> exerciseArrayList;

    //public VPAdapter(ArrayList<ViewPagerItem> viewPagerItemArrayList) {
     //   this.viewPagerItemArrayList = viewPagerItemArrayList;
    //}
    public VPAdapter(ArrayList<Exercise> exerciseArrayList) {
        this.exerciseArrayList =  exerciseArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager_item,parent,false);

        return new ViewHolder(view);
    }

    //@Override
    //public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //ViewPagerItem viewPagerItem = viewPagerItemArrayList.get(position);

       // holder.imageView.setImageResource(viewPagerItem.imageID);
       // holder.tcHeading.setText(viewPagerItem.heading);
       // holder.tvDesc.setText(viewPagerItem.description);

    //}
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Exercise exercise = exerciseArrayList.get(position);
         holder.imageView.setImageResource(exercise.getImageID());
         holder.tcHeading.setText(exercise.getName());
         holder.tvDesc.setText(exercise.getDesc());
    }
    //@Override
    //public int getItemCount() {
    //    return viewPagerItemArrayList.size();
    //}
    @Override
    public int getItemCount() {
          return exerciseArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tcHeading, tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivimage);
            tcHeading = itemView.findViewById(R.id.tvHeading);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }

}