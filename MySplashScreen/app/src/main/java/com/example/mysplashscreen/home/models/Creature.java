package com.example.mysplashscreen.home.models;

public class Creature {
    private int image;

    private String name;
    private int coins;

    private int XP;

    public Creature(int image, String name, int coins, int XP){
        this.image = image;
        this.name = name;
        this.coins = coins;
        this.XP = XP;
    }

    public int getImage(){ return image; }
    public int getCoins(){ return coins; }

    public String getName(){ return name; }

    public int getXP() {return XP;}

}
