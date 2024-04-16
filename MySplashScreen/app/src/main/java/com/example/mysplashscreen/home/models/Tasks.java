package com.example.mysplashscreen.home.models;

public class Tasks {

    private String taskName;

    public Tasks(String task){
        taskName = task;
    }

    public String getTaskName(){
        return taskName;
    }

    public int getTaskXP(){
        return 10;
    }
}
