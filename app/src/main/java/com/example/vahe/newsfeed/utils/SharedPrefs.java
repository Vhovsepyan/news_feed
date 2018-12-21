package com.example.vahe.newsfeed.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static final String PREF_NAME = "PREF_NAME";
    private static final SharedPrefs sharedPrefs = new SharedPrefs();
    private static SharedPreferences mMyPreferences;

    private SharedPrefs() {
    }

    public static void initialize(Context context) {
        mMyPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPrefs getInstance() {
        return sharedPrefs;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor e = mMyPreferences.edit();
        e.putString(key, value);
        e.apply();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor e = mMyPreferences.edit();
        e.putInt(key, value);
        e.apply();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor e = mMyPreferences.edit();
        e.putBoolean(key, value);
        e.apply();
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor e = mMyPreferences.edit();
        e.putLong(key, value);
        e.apply();
    }

    public String getString(String key, String value) {
        return mMyPreferences.getString(key, value);
    }

    public int getInt(String key, int value) {
        return mMyPreferences.getInt(key, value);
    }

    public boolean getBoolean(String key, Boolean value) {
        return mMyPreferences.getBoolean(key, value);
    }

}
