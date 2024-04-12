package com.example.mysplashscreen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExerciseDatabaseHelper extends  SQLiteOpenHelper {

    private static ExerciseDatabaseHelper instance;

    private static final String DATABASE_NAME = "exercise_database";
    private static final int DATABASE_VERSION = 4;

    public static final String TABLE_EXERCISES = "exercises";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE = "imageID";
    public static final String  COLUMN_SETS = "sets";
    public static final String  COLUMN_REPS = "reps";
    public static final String COLUMN_DIFFICULTY = "difficulty";
    public static final String COLUMN_MUSCLE_GROUP = "muscleGroup";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_EXERCISES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DESCRIPTION + "TEXT, " +
                    COLUMN_IMAGE + " INTEGER, "+
                    COLUMN_SETS+ "INTEGER," +
                    COLUMN_REPS+ "INTEGER," +
                    COLUMN_DIFFICULTY + " INTEGER, " +
                    COLUMN_MUSCLE_GROUP + " TEXT" + ")";

    public ExerciseDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void deleteAllEntries() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.beginTransaction();
            db.delete("exercises", null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("DatabaseError", "Error clearing the database", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 4) {
            db.execSQL("ALTER TABLE exercises ADD COLUMN sets INTEGER");
        }
    }



}
