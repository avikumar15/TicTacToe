package com.example.spidertask2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Result.db";
    public static final String TABLE_NAME = "Result";
    public static final String COL_1 = "Sr_No";
    public static final String COL_2 = "GAME_MODE";
    public static final String COL_3 = "TIME_TAKEN";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Result (Sr_No INTEGER PRIMARY KEY AUTOINCREMENT, GAME_MODE TEXT, TIME_TAKEN INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String GAME_MODE,int TIME_TAKEN) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,GAME_MODE);
        contentValues.put(COL_3,TIME_TAKEN);
        long result = db.insert(TABLE_NAME,null ,contentValues);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

}
