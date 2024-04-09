package com.example.mysplashscreen;

public class Level1State implements LevelState {

    User user = User.getInstance();
    @Override
    public int getLevelStateImageResource() {
        return R.drawable.lvl12;
    }

    @Override
    public void levelUp(User user) {
        int xp = user.getXp();
        if(xp >= 500){
            user.setLevelState(new Level1State());
        }
    }
}