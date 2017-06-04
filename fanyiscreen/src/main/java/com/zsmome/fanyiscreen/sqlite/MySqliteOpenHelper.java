package com.zsmome.fanyiscreen.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * Created by xray on 17/5/13.
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper{

    //数据库名字
    public static final String DB_NAME = "zsmome.db";
    //数据库版本，一旦版本增加，会对数据库进行自动升级，调用onUpgrade
    public static final int VERSION = 1;
    //创建words表
    public static final String CREATE_WORDS =
            "create table words_tb(_id integer primary key autoincrement,"+
                    "word text,"+
                    "trans text,"+
                    "phonetic text)";
    //删除表
    public static final String DROP_WORDS = "drop table if exists words_tb";

    public MySqliteOpenHelper(Context context){
        this(context,DB_NAME,null,VERSION);
    }

    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 创建表
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WORDS);
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion 旧版本
     * @param newVersion 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_WORDS);
        db.execSQL(CREATE_WORDS);
    }
}
