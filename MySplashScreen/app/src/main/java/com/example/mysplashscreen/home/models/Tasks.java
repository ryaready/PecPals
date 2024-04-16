package com.example.mysplashscreen.home.models;

public class Tasks {

    private int taskNumber;
    private String taskName;

    public Tasks(int taskNumber, String task){
        this.taskNumber = taskNumber;
        taskName = task;
    }

    public String getTaskName(){
        return taskName;
    }

    public int getTaskNumber(){
        return taskNumber;
    }
}
