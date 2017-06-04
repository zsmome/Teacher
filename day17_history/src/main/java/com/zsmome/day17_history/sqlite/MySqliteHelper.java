package com.zsmome.day17_history.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    private MySqliteHelper mySqliteHelper;
    /*数据库名字*/
    public static final String DB_NAME = "zsmome.db";
    /*数据库版本*/
    public static final int VERSION = 1;
    /*创建表*/
    public static final String CREATE_HISTORY =
            "create table history_tb(_id integer primary key autoincrement," +
                    "content text," +
                    "date text," +
                    "count integer)";
    /*删除表*/
    public static final String DROP_HISTORY = "drop table is exists history_tb";

    public MySqliteHelper(Context context) {
        this(context,DB_NAME,null,VERSION);
    }
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_HISTORY);
        db.execSQL(CREATE_HISTORY);
    }
}
