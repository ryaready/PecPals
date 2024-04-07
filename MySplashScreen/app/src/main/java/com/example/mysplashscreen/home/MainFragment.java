package com.example.mysplashscreen.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.mysplashscreen.CirclePagerIndicatorDecoration;
import com.example.mysplashscreen.R;
import com.example.mysplashscreen.home.adapters.CreatureAdapter;
import com.example.mysplashscreen.home.models.Creature;

import java.util.ArrayList;
import java.util.List;

/*
    Implementation is inspired by and taken from CodingSTUFF:
    https://www.youtube.com/watch?v=CXfXFHuQIWo
 */
public class MainFragment extends Fragment {

    private RecyclerView creatureRecyclerView;
    SnapHelper helper;

    TextView creatureName;

    Button taskButton;

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

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        creatureRecyclerView = v.findViewById(R.id.creatureRecyclerView);

        // helps snap to a page
        helper.attachToRecyclerView(creatureRecyclerView);

        // pager indicator
        creatureRecyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());

        creatureRecyclerView.setHasFixedSize(true);
        creatureRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));

        List<Creature> creatureList = new ArrayList<>();

        creatureList.add(new Creature(R.drawable.animation_splash, "Egg", 10, 2423));
        creatureList.add(new Creature(R.drawable.animation_splash, "Egg 2", 100, 50));

        CreatureAdapter creatureAdapter = new CreatureAdapter(creatureList);
        creatureRecyclerView.setAdapter(creatureAdapter);

//        taskButton = v.findViewById(R.id.taskButton);

        return v;
    }
}