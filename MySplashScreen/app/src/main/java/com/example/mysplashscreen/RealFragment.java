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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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

        coinsDisplay = view.findViewById(R.id.shop_coin);
        user = User.getInstance();

        recyclerview = view.findViewById(R.id.recyclerview1);
//        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
//        MyAdapter1 myAdapter = new MyAdapter1(getContext(), realItemsArrayList, this);
//        recyclerview.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();
    }

    public void dataInitialize() {

        realItemsArrayList = new ArrayList<>();

        rewardCosts = new int[]{
                100,
                200,
                300,
                400,
                500,
                600
        };

        rewardDuration = new String[]{
                "ActiveSG \n1 Month",
                "ActiveSG \n2 Month",
                "ActiveSG \n3 Month",
                "ActiveSG \n4 Month",
                "ActiveSG \n5 Month",
                "ActiveSG \n6 Month"

        };
        imageResourceID = new int[]{
                R.drawable.active_sg,
                R.drawable.active_sg,
                R.drawable.active_sg,
                R.drawable.active_sg,
                R.drawable.active_sg,
                R.drawable.active_sg
        };
        for (int i = 0; i < rewardCosts.length; i++){

            RealItems realitems = new RealItems(rewardCosts[i], rewardDuration[i], imageResourceID[i] );
            realItemsArrayList.add(realitems);
        }
    }

    // listener for button clicks in the shop
//    @Override
//    public void recyclerViewListClicked(View v, int pos) {
//        // pos represents each item with 0 being the 1st item
//        switch (pos){
//            case 0:
//                Toast.makeText(getActivity().getApplicationContext(), "Purchased!", Toast.LENGTH_SHORT).show();
//                User user = User.getInstance();
//                int cn = user.getCoins();
//                cn -= 10;
//                user.setCoins(cn);
////                coinsDisplay.setText(String.valueOf(user.getCoins()));
//                FragmentContainer fragmentContainer;
//
//                break;
//            case 1: Toast.makeText(getActivity().getApplicationContext(), "Not Purchasable Yet!", Toast.LENGTH_SHORT).show();
//        }
//    }
}