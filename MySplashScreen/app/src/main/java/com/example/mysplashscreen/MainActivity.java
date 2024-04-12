package com.example.mysplashscreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        String email = user.getEmail();
        int xp = user.getXp();
        int coins = user.getCoins();
        databaseHelper.updateXP(email, xp);
        databaseHelper.updateCoins(email, coins);
    }
}