package com.example.htw.myapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.htw.myapplication.DataStorage.DataBaseSqLite;

public class SqLiteActivity extends AppCompatActivity {
    EditText editId;
    EditText editName;
    EditText editSurname;
    EditText editMarks;
    Button btnAddData;
    Button btnShowData;
    Button btnUpdateData;
    DataBaseSqLite myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite);

        myDataBase = new DataBaseSqLite(this);

        editId= (EditText) findViewById(R.id.dataBaseId);
        editName = (EditText) findViewById(R.id.dataBaseName);
        editSurname = (EditText) findViewById(R.id.dataBaseSurname);
        editMarks = (EditText) findViewById(R.id.dataBasemarks);
        btnAddData = (Button) findViewById(R.id.dataBaseButton);
        btnShowData = (Button) findViewById(R.id.dataBaseShow);
        btnUpdateData = (Button)findViewById(R.id.dataBaseUpdate) ;
        addData();
        showDate ();
    }
    public void showDate (){
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor resault = myDataBase.getAllDate();

                if (resault.getCount() ==  0){
                    showMessage("ERROR", "NOTHING TO SHOW");

                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (resault.moveToNext()){
                    buffer.append("Id :" + resault.getString(0)+"\n");
                    buffer.append("name :" + resault.getString(1)+"\n");
                    buffer.append("surname :" + resault.getString(2)+"\n");
                    buffer.append("marks :" + resault.getString(3)+"\n\n");
                }
                //shoe all data
                showMessage("DATA", buffer.toString());
            }
        });
    }
        public void addData () {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean isInserted =  myDataBase.insertData(
                        editName.getText().toString(),
                        editSurname.getText().toString(),
                        editMarks.getText().toString() );
                if (isInserted == true)
                    Toast.makeText(SqLiteActivity.this, "success", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SqLiteActivity.this, "wrong data insert", Toast.LENGTH_LONG).show();
            }
        });

    };

    public void updateData () {
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            boolean isUpdated = myDataBase.updateData(
                    editId.getText().toString(),
                    editName.getText().toString(),
                    editSurname.getText().toString(),
                    editMarks.getText().toString() );
                if (isUpdated == true)
                    Toast.makeText(SqLiteActivity.this, "success", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SqLiteActivity.this, "wrong update", Toast.LENGTH_LONG).show();

            }
        });

    };
    public void showMessage (String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }


}
