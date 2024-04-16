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

import java.util.ArrayList;
import java.util.List;

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

    public static List<Exercise> exerciseList = new ArrayList<>();
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

        exerciseList = instantiateExercises();
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

    public static Intent startActivityTaskActivity(Context first, Class second, int pos){
        Intent intent = new Intent(first, second);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("task", pos);
        first.startActivity(intent);
        return intent;
    }

    private List<Exercise> instantiateExercises(){
        Exercise bicep_curls = new Exercise();
        Exercise shoulder_press = new Exercise();
        Exercise bent_over_rows = new Exercise();
        Exercise bench_press = new Exercise();
        Exercise push_ups = new Exercise();
        ExercisePlan exercisePlan = new ExercisePlan(this);

        //viewPager2 = findViewById(R.id.viewpager);

        bicep_curls.setName(getString(R.string.bicepcurl_name));
        bicep_curls.setDesc(getString(R.string.bicepcurl_desc));
        bicep_curls.setImageID(R.drawable.a);

        shoulder_press.setName(getString(R.string.shoulderpress_name));
        shoulder_press.setDesc(getString(R.string.shoulderpress_desc));
        shoulder_press.setImageID(R.drawable.b);

        bent_over_rows.setName(getString(R.string.bentoverrows_name));
        bent_over_rows.setDesc(getString(R.string.bentoverrows_desc));
        bent_over_rows.setImageID(R.drawable.c);

        bench_press.setName(getString(R.string.benchpress_name));
        bench_press.setDesc(getString(R.string.benchpress_desc));
        bench_press.setImageID(R.drawable.d);

        push_ups.setName(getString(R.string.pushup_name));
        push_ups.setDesc(getString(R.string.pushup_desc));
        push_ups.setImageID(R.drawable.e);

        exercisePlan.clearExerciseDatabase();
        exercisePlan.insertExercise(push_ups);
//        Log.d("check push ups", String.valueOf(exercisePlan.insertExercise(push_ups)));
        exercisePlan.insertExercise(bicep_curls);
        exercisePlan.insertExercise(shoulder_press);
        exercisePlan.insertExercise(bench_press);
        exercisePlan.insertExercise(bent_over_rows);

        List<Exercise> exercises =  new ArrayList<>();
        exercises.add(bicep_curls);
        exercises.add(shoulder_press);
        exercises.add(bent_over_rows);
        exercises.add(bench_press);
        exercises.add(push_ups);

//        return exercisePlan.getAllExercises();
        return exercises;
    }

}
