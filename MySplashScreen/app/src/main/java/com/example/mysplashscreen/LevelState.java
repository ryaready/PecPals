package com.example.mysplashscreen;

public interface LevelState {
    void levelUp();
    int getImageId();


    int getLevelStateImageResource();
    void levelUp(User user);
}
