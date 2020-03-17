package com.limaefdua.bootcamp.app;

//
// Created by maftuhin on 10/23/2019.
//

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
