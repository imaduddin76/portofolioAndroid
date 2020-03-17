package com.limaefdua.bootcamp.utils;
//
// Created by maftuhin on 10/18/2019.
//

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private Context context;
    private SharedPreferences sp;

    public Preferences(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public void setIsLogin(boolean login) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isLogin", login);
        edit.apply();
    }

    public boolean getIsLogin() {
        return sp.getBoolean("isLogin", false);
    }

    public void setUsername(String username){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("username", username);
        edit.apply();
    }

    public String getUsername(){
        return sp.getString("username","Anonymous");
    }
}
