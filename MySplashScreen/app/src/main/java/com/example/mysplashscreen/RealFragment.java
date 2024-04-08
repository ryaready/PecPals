package com.example.mysplashscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RealFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RealFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public String mParam1;
    public String mParam2;
    public ArrayList<RealItems> realItemsArrayList;
    public String[] rewardHeadings;
    public String[] rewardDetails;
    public int[] imageResourceID;
    public RecyclerView recyclerview;

    public RealFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RealFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        dataInitialize();

        recyclerview = view.findViewById(R.id.recyclerview1);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        MyAdapter1 myAdapter = new MyAdapter1(getContext(), realItemsArrayList);
        recyclerview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    public void dataInitialize() {

        realItemsArrayList = new ArrayList<>();

        rewardHeadings = new String[]{
                getString(R.string.Gym0),
                getString(R.string.Gym1),
                getString(R.string.Gym2),
                getString(R.string.Boost0),
                getString(R.string.NTUC0),
                getString(R.string.NTUC1)
        };
        rewardDetails = new String[]{
                getString(R.string.Gym0description),
                getString(R.string.Gym1description),
                getString(R.string.Gym2description),
                getString(R.string.Boost0description),
                getString(R.string.NTUC0description),
                getString(R.string.NTUC1description)

        };
        imageResourceID = new int[]{
                R.drawable.activesglogo,
                R.drawable.activesglogo,
                R.drawable.activesglogo,
                R.drawable.boostlogo,
                R.drawable.ntuclogo,
                R.drawable.ntuclogo
        };
        for (int i = 0; i < rewardHeadings.length; i++){

            RealItems realitems = new RealItems(rewardHeadings[i], rewardDetails[i], imageResourceID[i] );
            realItemsArrayList.add(realitems);
        }
    }
}