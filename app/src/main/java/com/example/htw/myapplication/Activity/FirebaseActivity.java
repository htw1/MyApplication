package com.example.htw.myapplication.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.htw.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.ArrayList;

public class FirebaseActivity extends AppCompatActivity {

    DatabaseReference rootRefUsers = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myapplication-fb899.firebaseio.com/Users");

    private ArrayList <String> listArray = new ArrayList<>();

    String NamefromEditText;
    String KeyFromEditText;

    EditText editTextName;
    EditText editTextKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        editTextName =  findViewById(R.id.editTextFirebaseName);
        editTextKey =findViewById (R.id.editTextFirebaseKey);

        findViewById(R.id.sendDada).setOnClickListener(this::sendData);

    }

    private void sendData(View view) {

        ArrayAdapter <String> arrayListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listArray);

        NamefromEditText = editTextName.getText().toString().trim();
        KeyFromEditText = editTextKey.getText().toString().trim();

        DatabaseReference addUser  = rootRefUsers.child(KeyFromEditText);
        addUser.setValue(NamefromEditText);

        rootRefUsers.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);

                listArray.add(value);
                arrayListAdapter.notifyDataSetChanged();
                Toast.makeText(FirebaseActivity.this, "DATA SENT", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
