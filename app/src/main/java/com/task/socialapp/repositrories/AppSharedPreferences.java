package com.task.socialapp.repositrories;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreferences {

    private static SharedPreferences preferences;
    private static AppSharedPreferences instance;

    private static Context context;

    private AppSharedPreferences(Context context) {
        AppSharedPreferences.context = context;
        createInstance();
    }

    private static void createInstance() {
        if (preferences == null) {
            synchronized (AppSharedPreferences.class) {
                if (preferences == null) {
                    preferences = context.getSharedPreferences(context
                            .getPackageName(), Context.MODE_PRIVATE);
                }
            }
        }
    }

    public static AppSharedPreferences getInstance(Context context) {
        if (instance == null) {
            synchronized (AppSharedPreferences.class) {
                if (instance == null) {
                    instance = new AppSharedPreferences(context);
                }
            }
        }
        return instance;
    }

    public void setLoginDetails(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public boolean getLoginDetails(String key, boolean elseValue) {
        return preferences.getBoolean(key, elseValue);
    }

    public void setStringPreference(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public String getStringSharedPreference(String key, String elseValue) {
        return preferences.getString(key, elseValue);
    }


    public void flushUser() {
        preferences.edit().clear().apply();
    }
}
