package com.example.mysplashscreen.home.models;
import com.example.mysplashscreen.R;
import com.example.mysplashscreen.User;

import java.util.HashMap;

public class Creature {
    private int image;
    private String name;
    private Tasks tasks;
    private User user = User.getInstance();
    private int levelState = user.getLevelState();


    public Creature(String name, int levelState){
        this.name = name;
        this.levelState = levelState;
        setImg(levelState);
    }

    public static HashMap getImageHashMapC1() {
        // Create a HashMap with Integer keys and String values (assuming file paths)
        HashMap<Integer, Integer> imageHashMap = new HashMap<>();
        imageHashMap.put(0, R.drawable.lockedegg);
        imageHashMap.put(1, R.drawable.c1pos1);
        imageHashMap.put(2, R.drawable.c1pos2);
        imageHashMap.put(3, R.drawable.c1pos3);
        imageHashMap.put(4, R.drawable.c1pos4);
        imageHashMap.put(5, R.drawable.c1pos5);
        imageHashMap.put(6, R.drawable.c1pos6);
        imageHashMap.put(7, R.drawable.c1pos7);
        imageHashMap.put(8, R.drawable.c1pos8);
        imageHashMap.put(9, R.drawable.c1pos9);
        imageHashMap.put(10, R.drawable.c1pos10);
        imageHashMap.put(11, R.drawable.c1pos11);
        imageHashMap.put(12, R.drawable.c1pos12);
        imageHashMap.put(13, R.drawable.c1pos13);

        return imageHashMap;

    }

    public void setImg(int a){

        HashMap map = getImageHashMapC1();

        if (a  >= 13){
            this.image = (int) map.get(13);
        }
        else{
            this.image = (int) map.get(a);
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