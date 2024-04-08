package com.example.mysplashscreen;
import java.util.HashMap;

public class User {
    private static final HashMap<String, User> instancesByEmail = new HashMap<>();

    private String email;
    private int xp;
    private int coins;
    private int loginStreak;

    private User() {}

    // Static method to obtain the singleton instance by email
    public static synchronized User getInstance(String email) {
        if (!instancesByEmail.containsKey(email)) {
            User newUser = new User();
            newUser.setEmail(email);
            instancesByEmail.put(email, newUser);
        }
        return instancesByEmail.get(email);
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

