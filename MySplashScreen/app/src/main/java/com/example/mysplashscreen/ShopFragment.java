package com.example.mysplashscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

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

        ImageButton coinshopbutton = view.findViewById(R.id.coinshopbutton);
        ImageButton realshopbutton = view.findViewById(R.id.realshopbutton);
        TextView shopname = view.findViewById(R.id.shop_name);
        coinshopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realshopbutton.setImageResource(R.drawable.virtual_shop_icon_default);
                coinshopbutton.setImageResource(R.drawable.voucher_shop_icon_clicked);
                shopname.setText("Virtual Shop");
                replaceChildFragment(coinFragment);
            }

        });


        realshopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coinshopbutton.setImageResource(R.drawable.voucher_shop_icon_default);
                realshopbutton.setImageResource(R.drawable.virtual_shop_icon_clicked);
                shopname.setText("Voucher Shop");
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