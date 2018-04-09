package com.example.htw.myapplication.DataStorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseSqLite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL1 = "ID" ;
    public static final String COL2 = "NAME";
    public static final String COL3 = "SURNAME";
    public static final String COL4 = "MARKS";

    public DataBaseSqLite(Context context) {
        super(context, DATABASE_NAME,   null, 1);
       // SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, NAME TEXT, SURNAME TEXT, MARKS INTEGER)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME );
        onCreate(db);

    }

    public boolean insertData (String name, String surname, String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2 , name);
        contentValues.put(COL3 , surname);
        contentValues.put(COL4 , marks);
        long resoult = db.insert(TABLE_NAME, null,contentValues);
        if (resoult == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean updateData (String id,String name, String surname, String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1 , id);
        contentValues.put(COL2 , name);
        contentValues.put(COL3 , surname);
        contentValues.put(COL4 , marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String [] {id} );
      return  true;
    }

    public boolean deleteData (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,"ID = ?", new String[] {id});
        return true;

    }




    public Cursor getAllDate ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resault = db.rawQuery("select * from  "+ TABLE_NAME, null);
        return resault;
    }

}
