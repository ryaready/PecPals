package com.example.mysplashscreen.home.models;

import com.example.mysplashscreen.R;
import com.example.mysplashscreen.User;

import java.util.HashMap;

public class Creature2 {
    private int image;
    private String name;
    private Tasks tasks;
    private User user = User.getInstance();
    private int levelState = user.getLevelState();


    public Creature2(String name, int levelState){
        this.name = name;
        this.levelState = levelState;
        setImg(levelState);
    }

    public static HashMap getImageHashMapC3() {
        // Create a HashMap with Integer keys and String values (assuming file paths)
        HashMap<Integer, Integer> imageHashMap = new HashMap<>();
        imageHashMap.put(0, R.drawable.lockedegg);
        imageHashMap.put(25, R.drawable.c3pos1);
        imageHashMap.put(26, R.drawable.c3pos2);
        imageHashMap.put(27, R.drawable.c3pos3);
        imageHashMap.put(28, R.drawable.c3pos4);
        imageHashMap.put(29, R.drawable.c3pos5);
        imageHashMap.put(30, R.drawable.c3pos6);
        imageHashMap.put(31, R.drawable.c3pos7);
        imageHashMap.put(32, R.drawable.c3pos8);
        imageHashMap.put(33, R.drawable.c3pos9);
        imageHashMap.put(34, R.drawable.c3pos10);
        imageHashMap.put(35, R.drawable.c3pos11);

        return imageHashMap;

    }

    public void setImg(int a){

        HashMap map = getImageHashMapC3();

        if (a  >= 35){
            this.image = (int) map.get(35);
        }
        else if(a <25) {
            this.image = (int) map.get(0);
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