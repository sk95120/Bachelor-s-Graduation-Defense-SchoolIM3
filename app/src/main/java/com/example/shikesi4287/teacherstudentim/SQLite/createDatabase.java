package com.example.shikesi4287.teacherstudentim.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class createDatabase  extends SQLiteOpenHelper {
    private static final String sql = "create table info(_id integer primary key autoincrement,name varchar(20),stuid varchar(20))";

    public createDatabase(Context context) {
        //数据库版本改变，版本做出改变
        super(context,"SchoolIM.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //向某表添加字段
    }
}
