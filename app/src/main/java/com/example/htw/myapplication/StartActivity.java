package com.example.htw.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.login).setOnClickListener(this::onLogin);
        findViewById(R.id.register).setOnClickListener(this::onRegister);
        findViewById(R.id.jsonFragmentButton).setOnClickListener(this::onJsonFragment);
        findViewById(R.id.sqLiteButton).setOnClickListener(this::onSqLiteActitivty);
        findViewById(R.id.sqLiteButtonActivAndroid).setOnClickListener(this::onSqLiteActitivtySimplyWay);


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
        Intent intent = new Intent();
    }

    private void onLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
