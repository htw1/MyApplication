package com.example.htw.myapplication.DataStorage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferencesConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharePreferencesConfig( Context context) {
        this.sharedPreferences = context.getSharedPreferences("login sharePreferences",Context.MODE_PRIVATE );
        this.context = context;
    }


    public void wriveLoginStatus (boolean status){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Login Status preferences",status );
        editor.commit();
    }

    public boolean readLoginStatus (){

        boolean status = false;
        status = sharedPreferences.getBoolean("Login Status preferences", false);

        return  status;
    }

}
