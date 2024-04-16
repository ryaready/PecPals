package com.example.mysplashscreen;

import static com.example.mysplashscreen.BottomNavActivity.exerciseList;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity{
    ViewPager2 viewPager2;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;
    CountDownTimer countDownTimer;

    ImageView exerciseImage;
    TextView exerciseName, exerciseDesc;

    Button completedButton;
    List<Integer> images = new ArrayList<>();
    List<String> heading = new ArrayList<>();
    List<String> desc = new ArrayList<>();

    User user = User.getInstance();
    int taskID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            taskID = extras.getInt("task");
        }

        exerciseImage = findViewById(R.id.ivimage);
        exerciseName = findViewById(R.id.tvHeading);
        exerciseDesc = findViewById(R.id.tvDesc);
        completedButton = findViewById(R.id.completed_button);

        //viewPager2 = findViewById(R.id.viewpager);

//        bicep_curls.setName(getString(R.string.bicepcurl_name));
//        bicep_curls.setDesc(getString(R.string.bicepcurl_desc));
//        bicep_curls.setImageID(R.drawable.a);
//
//        shoulder_press.setName(getString(R.string.shoulderpress_name));
//        shoulder_press.setDesc(getString(R.string.shoulderpress_desc));
//        shoulder_press.setImageID(R.drawable.b);
//
//        bent_over_rows.setName(getString(R.string.bentoverrows_name));
//        bent_over_rows.setDesc(getString(R.string.bentoverrows_desc));
//        bent_over_rows.setImageID(R.drawable.c);
//
//        bench_press.setName(getString(R.string.benchpress_name));
//        bench_press.setDesc(getString(R.string.benchpress_desc));
//        bench_press.setImageID(R.drawable.d);
//
//        push_ups.setName(getString(R.string.pushup_name));
//        push_ups.setDesc(getString(R.string.pushup_desc));
//        push_ups.setImageID(R.drawable.e);

//        exercisePlan.clearExerciseDatabase();
//        exercisePlan.insertExercise(push_ups);
//        exercisePlan.insertExercise(bicep_curls);
//        exercisePlan.insertExercise(shoulder_press);
//        exercisePlan.insertExercise(bench_press);
//        exercisePlan.insertExercise(bent_over_rows);
//
//        exerciseArrayList = exercisePlan.getAllExercises();
//        Log.d("array", String.valueOf(exerciseArrayList));

//        Log.d("check database", String.valueOf(exercisePlan.getAllExercises()));

//        Collections.addAll(images,R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e);
//        Collections.addAll(heading, getString(R.string.bicepcurl_name),getString(R.string.shoulderpress_name),getString(R.string.bentoverrows_name),getString(R.string.benchpress_name),getString(R.string.pushup_name));
//        Collections.addAll(desc, getString(R.string.bicepcurl_desc),
//                getString(R.string.shoulderpress_desc),
//                getString(R.string.bentoverrows_desc),
//                getString(R.string.benchpress_desc)
//                ,getString(R.string.pushup_desc));


//        Log.d("ExerciseDatabase", String.valueOf(exerciseArrayList.size()));

        exerciseImage.setImageResource(exerciseList.get(taskID).getImageID());
        exerciseDesc.setText(exerciseList.get(taskID).getDesc());
        exerciseName.setText(exerciseList.get(taskID).getName());

        completedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToPage();
            }
        });
        // VPAdapter vpAdapter = new VPAdapter(exerciseArrayList);

        // viewPager2.setAdapter(vpAdapter);

        // viewPager2.setClipToPadding(false);

        // viewPager2.setClipChildren(false);

        // viewPager2.setOffscreenPageLimit(2);

        // viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        //        viewPagerItemArrayList = new ArrayList<>();
        //
        //        for (int i =0; i< images.length ; i++){
        //
        //            ViewPagerItem viewPagerItem = new ViewPagerItem(images[i],heading[i],desc[i]);
        //            viewPagerItemArrayList.add(viewPagerItem);
        //
        //        }

        //        VPAdapter vpAdapter = new VPAdapter(viewPagerItemArrayList);
        //
        //        viewPager2.setAdapter(vpAdapter);
        //
        //        viewPager2.setClipToPadding(false);
        //
        //        viewPager2.setClipChildren(false);
        //
        //        viewPager2.setOffscreenPageLimit(2);
        //
        //        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

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

    public void jumpToPage() {
//        int i = viewPager2.getCurrentItem();
//        viewPager2.setCurrentItem(i+1);
        Toast.makeText(getApplicationContext(), "Well Done!", Toast.LENGTH_SHORT).show();
//        resetTimer(60000, 1000);
//        if (i == viewPager2.getAdapter().getItemCount()-1){
//
//
//        }

        int ls = user.getLoginStreak();
        double mul = ls * 0.5;


        int currCoin = user.getCoins();
        int totalCoins = (int)(currCoin + 50*mul);
        user.setCoins(totalCoins);

        int currXP = user.getXp();
        int totalXP = (int)(currXP + 50*mul);
        user.setXp(totalXP);

        if (user.checkLvlUp() == true){
            Toast.makeText(getApplicationContext(), "Level Up !!!!", Toast.LENGTH_SHORT).show();
            user.levelUp();
        }
        Intent intent = new Intent(this.getApplicationContext(), BottomNavActivity.class);
        startActivity(intent);
    }

    void resetTimer(long ms, long interval){
        countDownTimer.cancel();
        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //exercisePlan.clearExerciseDatabase();
//        Log.d("ExerciseDatabase", String.valueOf(exerciseArrayList.size()));
    }
}
