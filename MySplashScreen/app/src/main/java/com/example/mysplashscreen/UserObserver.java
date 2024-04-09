package com.example.mysplashscreen;


public interface UserObserver {
    void onUserXpUpdated(User user);
    void onUserCoinsUpdated(User user);
    void onUserLoginStreakUpdated(User user);
}