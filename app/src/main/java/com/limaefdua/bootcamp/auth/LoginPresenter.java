package com.limaefdua.bootcamp.auth;
//
// Created by maftuhin on 10/17/2019.
//

import android.content.Context;

import com.limaefdua.bootcamp.room.BcDatabase;
import com.limaefdua.bootcamp.room.table.User;
import com.limaefdua.bootcamp.sqlite.SQLiteDB;

public class LoginPresenter {
    private LoginView view;
    private BcDatabase dbRoom;
//    private SQLiteDB db;

    public LoginPresenter(Context context, LoginView view) {
        this.view = view;
        dbRoom = BcDatabase.getDatabase(context);
//        db = new SQLiteDB(context);
    }

    void doLogin(String username, String password) {
        User user = dbRoom.userDao().userLogin(username, password);
//        User user = db.login(username, password);
        if (user != null) {
            view.onLoginSuccess(user.username);
        } else {
            view.onError("Wrong username or password!");
        }
    }
}
