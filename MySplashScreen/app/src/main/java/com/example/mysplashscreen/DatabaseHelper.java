package com.example.mysplashscreen;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "SignLog.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "SignLog.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(email TEXT primary key, username TEXT,password TEXT, coins INTEGER DEFAULT 0, XP INTEGER DEFAULT 0, last_login_time INTEGER DEFAULT 0, login_streak INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public void updateLoginStreak(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        long currentTimeMillis = System.currentTimeMillis();

        long lastLoginTime = getLastLoginTime(email);

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTimeInMillis(currentTimeMillis);

        Calendar lastLoginCalendar = Calendar.getInstance();
        lastLoginCalendar.setTimeInMillis(lastLoginTime);


        if (currentCalendar.get(Calendar.DAY_OF_YEAR) == lastLoginCalendar.get(Calendar.DAY_OF_YEAR)
                && currentCalendar.get(Calendar.YEAR) == lastLoginCalendar.get(Calendar.YEAR)) {

            int currentStreak = User.getInstance().getLoginStreak();
            contentValues.put("login_streak", currentStreak + 1);
        } else {

            contentValues.put("login_streak", 1);
        }

        // Update last login time
        contentValues.put("last_login_time", currentTimeMillis);

        MyDatabase.update("users", contentValues, "email=?", new String[]{email});

        // Update User instance with updated login streak
        User.getInstance().setLoginStreak(contentValues.getAsInteger("login_streak"));
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

    public User getUserByEmail(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE email=?", new String[]{email});

        User user = null;
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));

            user = new User();

        }

        cursor.close();
        return user;
    }

    // Method to get user by username (if needed)
    public User getUserByUsername(String username) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});

        User user = null;
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));

            user = new User();

        }

        cursor.close();
        return user;
    }
    @SuppressLint("Range")
    public int getXPByEmail(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT XP FROM users WHERE email=?", new String[]{email});

        int xp = 0;
        if (cursor.moveToFirst()) {
            xp = cursor.getInt(cursor.getColumnIndex("XP"));
        }

        cursor.close();

        // Update User instance with XP
        User.getInstance().setXp(xp);

        return xp;
    }


    @SuppressLint("Range")
    public int getCoinsByEmail(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT coins FROM users WHERE email=?", new String[]{email});

        int coins = 0;
        if (cursor.moveToFirst()) {
            coins = cursor.getInt(cursor.getColumnIndex("coins"));
        }

        cursor.close();

        // Update User instance with coins
        User.getInstance().setCoins(coins);

        return coins;
    }

    @SuppressLint("Range")
    public int getLoginStreakByEmail(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT login_streak FROM users WHERE email=?", new String[]{email});

        int loginStreak = 0;
        if (cursor.moveToFirst()) {
            loginStreak = cursor.getInt(cursor.getColumnIndex("login_streak"));
        }

        cursor.close();

        // Update User instance with login streak
        User.getInstance().setLoginStreak(loginStreak);

        return loginStreak;
    }

}


