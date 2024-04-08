package com.example.mysplashscreen;

public class User {
    private static User instance;
    private String email;
    private int xp;
    private int coins;
    private int loginStreak;

  
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
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setLoginStreak(int loginStreak) {
        this.loginStreak = loginStreak;
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
}
