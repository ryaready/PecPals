package com.example.mysplashscreen;

import java.util.HashMap;

public class ImageHashMap {
    public static void main(String[] args) {
        // Create a HashMap with Integer keys and String values (assuming file paths)
        HashMap<Integer, String> imageHashMap = new HashMap<>();

        // Adding image paths to the HashMap
        imageHashMap.put(0, R.drawable.lockedegg);
        imageHashMap.put(1, R.drawable.pos1);
        imageHashMap.put(2, R.drawable.image2);
        imageHashMap.put(3, R.drawable.image3);
        imageHashMap.put(4, R.drawable.image4);
        imageHashMap.put(5, R.drawable.image5);
        imageHashMap.put(6, R.drawable.image6);
        imageHashMap.put(7, R.drawable.image7);
        imageHashMap.put(8, R.drawable.image8);
        imageHashMap.put(9, R.drawable.image9);
        imageHashMap.put(10, R.drawable.image10);
        imageHashMap.put(11, R.drawable.image11);
        imageHashMap.put(12, R.drawable.image12);
        imageHashMap.put(13, R.drawable.image13);

        // Retrieve an image path using a key
        int key = 5; // Example key
        String imagePath = imageHashMap.get(key);
        System.out.println("Image path for key " + key + ": " + imagePath);
    }
}
