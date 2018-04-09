package com.example.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {


    public static final String DEFAULT = "N/A";
    TextView showUserNameField;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listView = findViewById(R.id.listView);
        showUserNameField = findViewById(R.id.showMyNameTxt);
        findViewById(R.id.loadNameBtn).setOnClickListener(this::loadMyName);
        findViewById(R.id.eraseData).setOnClickListener(this::eraceMyData);
        findViewById(R.id.go_tologin_activity).setOnClickListener(this::goToLogin);


        String [] nameListView = {"raz","dwa", "trzy","cztery"};
        Integer [] imageListView = {R.drawable.ic_group_black_24dp,R.drawable.ic_group_black_24dp
                ,R.drawable.ic_group_black_24dp,R.drawable.ic_group_black_24dp};



        ListViewAdapter adapter = new ListViewAdapter(SecondActivity.this,nameListView,imageListView);
        listView.setAdapter(adapter);




    }

    private void goToLogin(View view) {

       // Intent intent = new Intent(SecondActivity.this,   );

    }


    private void eraceMyData(View view) {

    SharedPreferences sharedPreferences = getSharedPreferences("DANE", Context.MODE_PRIVATE);
    //String name = getSharedPreferences("name", DEFAULT);
        sharedPreferences.edit().remove("name").commit();
    }

    private void loadMyName(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("DANE", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", DEFAULT);

        if(name.isEmpty()) {
            Toast.makeText(this, "BRAK DANYCH", Toast.LENGTH_LONG);
        }
        else {
            showUserNameField.setText(name);
        }





    }
}




