package com.task.socialapp;



import android.app.Application;

import com.task.socialapp.repositrories.AppSharedPreferences;
import com.task.socialapp.util.Utils;

import okhttp3.internal.Util;


public class MyApplication extends Application {

    public static AppSharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = AppSharedPreferences.getInstance(this);

    }
}
