package com.example.mysplashscreen.home.adapters;

import static com.example.mysplashscreen.BottomNavActivity.getAppContext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysplashscreen.BottomNavActivity;
import com.example.mysplashscreen.R;
import com.example.mysplashscreen.TaskActivity;
import com.example.mysplashscreen.home.models.Tasks;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

//    private List<Creature> creatureList;
    private List<Tasks> taskList;

    public TaskAdapter(List<Tasks> list){
        taskList = list;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.creature_tasks, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
//        Log.d("creature_tasks", String.valueOf(taskList.get(position).getTaskXP()));
        holder.taskXpText.setText("+" + String.valueOf(taskList.get(position).getTaskNo()));
        holder.taskText.setText(taskList.get(position).getTaskName());
        holder.taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavActivity.startActivityTaskActivity(getAppContext(), TaskActivity.class, taskList.get(holder.getLayoutPosition()).getTaskNo());
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private TextView taskText;
        private TextView taskXpText;
        private CardView taskButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.taskText);
            taskXpText = itemView.findViewById(R.id.creatureXP2);
            taskButton = itemView.findViewById(R.id.taskButton);
        }
    }

}
