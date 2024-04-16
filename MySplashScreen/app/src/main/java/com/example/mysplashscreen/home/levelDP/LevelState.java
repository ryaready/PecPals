package com.example.mysplashscreen.home.levelDP;

import com.example.mysplashscreen.User;

public interface LevelState {
    int getLevelStateImageResource();
    int currLevel();
    void levelUp(User user);
}
