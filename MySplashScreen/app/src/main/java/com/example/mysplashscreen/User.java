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
    private int currentUserState;
    private List<UserObserver> observers = new ArrayList<>();

    private DatabaseReference databaseReference;
    private boolean checkLvlUp;

    protected User() {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://pecpals-84281-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = database.getReference().child("users");
        currentUserState = 1;

    }

    public static synchronized User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
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

    public int getCurrentUserState() {
        return currentUserState;
    }
    public void upgradeCurrentUserState() {
        if( (200 <= getXp()) && (getXp() < 300)){
            currentUserState = 2;
        } else if((300 <= getXp()) && (getXp() <= 400)) {
            currentUserState = 3;
        } else {
            currentUserState = 4;
        }
    }

    public void removeObserver(UserObserver observer) {

        observers.remove(observer);
    }

    private void notifyObservers() {
        for (UserObserver observer : observers) {
            observer.onUserUpdated(this);
        }
    }

//    public void levelUp() {
//        notifyObservers();
//    }

    public boolean checkLvlUp(){
        if(xp%50 == 0 && xp!= 0){
            return true;
        }
        else{
            return false;
        }
    }


    public void levelUp() {
        if (checkLvlUp == true){
            int next = xp/50;
            setLevelState(next);
            notifyObservers();
        }

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

