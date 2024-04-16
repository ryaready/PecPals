package com.example.mysplashscreen.home.models;


import com.example.mysplashscreen.User;

public class Creature {
    private int image;
    private String name;
    private Tasks tasks;
    private User user = User.getInstance();

    private int levelState = user.getLevelState();
    


    public Creature(String name, int levelState){
        this.name = name;
        this.levelState = levelState;
    }

    public void setImg(int a){

        if (levelState  >= 13){
            this.image = getLevelStateImageResource(13);
        }
        else{
            this.image = getLevelStateImageResource(a);
        }
    }

    private int getLevelStateImageResource(int i) {
        return i;

    }


    public int getImage(){
        return image;
    }
    public String getName(){
        return name;
    }
    public Tasks getTasks(){
        return tasks;
    }

}