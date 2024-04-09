package com.example.mysplashscreen;

public class LevelBaseState implements LevelState {

    User user = User.getInstance();
    @Override
    public int getLevelStateImageResource() {
        return R.drawable.beige;
    }

    @Override
    public void levelUp(User user) {
        int xp = user.getXp();
        if(xp >= 500){
            user.setLevelState(new Level1State());
        }
    }
}