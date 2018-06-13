package com.example.htw.myapplication.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.htw.myapplication.DataStorage.ViewModelData;
import com.example.htw.myapplication.Fragments.FragmentBottom;
import com.example.htw.myapplication.Fragments.FragmentTop;
import com.example.htw.myapplication.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewActivityAfterLogin extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_after_login);

        textView = findViewById(R.id.logenPerson);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MVPActivity.NAME);
        textView.setText(message);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_for_top, new FragmentTop())
                .replace(R.id.container_for_bottom, new FragmentBottom())
                .commit();

        ViewModelData.getData().observe(this, time -> {


            //
            // Display a date in day, month, year format
            //
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String today = formatter.format(time);

            ( (TextView)findViewById(R.id.time_t)).setText("loged at: " + today);

        });
    }



    public void getTime (View view) {
        ViewModelData.getData();
    }


}
