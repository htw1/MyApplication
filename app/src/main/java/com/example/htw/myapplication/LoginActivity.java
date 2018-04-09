package com.example.htw.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.htw.myapplication.R;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.sign).setOnClickListener(this::signIn);
        findViewById(R.id.button_login).setOnClickListener(this::loginButton);
    }

    private void loginButton(View view) {
    }

    private void signIn(View view) {


    }
}
