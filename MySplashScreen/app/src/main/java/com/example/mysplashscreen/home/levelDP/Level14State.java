package com.example.mysplashscreen.home.levelDP;

import com.example.mysplashscreen.R;
import com.example.mysplashscreen.User;

public class Level14State implements LevelState {

    User user = User.getInstance();
    @Override
    public int getLevelStateImageResource() {

        return R.drawable.pos5;
    }

    @Override
    public int currLevel() {
        return 13;
    }

    @Override
    public void levelUp(User user) {
        int xp = user.getXp();
        if(xp >= 50){
            user.setLevelState(new Level14State());
        }
    }
}