package com.example.htw.myapplication.dagger;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    private Context context;


    public SharedPreferencesModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext(){
        return context;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences() {
        return context.getSharedPreferences("PrefName",Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    MySharedPreferences provideMySharedPreferences(SharedPreferences sharedPreferences) {
                return new MySharedPreferences(sharedPreferences);
            }
}