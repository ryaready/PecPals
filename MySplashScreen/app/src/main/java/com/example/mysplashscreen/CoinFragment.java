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
 * Use the {@link CoinFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoinFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<CoinItems> coinItemsArrayList;
    private String[] coinDescription;
    private String[] coinPrice;
    private int[] imageResourceID;
    private RecyclerView recyclerview;

    public CoinFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoinFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoinFragment newInstance(String param1, String param2) {
        CoinFragment fragment = new CoinFragment();
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
        return inflater.inflate(R.layout.fragment_coin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        MyAdapter myAdapter;
        myAdapter = new MyAdapter(getContext(),coinItemsArrayList);
        recyclerview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {
        coinItemsArrayList = new ArrayList<>();

        coinDescription = new String[]{
                getString(R.string.item0),
                getString(R.string.item1),
                getString(R.string.item2),
                getString(R.string.item3)
        };
        coinPrice = new String[]{
                getString(R.string.price0),
                getString(R.string.price1),
                getString(R.string.price2),
                getString(R.string.price3)
        };
        imageResourceID = new int[]{
                R.drawable.beige,
                R.drawable.yellow,
                R.drawable.blue,
                R.drawable.green,
        };

        for (int i=0; i< coinDescription.length; i++){
            CoinItems coinitems = new CoinItems(coinDescription[i],coinPrice[i],imageResourceID[i] );
            coinItemsArrayList.add(coinitems);
        }

    }
}