package com.example.mysplashscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "SignLog.db";

    // this line indicates a constant string 'databaseName' which holds the name of the database
    public DatabaseHelper(@Nullable Context context) {
        super(context, "SignLog.db", null, 1);
    }
// this is the class constructor, inits DBhelper, calls superclass constructor SQLiteOpen helper

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(email TEXT primary key, username TEXT,password TEXT, coins INTEGER DEFAULT 0, XP INTEGER DEFAULT 0, last_login_date TEXT)");
    }
    //  this method is called when the database is created for the first time
    // executes SQL statement to create a table named 'users' with columns above

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }
    //This method is called when the database needs to be upgraded. It drops the existing users table if it exists.

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
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        return cursor.getCount() > 0;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where username= ?", new String[]{username});

        return cursor.getCount() > 0;
    }


    public Boolean checkEmailPassword(String emailOrUsername, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where (email = ? or username = ?) and password = ?", new String[]{emailOrUsername, emailOrUsername, password});

        return cursor.getCount() > 0;
    }

}
