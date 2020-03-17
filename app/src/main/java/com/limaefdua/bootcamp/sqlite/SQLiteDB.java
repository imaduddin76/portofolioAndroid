package com.limaefdua.bootcamp.sqlite;
//
// Created by maftuhin on 10/23/2019.
//

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.limaefdua.bootcamp.room.table.User;

public class SQLiteDB extends SQLiteOpenHelper {

    private static String dbName = "bc-sqlite";
    private static final String tblUser = "CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT)";
    private SQLiteDatabase db;

    public SQLiteDB(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        sqlDB.execSQL(tblUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int oldVersion, int newVersion) {

    }

    public long register(User user) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", user.username);
        cv.put("password", user.password);
        cv.put("email", user.email);
        return db.insert("user", null, cv);
    }

    public User login(String username, String password) {
        User user = null;
        db = this.getWritableDatabase();
        String sql = "SELECT * FROM user WHERE username=? AND password=? LIMIT 1";
        Cursor c = db.rawQuery(sql, new String[]{username, password});
        if (c.moveToFirst()) {
            user = new User();
            user.id = c.getInt(0);
            user.username = c.getString(1);
            user.password = c.getString(2);
            user.email = c.getString(3);
        } else {
            Log.w("asda", "tidak ditemukan");
        }
        c.close();
        return user;
    }
}
