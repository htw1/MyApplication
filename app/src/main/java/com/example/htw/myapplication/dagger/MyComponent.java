package com.example.htw.myapplication.dagger;

import android.content.SharedPreferences;
import com.example.htw.myapplication.Activity.DaggerActivity;

import dagger.BindsInstance;
import dagger.Component;

@Component( modules = {SharedPreferencesModule.class })
public interface MyComponent {


    // MIEJSCE GDZIE BĘDZIEMY WSTRZYKIWAĆ ZALEŻNOŚCI
    void inject (DaggerActivity daggerActivity);



}
