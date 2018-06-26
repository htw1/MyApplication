package com.example.htw.myapplication.DataStorage;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.htw.myapplication.dagger.ApplicationComponent;

import com.example.htw.myapplication.dagger.DaggerApplicationComponent;
import com.example.htw.myapplication.dagger.SharedPreferencesModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends MultiDexApplication {



  private ApplicationComponent component;

  @Override
  public void onCreate() {
    super.onCreate();



    Realm.init(this);
    RealmConfiguration config = new RealmConfiguration.Builder()
            .name("myrealm.realm")
            .deleteRealmIfMigrationNeeded()
            .build();
    Realm.setDefaultConfiguration(config);


      component = DaggerApplicationComponent.builder()
              .sharedPreferencesModule(new SharedPreferencesModule(getApplicationContext()))
              .build();
  }
  public ApplicationComponent getComponent() {
    return component;
  }

}