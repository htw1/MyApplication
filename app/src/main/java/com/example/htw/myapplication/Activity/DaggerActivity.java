package com.example.htw.myapplication.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.htw.myapplication.DataStorage.MyApplication;
import com.example.htw.myapplication.R;
import com.example.htw.myapplication.dagger.DaggerApplication;
import com.example.htw.myapplication.dagger.MyComponent;
import com.example.htw.myapplication.dagger.MySharedPreferences;

public class DaggerActivity extends AppCompatActivity {

    MySharedPreferences mySharedPreferences;
    MyComponent myComponent;

    TextView textView;
    Button button;
    EditText editText;
    public static final String PREFERENCES_KEY = "my_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

/*        ((MyApplication)getApplicationContext()).getMyComponent().inject(this);*/




        textView = (TextView) findViewById(R.id.daggerTextView);
        editText = (EditText) findViewById(R.id.daggerEditText);
        findViewById(R.id.daggerButton).setOnClickListener(this::saveData);

    }

    private void saveData(View view) {
        String valueFromEditText = editText.getText().toString();



    }
}
