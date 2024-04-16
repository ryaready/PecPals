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

    boolean taskOver = false;

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

        exerciseImage.setImageResource(exerciseList.get(taskID).getImageID());
        exerciseDesc.setText(exerciseList.get(taskID).getDesc());
        exerciseName.setText(exerciseList.get(taskID).getName());

        completedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToPage();
            }
        });

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


        int currCoin = user.getCoins();
        int totalCoins = currCoin + 500;
        user.setCoins(totalCoins);

        int a = user.getXp();
        int totala = a + 500;
        user.setXp(totala);

        user.levelUp();
        taskOver = true;
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
