package com.example.mysplashscreen.home.models;

public class Tasks {

    private String taskName;
    private int taskNo;

    public Tasks(int taskno, String task){

        taskName = task;
        taskNo = taskno;
    }

    public String getTaskName(){
        return taskName;
    }

    public int getTaskNo(){
        return taskNo;
    }
}
