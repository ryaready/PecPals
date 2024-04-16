package com.example.mysplashscreen.home.models;

package com.example.mysplashscreen.home.models;


import com.example.mysplashscreen.R;
import com.example.mysplashscreen.User;

import java.util.HashMap;


public class Creature2 {
    private int image;
    private String name;
    private int coins;
    private int XP;
    private Tasks tasks;


    public Creature2(int image, String name, int coins, int XP){
        this.image = image;

        private User user = User.getInstance();
        private int levelState = user.getLevelState();


    public Creature(String name, int levelState){

            this.name = name;
            this.coins = coins;
            this.XP = XP;
        }


    public Creature(int image, String name, int coins, int XP, Tasks task){
            this(image, name, coins, XP);
            tasks = task;
        }

        public int getImage(){ return image; }
        public int getCoins(){ return coins; }

        public String getName(){ return name; }

        public int getXP() {return XP;}

        public Tasks getTasks(){return tasks;}

        public static HashMap getImageHashMapC3() {
            // Create a HashMap with Integer keys and String values (assuming file paths)
            HashMap<Integer, Integer> imageHashMap = new HashMap<>();
            imageHashMap.put(0, R.drawable.lockedegg);
            imageHashMap.put(1, R.drawable.c3pos1);
            imageHashMap.put(2, R.drawable.c3pos2);
            imageHashMap.put(3, R.drawable.c3pos3);
            imageHashMap.put(4, R.drawable.c3pos4);
            imageHashMap.put(5, R.drawable.c3pos5);
            imageHashMap.put(6, R.drawable.c3pos6);
            imageHashMap.put(7, R.drawable.c3pos7);
            imageHashMap.put(8, R.drawable.c3pos8);
            imageHashMap.put(9, R.drawable.c3pos9);
            imageHashMap.put(10, R.drawable.c3pos10);
            imageHashMap.put(11, R.drawable.c3pos11);

            return imageHashMap;

        }

        public void setImg(int a){

            HashMap map = getImageHashMapC3();

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