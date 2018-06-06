package com.example.shikesi4287.teacherstudentim.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseInfo {
    private createDatabase dbHelperSqlite;
    public DataBaseInfo(Context context){
        dbHelperSqlite = new createDatabase(context);
    }

    public boolean add(InfoBean bean){
        SQLiteDatabase db = dbHelperSqlite.getReadableDatabase();
        //使用map封装的对象,用来存放值
        ContentValues values = new ContentValues();
        values.put("name",bean.name);
        values.put("stuid",bean.stuid);
        //nullColumnHack空标示添加一个空行，返回值-1代表添加失败
        long result = db.insert("info",null,values);
        db.close();
        if(result!=-1){
            return true;
        }else{
            return false;
        }
    }

    public int del(String _id){
        SQLiteDatabase db = dbHelperSqlite.getReadableDatabase();
        //table表名，whereClause删除条件，whereArgs条件的占位符参数
        int result =  db.delete("info","_id = ?",new String[]{_id});
        db.close();
        return result;
    }

    public int update(InfoBean bean){
        SQLiteDatabase db = dbHelperSqlite.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",bean.name);
        values.put("stuid",bean.stuid);
        int result = db.update("info",values,"_id = ?",new String []{bean._id});
        db.close();
        return result;

    }

    public void query(String _id){
        SQLiteDatabase db = dbHelperSqlite.getReadableDatabase();
        Cursor cursor = db.rawQuery("select tram,humi from info ",new String[]{_id});
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String name = cursor.getString(0);
                String stuid = cursor.getString(1);
                System.out.println("_id:"+_id+";name:"+name+";stuid:"+stuid);
            }
            cursor.close();
        }
        db.close();
    }

}
