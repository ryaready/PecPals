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

public class TaskActivity extends AppCompatActivity{
    CountDownTimer countDownTimer;

    ImageView exerciseImage;
    TextView exerciseName, exerciseDesc;

    Button completedButton;

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
                completedExercise();
            }
        });

        TextView mTextField = findViewById(R.id.timer);
        /*
        Countdown timer implementation taken from:
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

    public void completedExercise() {
        Toast.makeText(getApplicationContext(), "Well Done!", Toast.LENGTH_SHORT).show();

        int ls = user.getLoginStreak();
        double mul = ls * 0.5;

        int currCoin = user.getCoins();
        int totalCoins = (int)(currCoin + 50*mul);
        user.setCoins(totalCoins);

        int currXP = user.getXp();
        int totalXP = (int)(currXP + 50*mul);
        user.setXp(totalXP);

        user.levelUp();
        taskOver = true;
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
    }
}
