package com.example.mysplashscreen;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ExercisePlan {
    private ExerciseDatabaseHelper dbHelper;

    public ExercisePlan(Context context)  {
        this.dbHelper = new ExerciseDatabaseHelper(context);
    }

    public long insertExercise(Exercise exercise) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ExerciseDatabaseHelper.COLUMN_ID, exercise.getId());
        values.put(ExerciseDatabaseHelper.COLUMN_NAME, exercise.getName());
        values.put(ExerciseDatabaseHelper.COLUMN_DESCRIPTION, exercise.getDesc());
        values.put(ExerciseDatabaseHelper.COLUMN_IMAGE, exercise.getImageID());
        values.put(ExerciseDatabaseHelper.COLUMN_REPS, exercise.getRepCount());
        values.put(ExerciseDatabaseHelper.COLUMN_SETS, exercise.getSetCount());
        values.put(ExerciseDatabaseHelper.COLUMN_MUSCLE_GROUP, exercise.getMuscleGroup());
        values.put(ExerciseDatabaseHelper.COLUMN_DIFFICULTY, exercise.getDifficulty());


        long id = db.insert(ExerciseDatabaseHelper.TABLE_EXERCISES, null, values);
        db.close();
        return id;
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
