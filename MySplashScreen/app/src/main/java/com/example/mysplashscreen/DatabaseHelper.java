package com.example.mysplashscreen;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "SignLog.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(email TEXT primary key, username TEXT,password TEXT, coins INTEGER DEFAULT 0, XP INTEGER DEFAULT 0, last_login_time INTEGER DEFAULT 0, login_streak INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = MyDatabase.insert("users", null, contentValues);

        return result != -1;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        return cursor.getCount() > 0;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = MyDatabase.rawQuery("Select * from users where username= ?", new String[]{username});

        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String emailOrUsername, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = MyDatabase.rawQuery("Select * from users where (email = ? or username = ?) and password = ?", new String[]{emailOrUsername, emailOrUsername, password});

        return cursor.getCount() > 0;
    }

    public void updateLoginStreak(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Get current time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        // Get user's last login time
        long lastLoginTime = getLastLoginTime(email);

        // Get current date and time
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(currentTimeMillis);

        // Get date and time of last login
        Calendar lastLoginCalendar = Calendar.getInstance();
        lastLoginCalendar.setTimeInMillis(lastLoginTime);

        // Check if current login is within 24 hours of last login
        if (currentCalendar.get(Calendar.DAY_OF_YEAR) == lastLoginCalendar.get(Calendar.DAY_OF_YEAR)
                && currentCalendar.get(Calendar.YEAR) == lastLoginCalendar.get(Calendar.YEAR)) {
            // If it's the same day, increment login streak
            int currentStreak = getLoginStreak(email);
            contentValues.put("login_streak", currentStreak + 1);
        } else {
            // If it's a new day, reset login streak to 1
            contentValues.put("login_streak", 1);
        }

        // Update last login time
        contentValues.put("last_login_time", currentTimeMillis);

        int rowsAffected = MyDatabase.update("users", contentValues, "email=?", new String[]{email});
    }

    private long getLastLoginTime(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT last_login_time FROM users WHERE email=?", new String[]{email});
        long lastLoginTime = 0;
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("last_login_time");
            if (columnIndex != -1) {
                lastLoginTime = cursor.getLong(columnIndex);
            } else {
                Log.e("DatabaseHelper", "Column 'last_login_time' not found in query result");
            }
        } else {
            Log.e("DatabaseHelper", "No data found for email: " + email);
        }
        cursor.close();
        return lastLoginTime;
    }

    public int getLoginStreak(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT login_streak FROM users WHERE email=?", new String[]{email});
        int loginStreak = 1; // Set default login streak to 1

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("login_streak");
            if (columnIndex != -1) {
                loginStreak = cursor.getInt(columnIndex);
            } else {
                Log.e("DatabaseHelper", "Column 'login_streak' not found in query result");
            }
        } else {
            Log.e("DatabaseHelper", "No data found for email: " + email);
        }

        cursor.close();
        return loginStreak;
    }

    public String getCurrentUserEmail(Context context) {
        // Replace this with your own logic to retrieve the current user's email
        SharedPreferences sharedPreferences = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("currentUserEmail", "");
    }
}
}
