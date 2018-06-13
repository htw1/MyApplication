package com.example.htw.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import com.example.htw.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.log_out).setOnClickListener(this::logOut);

    }

    private void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
    }
}
