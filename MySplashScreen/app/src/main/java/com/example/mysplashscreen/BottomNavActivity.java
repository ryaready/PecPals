package com.example.mysplashscreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BottomNavActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;

    BottomSheetDialog bottomSheetDialog;

    RoadMapFragment roadMapFragment = new RoadMapFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    TaskModalFragment taskModalFragment = new TaskModalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnav);

        // get button components
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        floatingActionButton = findViewById(R.id.addTask);
        bottomNavigationView.setBackground(null);

        // changes tab content when button is clicked with respective pages
        // this is for the navigation bar
        replaceFragment(roadMapFragment);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile) {
                replaceFragment(profileFragment);
            } else if (item.getItemId() == R.id.roadmap) {
                replaceFragment(roadMapFragment);
            }

            return true;
        });

        // changes tab content when the floating action button is clicked
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskModalFragment.show(getSupportFragmentManager(), taskModalFragment.getTag());
            }
        });
    }

    // replaces the fragments currently on screen with the given fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}
