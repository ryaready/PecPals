package com.example.mysplashscreen;

import static com.example.mysplashscreen.home.MainFragment.creatureAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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


    public static Context context;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;

    BottomSheetDialog bottomSheetDialog;

    ShopFragment shopFragment = new ShopFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    TaskModalFragment taskModalFragment = new TaskModalFragment();

    MainFragment mainFragment = new MainFragment();

    User user = User.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnav);

        // get button components
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        floatingActionButton = findViewById(R.id.homePage);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.homepage);


        // changes tab content when button is clicked with respective pages
        // this is for the navigation bar
        replaceFragment(mainFragment);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.shop) {
                replaceFragment(shopFragment);
            } else if (item.getItemId() == R.id.userProfile) {
                replaceFragment(profileFragment);
            } else if (item.getItemId() == R.id.homepage) {
                replaceFragment(new MainFragment());

                // TODO: if possible, try to find a way to recycle mainFragment instead of creating a new one everytime
//                replaceFragment(mainFragment);
//                MainFragment.UpdateUIForFirstItem();
            }

            user.levelUp();
            creatureAdapter.notifyDataSetChanged();
            return true;
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

    public static void startActivityWithIntent(Context first, Class second){
        Intent intent = new Intent(first, second);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        first.startActivity(intent);
    }

}
