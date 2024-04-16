package com.example.mysplashscreen.home;

import static com.example.mysplashscreen.BottomNavActivity.exerciseList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.mysplashscreen.CirclePagerIndicatorDecoration;
import com.example.mysplashscreen.R;
import com.example.mysplashscreen.User;
import com.example.mysplashscreen.UserObserver;
import com.example.mysplashscreen.home.adapters.CreatureAdapter;
import com.example.mysplashscreen.home.adapters.TaskAdapter;
import com.example.mysplashscreen.home.models.Creature;
import com.example.mysplashscreen.home.models.Creature1;
import com.example.mysplashscreen.home.models.Creature2;
import com.example.mysplashscreen.home.models.Tasks;

import java.util.ArrayList;
import java.util.List;

/*
    Implementation is inspired by and taken from CodingSTUFF:
    https://www.youtube.com/watch?v=CXfXFHuQIWo
 */
public class MainFragment extends Fragment implements UserObserver {

    private static RecyclerView creatureRecyclerView;
    private RecyclerView creatureInfoRV;
    SnapHelper helper;

    public static CreatureAdapter creatureAdapter;
    List<Creature> creatureTasksList = new ArrayList<>();
    List<Tasks> tasksList = new ArrayList<>();
    TaskAdapter taskAdapter;
    TextView tasktodo;
    private User user = User.getInstance();

    List<Object> creatureList = new ArrayList<>();

    int levelState = user.getLevelState();

//    public static CreatureAdapter creatureAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        helper = new LinearSnapHelper();

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        tasktodo = v.findViewById(R.id.taskstodo);
        TextView usernameTextView = v.findViewById(R.id.userName);


        String username = user.getInstance().getEmail();

        usernameTextView.setText("Hello " + username + "!");


        creatureRecyclerView = v.findViewById(R.id.creatureRecyclerView);
        creatureInfoRV = v.findViewById(R.id.creatureInfo);

        // helps snap to a page
        helper.attachToRecyclerView(creatureRecyclerView);

        // pager indicator
        creatureRecyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());

        creatureRecyclerView.setHasFixedSize(true);
        creatureRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));

        creatureInfoRV.setHasFixedSize(true);
        creatureInfoRV.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        creatureList.add(new Creature("pinko", levelState));
        creatureList.add(new Creature1("chickie", levelState));
        creatureList.add(new Creature2("treevor", levelState));

        creatureAdapter = new CreatureAdapter(creatureList);
        creatureRecyclerView.setAdapter(creatureAdapter);


        tasksList.add(new Tasks("Task: " + exerciseList.get(0).getName()));
        tasksList.add(new Tasks("Task: " + exerciseList.get(1).getName()));


        taskAdapter = new TaskAdapter(tasksList);
        creatureInfoRV.setAdapter(taskAdapter);
        creatureRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                // this section is needed to ensure that we are getting the correct page so that
                // the task list can be updated accordingly
                LinearLayoutManager layoutManager = (LinearLayoutManager) creatureRecyclerView.getLayoutManager();
                int activePosition = layoutManager.findFirstVisibleItemPosition();

                // activePosition = 0 -> page 1
                if(activePosition == 0){
                    tasktodo.setVisibility(View.VISIBLE);
                    tasksList.clear();

                    tasksList.add(new Tasks("Task: " + exerciseList.get(0).getName()));
                    tasksList.add(new Tasks("Task: " + exerciseList.get(1).getName()));
                    taskAdapter.notifyDataSetChanged();
                }

                else if (activePosition == 1 && levelState <=13){
                    tasktodo.setVisibility(View.INVISIBLE);
                    taskAdapter.notifyDataSetChanged();

                }
                else if(activePosition == 1 && levelState >13){
                    tasktodo.setVisibility(View.VISIBLE);
                    tasksList.clear();

                    tasksList.add(new Tasks("Task: " + exerciseList.get(2).getName()));
                    tasksList.add(new Tasks("Task: " + exerciseList.get(3).getName()));
                    taskAdapter.notifyDataSetChanged();

                }

                else if(activePosition == 2 && levelState >25){
                    tasksList.clear();
                    tasktodo.setVisibility(View.VISIBLE);
                    tasksList.add(new Tasks("Task: " + exerciseList.get(4).getName()));
                    tasksList.add(new Tasks("Task: " + exerciseList.get(2).getName()));
                    taskAdapter.notifyDataSetChanged();

                }
                else{
                    tasktodo.setVisibility(View.INVISIBLE);
                    //UpdateTasks(tasksList);
                    taskAdapter.notifyDataSetChanged();
                }
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    List<Tasks> UpdateTasks(List<Tasks> taskList){
        tasksList.clear();
//        tasksList.add(new Tasks(10, "Task 1"));
//        tasksList.add(new Tasks(20, "Task 2"));
//        tasksList.add(new Tasks(30, "Task 3"));
//        tasksList.add(new Tasks(40, "Task 4"));
//        tasksList.add(new Tasks(80, "Task 8"));
//        tasksList.add(new Tasks(90, "Task 9"));
//        tasksList.add(new Tasks(100, "Task 10"));
        return taskList;
    }

    public static void UpdateUIForFirstItem(){
        LinearLayoutManager layoutManager = (LinearLayoutManager) creatureRecyclerView.getLayoutManager();
        layoutManager.scrollToPosition(0);

    }

    @Override
    public void onUserUpdated(User user) {
    }

}