package com.example.mysplashscreen.home.levelDP;

import com.example.mysplashscreen.R;
import com.example.mysplashscreen.User;

public class LevelBaseState implements LevelState {

    User user = User.getInstance();
    @Override
    public int getLevelStateImageResource() {

        return R.drawable.lockedegg;
    }

    @Override
    public int currLevel() {
        return 0;
    }


    @Override
    public void levelUp(User user) {
        int xp = user.getXp();
        if(xp >= 10){
            user.setLevelState(new Level1State());
        }
    }
}