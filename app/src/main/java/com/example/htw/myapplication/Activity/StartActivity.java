package com.example.htw.myapplication.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.htw.myapplication.Fragments.NavigationDrawerFragment;
import com.example.htw.myapplication.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer_inactivity_main);
        // passing toolbar from MainActivity TO NavigationDrawerFragment
        navigationDrawerFragment.setUp(R.id.fragment_navigation_drawer_inactivity_main,(DrawerLayout)findViewById(R.id.drawerLayout), toolbar );


        findViewById(R.id.login).setOnClickListener(this::onLogin);
        findViewById(R.id.register).setOnClickListener(this::onRegister);
        findViewById(R.id.jsonFragmentButton).setOnClickListener(this::onJsonFragment);
        findViewById(R.id.sqLiteButton).setOnClickListener(this::onSqLiteActitivty);
        findViewById(R.id.sqLiteButtonActivAndroid).setOnClickListener(this::onSqLiteActitivtySimplyWay);
        findViewById(R.id.gallery).setOnClickListener(this::onGallery);
        findViewById(R.id.firebase).setOnClickListener(this::onFirebase);
        findViewById(R.id.sunsetFoto).setOnClickListener(this::onFirebasePhoto);

    }

    private void onFirebasePhoto(View view) {
        Intent intent = new Intent(this, PhotoActivity.class);
        startActivity(intent);
    }

    private void onFirebase(View view) {
        Intent intent = new Intent(this, FirebaseActivity.class);
        startActivity(intent);
    }

    private void onGallery(View view) {
        Intent intent = new Intent(this, DaggerActivity.class);
        startActivity(intent);
    }

    private void onSqLiteActitivtySimplyWay(View view) {
        Intent intent = new Intent(this, SqLiteRealmActivity.class);
        startActivity(intent);

    }

    private void onSqLiteActitivty(View view) {

        Intent intent = new Intent(this, SqLiteActivity.class);
        startActivity(intent);

    }

    private void onJsonFragment(View view) {
        Intent intent = new Intent(StartActivity.this, JsonActivity.class);
        startActivity(intent);
    }


    private void onRegister(View view) {
        Intent intent = new Intent(this, MVPActivity.class);
        startActivity(intent);
    }

    private void onLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
