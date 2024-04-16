package com.example.mysplashscreen;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class User {
    private static User instance;
    private String email;
    private String password;
    private int xp;
    private int coins;
    private int loginStreak;
    private int levelState;
    private List<UserObserver> observers = new ArrayList<>();

    private DatabaseReference databaseReference;

    protected User() {
    }

    public static synchronized User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void setLevelState(int levelState){
        this.levelState = levelState;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {

        return email;
    }

    public String getPassword() {

        return password;
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
    public void levelUp() {
        notifyObservers();
    }

    public int getLevelState() {
        return levelState;
    }

    public void saveUserData(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pecpals-84281-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = database.getReference().child("users");
        String email = getEmail();
        if (email != null) {
            databaseReference.child(email).setValue(user);
        }
    }
}

