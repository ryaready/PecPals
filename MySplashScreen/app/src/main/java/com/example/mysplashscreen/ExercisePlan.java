package com.example.mysplashscreen;

import java.util.List;
import java.util.stream.Collectors;
public class ExercisePlan {
    private List<Exercise> exercisesDatabase;

    ExercisePlan(List<Exercise> exercisesDatabase) {
        this.exercisesDatabase = exercisesDatabase;
    }

    List<Exercise> generateExercisePlan(int difficulty, List<String> targetMuscleGroups) {
        return exercisesDatabase.stream()
                .filter(e -> e.difficultyLevel == difficulty)
                .filter(e -> e.muscleGroups.stream().anyMatch(targetMuscleGroups::contains))
                .limit(5)
                .collect(Collectors.toList());
    }
}
