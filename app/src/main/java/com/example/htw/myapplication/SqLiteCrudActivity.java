package com.example.htw.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;



public class SqLiteCrudActivity extends AppCompatActivity {

    EditText editName;
    EditText editSurname;
    EditText editMarks;
    Button btnAddData;
    Button btnShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite_crud);

       // ActiveAndroid

        editName = (EditText) findViewById(R.id.dataBaseName);
        editSurname = (EditText) findViewById(R.id.dataBaseSurname);
        editMarks = (EditText) findViewById(R.id.dataBasemarks);
        btnAddData = (Button) findViewById(R.id.dataBaseButton);
        btnShowData = (Button) findViewById(R.id.dataBaseShow);

    }
}
