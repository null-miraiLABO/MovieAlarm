package com.example.moviealarm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimeDbHelper extends SQLiteOpenHelper{
    public TimeDbHelper(Context context){
        super(context,TimeContract.DB_NAME,null,TimeContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable="CREATE TABLE "+TimeContract.TimeEntry.TABLE+" ( "+
                TimeContract.TimeEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TimeContract.TimeEntry.COL_TASK_TITLE+" TEXT NOT NULL)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TimeContract.TimeEntry.TABLE);
        onCreate(db);
    }
}
