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
import com.example.htw.myapplication.dagger.ApplicationComponent;
import com.example.htw.myapplication.dagger.MySharedPreferences;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    @Inject
    MySharedPreferences mySharedPreferences;


    TextView textView;
    EditText editText;
    public static final String PREFERENCES_KEY = "my_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        ((MyApplication)getApplicationContext()).getComponent().inject(this);

        textView = (TextView) findViewById(R.id.daggerTextView);
        editText = (EditText) findViewById(R.id.daggerEditText);
        findViewById(R.id.daggerButton).setOnClickListener(this::saveData);

    }
      // DAGGER WORK
    private void saveData(View view) {
        String valueFromEditText = editText.getText().toString();
        mySharedPreferences.putData(PREFERENCES_KEY,valueFromEditText );

        String lastValue = mySharedPreferences.getData(PREFERENCES_KEY);
        textView.setText(lastValue);

    }
}
