package com.example.htw.myapplication.dagger;
import android.content.SharedPreferences;


public class MySharedPreferences {

    private SharedPreferences mSharedPreferences;

    MySharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public void putData(String key, String data) {
        mSharedPreferences.edit().putString(key,data).apply();
    }

    public String getData(String key) {
        return mSharedPreferences.getString(key,"");
    }
}