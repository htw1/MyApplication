package com.example.htw.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.htw.myapplication.Model.Student;

import io.realm.Realm;
import io.realm.RealmResults;

public class SqLiteRealmActivity extends AppCompatActivity  {

    EditText editName;
    EditText editMarks;
    TextView txtToShow;
    Button btnSaveData;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite_crud);

        txtToShow = (TextView) findViewById(R.id.txtShowData);
        realm=Realm.getDefaultInstance();

        editName = (EditText) findViewById(R.id.dataBaseName);
        editMarks = (EditText) findViewById(R.id.dataBasemarks);
        findViewById(R.id.dataBaseButtonSave).setOnClickListener(this::saveData);
    }

    private void saveData(View view) {
        writeToDataBase (editName.getText().toString(), Integer.parseInt(editMarks.getText().toString().trim()));
        ShowData ();
    }

    private void ShowData() {
        // Query Realm for all dogs younger than 2 years old
        final RealmResults<Student> studentsArray = realm.where(Student.class).findAll();
        studentsArray.size(); // => 0 because no dogs have been added to the Realm yet
        String output = "";

// Persist your data in a transaction
        realm.beginTransaction();

        for (Student temp : studentsArray  ){
            output = output + studentsArray;
        }
        txtToShow.setText(output);

        realm.commitTransaction();
    }


    private void writeToDataBase(String name, int marks) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                //MODEL
                Student student = bgRealm.createObject(Student.class);
                student.setName(name);
                student.setMarks(marks);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.v("Database", "success");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.e("Database",error.getMessage() );
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


}
