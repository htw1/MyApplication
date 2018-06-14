package com.example.htw.myapplication.dagger;

import android.content.SharedPreferences;
import com.example.htw.myapplication.Activity.DaggerActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component( modules = {SharedPreferencesModule.class })
public interface ApplicationComponent {


    // MIEJSCE GDZIE BĘDZIEMY WSTRZYKIWAĆ ZALEŻNOŚCI
    void inject (DaggerActivity daggerActivity);



}
