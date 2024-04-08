package com.example.mysplashscreen;

import androidx.appcompat.app.AppCompatActivity;

public class RewardsGeneral extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private final double egg_1 = 1;
    private final double egg_2 = 1.1;
    private final double egg_3 = 1.3;
    private final double egg_4 = 1.5;
    private final double egg_5 = 2;
    private double loginStreak = 1;
    private int egg_level;

    private double exp_balance = 0;
    private double coin_balance = 0;
    private double exp_gained = 0;
    private double coins_gained = 0;

    public double getExp_balance() {
        return exp_balance;
    }

    public double getCoin_balance() {
        return coin_balance;
    }

    private void updateRewards() {
        //if loginSuccessful == true
        //loginStreak *= ;
        exp_gained = 50;
        coins_gained = 20;
        //if loginSuccessful on 7 days,
        //exp_gained = exp_gained + (loginStreak + 0.1)
        //exp_gained = exp_gained + (loginStreak + 0.1)
        exp_balance += exp_gained;
        coin_balance += coins_gained;
        //databaseHelper.insertRewards(exp_balance, coin_balance);
    }

}
