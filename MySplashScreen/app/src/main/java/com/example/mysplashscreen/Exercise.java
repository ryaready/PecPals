package com.example.mysplashscreen;

import java.util.List;


public class Exercise {
    String name;
    int difficultyLevel; // 1 to 4
    List<String> muscleGroups;

    Exercise(String name, int difficultyLevel, List<String> muscleGroups) {
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.muscleGroups = muscleGroups;
    }
}

