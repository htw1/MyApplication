package com.example.htw.myapplication.dagger;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class MySharedPreferences {

    private SharedPreferences mSharedPreferences;

    @Inject
    public MySharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public void putData(String key, int data) {
        mSharedPreferences.edit().putInt(key,data).apply();
    }

    public int getData(String key) {
        return mSharedPreferences.getInt(key,0);
    }
}