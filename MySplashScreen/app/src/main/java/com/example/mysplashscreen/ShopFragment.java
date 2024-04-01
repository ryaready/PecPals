package com.example.mysplashscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ShopFragment extends Fragment {

    private Fragment coinFragment, realFragment;

    ShopFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop, container, false);
        coinFragment = new CoinFragment();
        realFragment = new RealFragment();
        replaceChildFragment(coinFragment);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button coinshopbutton = view.findViewById(R.id.coinshopbutton);
        coinshopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceChildFragment(coinFragment);
            }

        });

        Button realshopbutton = view.findViewById(R.id.realshopbutton);
        realshopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceChildFragment(realFragment);
            }
        });
    }

    /*
    How to implement child fragments by user Suragch:
        https://stackoverflow.com/questions/6672066/fragment-inside-fragment
     */
    private void replaceChildFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment).setReorderingAllowed(true)
                .addToBackStack(null).commit();
    }




}