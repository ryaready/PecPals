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

    public void clearExerciseDatabase() {
        dbHelper.deleteAllEntries();
    }


    public ArrayList<Exercise> getAllExercises() {
        ArrayList<Exercise> exercises = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                ExerciseDatabaseHelper.TABLE_EXERCISES,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_NAME));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_DESCRIPTION));
                @SuppressLint("Range") int sets = cursor.getInt(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_SETS));
                @SuppressLint("Range") int reps = cursor.getInt(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_REPS));
                @SuppressLint("Range") int imageID = cursor.getInt(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_IMAGE));
                @SuppressLint("Range") int difficulty = cursor.getInt(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_DIFFICULTY));
                @SuppressLint("Range") String muscleGroup = cursor.getString(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_MUSCLE_GROUP));

                Exercise exercise = new Exercise();
                exercise.setId(String.valueOf(id));
                exercise.setImageID(imageID);
                exercise.setDesc(description);
                exercise.setRepCount(reps);
                exercise.setSetCount(sets);
                exercise.setName(name);
                exercise.setDifficulty(difficulty);
                exercise.setMuscleGroup(muscleGroup);

                exercises.add(exercise);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return exercises;
    }

    public ArrayList<Exercise> getLevelExercises() {
        ArrayList<Exercise> exercises = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection = {
                ExerciseDatabaseHelper.COLUMN_ID,
                ExerciseDatabaseHelper.COLUMN_NAME,
                ExerciseDatabaseHelper.COLUMN_DESCRIPTION,
                ExerciseDatabaseHelper.COLUMN_DIFFICULTY,
                ExerciseDatabaseHelper.COLUMN_MUSCLE_GROUP,
                ExerciseDatabaseHelper.COLUMN_SETS,
                ExerciseDatabaseHelper.COLUMN_REPS,
                ExerciseDatabaseHelper.COLUMN_IMAGE
        };
        String selection = ExerciseDatabaseHelper.COLUMN_DIFFICULTY + " = ?";
        String[] selectionArgs = { String.valueOf(ExerciseDatabaseHelper.COLUMN_DIFFICULTY) };

        Cursor cursor = db.query(
                ExerciseDatabaseHelper.TABLE_EXERCISES,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_NAME));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_DESCRIPTION));
                @SuppressLint("Range") int sets = cursor.getInt(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_SETS));
                @SuppressLint("Range") int reps = cursor.getInt(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_REPS));
                @SuppressLint("Range") int imageID = cursor.getInt(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_IMAGE));
                @SuppressLint("Range") int difficulty = cursor.getInt(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_DIFFICULTY));
                @SuppressLint("Range") String muscleGroup = cursor.getString(cursor.getColumnIndex(ExerciseDatabaseHelper.COLUMN_MUSCLE_GROUP));

                Exercise exercise = new Exercise();
                exercise.setId(String.valueOf(id));
                exercise.setImageID(imageID);
                exercise.setDesc(description);
                exercise.setRepCount(reps);
                exercise.setSetCount(sets);
                exercise.setName(name);
                exercise.setDifficulty(difficulty);
                exercise.setMuscleGroup(muscleGroup);


                exercises.add(exercise);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return exercises;
    }


}
