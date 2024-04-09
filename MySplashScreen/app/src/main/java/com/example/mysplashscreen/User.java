package com.example.mysplashscreen;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static User instance;
    private String email;
    private int xp;
    private int coins;
    private int loginStreak;
    private List<UserObserver> observers = new ArrayList<>();

    DatabaseHelper databaseHelper;

    private User() {}

    // Static method to obtain the singleton instance
    public static synchronized User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    // Setters for fields
    public void setEmail(String email) {
        this.email = email;
    }

    public void setXp(int xp) {
        this.xp = xp;
        notifyObservers();
    }

    public void setCoins(int coins) {
        this.coins = coins;
        notifyObservers();
    }

    public void setLoginStreak(int loginStreak) {
        this.loginStreak = loginStreak;
        notifyObservers();
    }

    // Getters for fields
    public String getEmail() {
        return email;
    }

    public int getXp() {
        return xp;
    }

    public int getCoins() {
        return coins;
    }

    public int getLoginStreak() {
        return loginStreak;
    }

    public void removeObserver(UserObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (UserObserver observer : observers) {
            observer.onUserUpdated(this);
        }
    }


}
