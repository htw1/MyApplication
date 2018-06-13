package com.example.htw.myapplication.DataStorage;

import android.app.Application;

import com.example.htw.myapplication.dagger.MyComponent;
import com.example.htw.myapplication.dagger.SharedPreferencesModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

  private MyComponent component;

  @Override
  public void onCreate() {
    super.onCreate();
    // The default Realm file is "default.realm" in Context.getFilesDir();
    // we'll change it to "myrealm.realm"
    Realm.init(this);
    RealmConfiguration config = new RealmConfiguration.Builder()
            .name("myrealm.realm")
            .deleteRealmIfMigrationNeeded()
            .build();
    Realm.setDefaultConfiguration(config);



      component = DaggerMyComponent.builder()
              .sharedPreferencesModule(new SharedPreferencesModule(getApplicationContext()))
              .build();

/*    public MyComponent getMyComponent() {
      return component;

  }*/

  }
}