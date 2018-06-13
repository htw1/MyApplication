package com.example.htw.myapplication.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.htw.myapplication.DataStorage.ViewModelData;
import com.example.htw.myapplication.Mvp.LogicRegistacion;
import com.example.htw.myapplication.Mvp.RegMethodView;
import com.example.htw.myapplication.Mvp.RegisterView;
import com.example.htw.myapplication.R;

public class MVPActivity extends AppCompatActivity implements RegMethodView {

    public static final String NAME = "name" ;
    EditText name;
    EditText surname;
    RegisterView presenter;
    ViewModelData model;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);

        String storedName = name.getText().toString();
        String storedLastName = surname.getText().toString();

        findViewById(R.id.button).setOnClickListener(this::button);
       // model = ViewModelProviders.of(this).get(ViewModelData.class);
        presenter = new LogicRegistacion(MVPActivity.this);
       // model.getCurrentName().setValue(storedName);


    }

    private void button(View view) {
       // model.getCurrentName().setValue(name.getText().toString());
        presenter.registerIn(name.getText().toString(), surname.getText().toString());
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Fields are empty !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean regSuccess() {
        //model.getCurrentName().setValue(name.getText().toString());
        Intent intent = new Intent(new Intent(this, NewActivityAfterLogin.class));
        String message = name.getText().toString();
        intent.putExtra(NAME, message );
        startActivity(intent);

        return true;
    }

    @Override
    public void start(View view) {
        //startActivity(new Intent(this, NewActivityAfterLogin.class));
    }


}
