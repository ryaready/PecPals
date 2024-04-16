package com.example.mysplashscreen.home.levelDP;

import com.example.mysplashscreen.R;
import com.example.mysplashscreen.User;

public class Level1State implements LevelState {

    User user = User.getInstance();
    @Override
    public int getLevelStateImageResource() {
        return R.drawable.c1pos1;
    }

    @Override
    public int currLevel() {
        return 1;
    }

    @Override
    public void levelUp(User user) {
        int xp = user.getXp();
        if(xp >= 20){
            user.setLevelState(new Level2State());
        }
    }
}