package com.example.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  Communicator{

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer_inactivity_main);
        // passing toolbar from MainActivity TO NavigationDrawerFragment
        navigationDrawerFragment.setUp(R.id.fragment_navigation_drawer_inactivity_main,(DrawerLayout)findViewById(R.id.drawerLayout), toolbar );



        showSingleFragment();

        editText =(EditText) findViewById(R.id.editText);
        findViewById(R.id.buttonNext).setOnClickListener(this::nextActivity);
        findViewById(R.id.save).setOnClickListener(this::saveName);

    }



    public void saveName (View view){

        Toast.makeText(getApplicationContext(), "DATA SAVE", Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = getSharedPreferences("DANE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",editText.getText().toString() );
        editor.commit();

    }

    public void nextActivity (View v){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }



    public void showSingleFragment() {
        MyFragment myFragment = new MyFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_main_contn, myFragment, "MyFragment");
        fragmentTransaction.commit();

    };




    @Override
    public void respond(String data) {

        FragmentManager manager = getFragmentManager();
        FragmentB fragmentB= (FragmentB) manager.findFragmentById(R.id.idFragmentFromActivity);
        fragmentB.changeText(data);
    }





}
