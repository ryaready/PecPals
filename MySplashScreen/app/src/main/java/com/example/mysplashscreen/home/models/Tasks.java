package com.example.mysplashscreen.home.models;

public class Tasks {

    private int taskXP;
    private String taskName;

    public Tasks(int xp, String task){
        taskXP = xp;
        taskName = task;
    }

    public String getTaskName(){
        return taskName;
    }

    public int getTaskXP(){
        return taskXP;
    }
}
