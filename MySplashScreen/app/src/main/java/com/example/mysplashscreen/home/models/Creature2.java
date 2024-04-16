package com.example.mysplashscreen.home.models;
import com.example.mysplashscreen.R;
import com.example.mysplashscreen.User;
import com.example.mysplashscreen.home.models.Tasks;

import java.util.HashMap;

public class Creature2 {
    private int image;
    private String name;
    private Tasks tasks;
    private User user = User.getInstance();
    private int levelState = user.getLevelState();


    public Creature1(String name, int levelState){
        this.name = name;
        this.levelState = levelState;
        setImg(levelState);
    }

    public static HashMap getImageHashMapC1() {
        // Create a HashMap with Integer keys and String values (assuming file paths)
        HashMap<Integer, Integer> imageHashMap = new HashMap<>();
        imageHashMap.put(0, R.drawable.lockedegg);
        imageHashMap.put(1, R.drawable.c0pos1);
        imageHashMap.put(2, R.drawable.c0pos2);
        imageHashMap.put(3, R.drawable.c0pos3);
        imageHashMap.put(4, R.drawable.c0pos4);
        imageHashMap.put(5, R.drawable.c0pos5);
        imageHashMap.put(6, R.drawable.c0pos6);
        imageHashMap.put(7, R.drawable.c0pos7);
        imageHashMap.put(8, R.drawable.c0pos8);
        imageHashMap.put(9, R.drawable.c0pos9);
        imageHashMap.put(10, R.drawable.c0pos10);
        imageHashMap.put(11, R.drawable.c0pos11);

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