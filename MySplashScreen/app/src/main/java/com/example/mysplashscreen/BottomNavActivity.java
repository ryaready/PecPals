package com.example.mysplashscreen;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mysplashscreen.home.MainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/*
    Implementation is inspired by and taken from Android Knowledege:
    https://www.youtube.com/watch?v=0x5kmLY16qE
 */
public class BottomNavActivity extends AppCompatActivity {

    private static Context context;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;

    BottomSheetDialog bottomSheetDialog;

    ShopFragment shopFragment = new ShopFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    TaskModalFragment taskModalFragment = new TaskModalFragment();

    MainFragment mainFragment = new MainFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnav);

        // get button components
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        floatingActionButton = findViewById(R.id.homePage);
        bottomNavigationView.setBackground(null);

        // changes tab content when button is clicked with respective pages
        // this is for the navigation bar
        replaceFragment(shopFragment);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.shop) {
                replaceFragment(shopFragment);
            } else if (item.getItemId() == R.id.userProfile) {
                replaceFragment(profileFragment);
            }


            floatingActionButton.setImageTintList(ColorStateList.valueOf(getColor(R.color.darkgrey)));

            return true;
        });

        // changes tab content when the floating action button is clicked
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(mainFragment);
                floatingActionButton.setImageTintList(ColorStateList.valueOf(getColor(R.color.black)));
            }
        });

        BottomNavActivity.context = getApplicationContext();
    }

    // replaces the fragments currently on screen with the given fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public static Context getAppContext() {
        return BottomNavActivity.context;
    }



}
