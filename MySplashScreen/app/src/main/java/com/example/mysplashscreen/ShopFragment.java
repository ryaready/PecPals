package com.example.mysplashscreen;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopFragment extends Fragment  implements RecyclerViewClickListener{

    private Fragment coinFragment, realFragment;

    private RecyclerView voucherShopRV;

    ArrayList<RealItems> realItemsArrayList;

    TextView coinsDisplay;

    User user;

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
//        coinFragment = new CoinFragment();
//        realFragment = new RealFragment();
//        replaceChildFragment(coinFragment);

        ImageButton coinshopbutton = v.findViewById(R.id.coinshopbutton);
        ImageButton realshopbutton = v.findViewById(R.id.realshopbutton);
        coinsDisplay = v.findViewById(R.id.shop_coin);
        voucherShopRV = v.findViewById(R.id.voucherShopRecyclerView);

        user = User.getInstance();

        realshopbutton.setImageResource(R.drawable.virtual_shop_icon_default);
        coinshopbutton.setImageResource(R.drawable.voucher_shop_icon_clicked);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ImageButton coinshopbutton = view.findViewById(R.id.coinshopbutton);
        ImageButton realshopbutton = view.findViewById(R.id.realshopbutton);
        TextView shopname = view.findViewById(R.id.shop_name);

        coinsDisplay.setText(String.valueOf(user.getCoins()));

        initializeVoucherShopArray();
        voucherShopRV.setHasFixedSize(true);
        MyAdapter1 voucherShopAdapter = new MyAdapter1(getContext(), realItemsArrayList, this);
        voucherShopRV.setAdapter(voucherShopAdapter);
        voucherShopAdapter.notifyDataSetChanged();

//        coinshopbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                realshopbutton.setImageResource(R.drawable.virtual_shop_icon_default);
//                coinshopbutton.setImageResource(R.drawable.voucher_shop_icon_clicked);
//                shopname.setText("Virtual Shop");
//                replaceChildFragment(coinFragment);
//            }
//
//        });
//
//
//        realshopbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                coinshopbutton.setImageResource(R.drawable.voucher_shop_icon_default);
//                realshopbutton.setImageResource(R.drawable.virtual_shop_icon_clicked);
//                shopname.setText("Voucher Shop");
//                replaceChildFragment(realFragment);
//            }
//        });
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


    public void initializeVoucherShopArray() {

//        Log.d("initialisevouchershoparray", "s");
        realItemsArrayList = new ArrayList<>();

        int[] rewardCosts = new int[]{
                100,
                200,
                300,
                400,
                500,
                600
        };

        String[] rewardDuration = new String[]{
                "ActiveSG \n1 Month",
                "ActiveSG \n2 Month",
                "NTUC \n5 Dollars Off",
                "NTUC \n10 Dollars Off",
                "Boost \n1 Free drink",
                "Boost \n2 Free drinks"
        };
        int[] imageResourceID = new int[]{
                R.drawable.active_sg,
                R.drawable.active_sg,
                R.drawable.ntuc_logo,
                R.drawable.ntuc_logo,
                R.drawable.boost_logo,
                R.drawable.boost_logo
        };
        for (int i = 0; i < rewardCosts.length; i++){

            RealItems realitems = new RealItems(rewardCosts[i], rewardDuration[i], imageResourceID[i] );
            realItemsArrayList.add(realitems);
        }
    }

    public void recyclerViewListClicked(View v, int pos) {

        int current_coins = user.getCoins();
        int cost_of_current_item_clicked = realItemsArrayList.get(pos).reward;
        if (current_coins < cost_of_current_item_clicked){
            pos = -1;
        }
        else{
            current_coins -= realItemsArrayList.get(pos).reward;
            user.setCoins(current_coins);
            coinsDisplay.setText(String.valueOf(user.getCoins()));
        }

        // pos represents each item with 0 being the 1st item
        switch (pos) {
            case -1:
                Toast.makeText(getActivity().getApplicationContext(), "Not Enough Coins! :(", Toast.LENGTH_SHORT).show();
                break;
            case 0:
            case 1:
//                Toast.makeText(getActivity().getApplicationContext(), "First Item!", Toast.LENGTH_SHORT).show();
                showDialog(R.drawable.activesg_qrcode);
                break;
            case 2:
            case 3:
                showDialog(R.drawable.ntuc_qrcode);
                break;
            case 4:
            case 5:
                showDialog(R.drawable.boost_qrcode);
                break;
        }
    }

    private void showDialog(int qrcode_drawable){
        AlertDialog dialogBuilder = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.qrcode_popup, null);
        dialogBuilder.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ImageButton closeModalBtn = dialogView.findViewById(R.id.closeQRModal);

        ImageView qrcode = dialogView.findViewById(R.id.QRcodeImage);
        qrcode.setImageResource(qrcode_drawable);

        closeModalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();

    }


}