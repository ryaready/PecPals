package com.example.mysplashscreen;

public class Exercise {
    private String id;
    private String name;
    private int imageID;
    private String desc;
    private int setCount;
    private int repCount;
    private int difficulty;
    private String muscleGroup;

    private Boolean completed;

    public Exercise() {}

    public Exercise(String id, String name, String desc, int set, int rep, int difficulty, String muscleGroup) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.muscleGroup = muscleGroup;
        this.desc = desc;
        setCount = set;
        repCount = rep;
        completed = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSetCount() {
        return setCount;
    }

    public void setSetCount(int setCount) {
        this.setCount = setCount;
    }

    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getCompleted() {
        return completed;
    }
}