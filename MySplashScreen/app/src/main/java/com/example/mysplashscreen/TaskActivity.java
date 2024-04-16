package com.example.mysplashscreen;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    ArrayList<Exercise> exerciseArrayList;
    CountDownTimer countDownTimer;
    Exercise bicep_curls = new Exercise();
    Exercise shoulder_press = new Exercise();
    Exercise bent_over_rows = new Exercise();
    Exercise bench_press = new Exercise();
    Exercise push_ups = new Exercise();
    ExercisePlan exercisePlan = new ExercisePlan(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);

        viewPager2 = findViewById(R.id.viewpager);

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
        exercisePlan.insertExercise(bicep_curls);
        exercisePlan.insertExercise(shoulder_press);
        exercisePlan.insertExercise(bench_press);
        exercisePlan.insertExercise(bent_over_rows);

        exerciseArrayList = exercisePlan.getAllExercises();
        Log.d("array", String.valueOf(exerciseArrayList));

        Log.d("check database", String.valueOf(exercisePlan.getAllExercises()));
        VPAdapter vpAdapter = new VPAdapter(exerciseArrayList);

        viewPager2.setAdapter(vpAdapter);

        viewPager2.setClipToPadding(false);

        viewPager2.setClipChildren(false);

        viewPager2.setOffscreenPageLimit(2);

        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        TextView mTextField = findViewById(R.id.timer);
        /*
        https://developer.android.com/reference/android/os/CountDownTimer
         */
        countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("Time's Up!");
            }
        }.start();

    }

    public void jumpToPage(View view) {
        int i = viewPager2.getCurrentItem();
        viewPager2.setCurrentItem(i+1);
        Toast.makeText(getApplicationContext(), "Well Done!", Toast.LENGTH_SHORT).show();
        resetTimer(60000, 1000);
        Log.d("HELP", String.valueOf(viewPager2.getAdapter().getItemCount()-1));
        Log.d("array", String.valueOf(exerciseArrayList));
        if (i == viewPager2.getAdapter().getItemCount()-1){
            BottomNavActivity.startActivityWithIntent(this.getApplicationContext(), BottomNavActivity.class);
        }
    }

    void resetTimer(long ms, long interval){
        countDownTimer.cancel();
        countDownTimer.start();
    }

}
