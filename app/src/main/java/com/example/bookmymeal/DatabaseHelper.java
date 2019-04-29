package com.example.bookmymeal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context,"cart.sqlite",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String sql = "create table cart(userid integer,name varchar(100),price int,foodItemId int)";
      db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
