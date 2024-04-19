package com.example.mysplashscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RealFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RealFragment extends Fragment{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String mParam1;
    public String mParam2;
    public ArrayList<RealItems> realItemsArrayList;
    public int[] rewardCosts;
    public String[] rewardDuration;
    public int[] imageResourceID;
    public RecyclerView recyclerview;
    TextView coinsDisplay;

    User user;

    public RealFragment() {
        // Required empty public constructor
    }

    public static RealFragment newInstance(String param1, String param2) {
        RealFragment fragment = new RealFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        coinsDisplay = view.findViewById(R.id.shop_coin);
        user = User.getInstance();

        recyclerview = view.findViewById(R.id.recyclerview1);
        recyclerview.setHasFixedSize(true);
    }

}