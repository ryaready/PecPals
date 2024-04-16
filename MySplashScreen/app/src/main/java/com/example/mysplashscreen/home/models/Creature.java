package com.example.mysplashscreen.home.models;


import com.example.mysplashscreen.User;
import com.example.mysplashscreen.home.levelDP.Level13State;
import com.example.mysplashscreen.home.levelDP.LevelState;

public class Creature {
    private int image;
    private String name;
    private Tasks tasks;
    private User user = User.getInstance();

    private LevelState levelState = user.getLevelState();

    private Level13State level13State = new Level13State();
    private int currLevel;




    public Creature(String name, LevelState levelState){
        this.name = name;
        this.levelState = levelState;
    }

    public void setCurrLevel(){
        if (levelState.currLevel() >=13){
            currLevel = 13
        }
        else{
            currLevel = levelState.currLevel();
        }
    }

    public void setImg(){

        if (currLevel  >= 13){
            this.image = level13State.getLevelStateImageResource();
        }
        else{
            this.image = levelState.getLevelStateImageResource();
        }
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