package com.example.mysplashscreen.home.levelDP;

import com.example.mysplashscreen.R;
import com.example.mysplashscreen.User;

public class Level2State implements LevelState {

    User user = User.getInstance();
    @Override
    public int getLevelStateImageResource() {
        return R.drawable.c1pos2;
    }

    @Override
    public int currLevel() {
        return 2;
    }

    @Override
    public void levelUp(User user) {
        int xp = user.getXp();
        if(xp >= 30){
            user.setLevelState(new Level13State());
        }
    }
}