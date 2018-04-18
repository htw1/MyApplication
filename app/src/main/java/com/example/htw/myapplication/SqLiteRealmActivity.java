package com.example.htw.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htw.myapplication.Model.Student;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

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

        realm=Realm.getDefaultInstance();

        txtToShow = (TextView) findViewById(R.id.txtShowData);
        editName = (EditText) findViewById(R.id.dataBaseName);
        editMarks = (EditText) findViewById(R.id.dataBasemarks);
        findViewById(R.id.dataBaseButtonSave).setOnClickListener(this::saveData);
    }

    private void saveData(View view) {

        //writeToDataBase (editName.getText().toString(), Integer.parseInt(editMarks.getText().toString().trim()));
        saveData();
        ShowData ();
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

    private void saveData (){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Student studentObj = bgRealm.createObject(Student.class);

                studentObj.setName(editName.getText().toString().trim());
                studentObj.setMarks(Integer.parseInt(editMarks.getText().toString()));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(SqLiteRealmActivity.this, "success", Toast.LENGTH_SHORT).show();
                // Transaction was a success.
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {


                // Transaction failed and was automatically canceled.
            }
        });

    }



    private void ShowData() {
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




    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


}
