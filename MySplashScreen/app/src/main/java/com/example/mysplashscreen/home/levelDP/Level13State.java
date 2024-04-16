package com.example.mysplashscreen.home.levelDP;

import com.example.mysplashscreen.R;
import com.example.mysplashscreen.User;

public class Level13State implements LevelState {

    User user = User.getInstance();
    @Override
    public int getLevelStateImageResource() {

        return R.drawable.pos4;
    }

    @Override
    public int currLevel() {
        return 13;
    }

    @Override
    public void levelUp(User user) {
        int xp = user.getXp();
        if(xp >= 40){
            user.setLevelState(new Level14State());
        }
    }
}