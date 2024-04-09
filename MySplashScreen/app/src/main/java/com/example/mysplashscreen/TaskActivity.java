package com.example.mysplashscreen;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;
    CountDownTimer countDownTimer;

    User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);

        viewPager2 = findViewById(R.id.viewpager);
        int[] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
        String[] heading = {getString(R.string.bicepcurl_name),getString(R.string.shoulderpress_name),getString(R.string.bentoverrows_name),getString(R.string.benchpress_name),getString(R.string.pushup_name)};
        String[] desc = {getString(R.string.bicepcurl_desc),
                getString(R.string.shoulderpress_desc),
                getString(R.string.bentoverrows_desc),
                getString(R.string.benchpress_desc)
                ,getString(R.string.pushup_desc)};

        viewPagerItemArrayList = new ArrayList<>();

        for (int i =0; i< images.length ; i++){

            ViewPagerItem viewPagerItem = new ViewPagerItem(images[i],heading[i],desc[i]);
            viewPagerItemArrayList.add(viewPagerItem);

        }

        VPAdapter vpAdapter = new VPAdapter(viewPagerItemArrayList);

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
        if (i == viewPager2.getAdapter().getItemCount()-1){

            int currCoin = user.getCoins();
            int totalCoins = currCoin + 500;
            user.setCoins(totalCoins);

            int a = user.getXp();
            int totala = a + 500;
            user.setXp(totala);

            BottomNavActivity.startActivityWithIntent(this.getApplicationContext(), BottomNavActivity.class);
        }
    }

    void resetTimer(long ms, long interval){
        countDownTimer.cancel();
        countDownTimer.start();
    }

}
