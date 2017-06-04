package com.zsmome.day17_student_table.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * Created by xray on 17/5/13.
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper{
    //上下文
    private Context mContext;
    //本类的引用
    private static MySqliteOpenHelper helper = null;
    //数据库名字
    public static final String DB_NAME = "zsmome.db";
    //数据库版本，一旦版本增加，会对数据库进行自动升级，调用onUpgrade
    public static final int VERSION = 1;
    //表名,注意sql语句中不能使用
    //创建用户表
    public static final String CREATE_STUDENT =
            "create table student_tb(_id integer primary key autoincrement," +
                    "name text," +
                    "age text," +
                    "sex text," +
                    "address text)";
    //删除用户表
    public static final String DROP_STUDENT = "drop table if exists student_tb";
    //单例
    public static MySqliteOpenHelper getInstance(Context context) {
        if(helper == null) {
            synchronized (MySqliteOpenHelper.class) {
                if(helper == null) {
                    MySqliteOpenHelper temp = new MySqliteOpenHelper(context);
                    helper = temp;
                }
            }
        }
        return helper;
    }
    private MySqliteOpenHelper(Context context){
        this(context,DB_NAME,null,VERSION);
    }

    private MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion 旧版本
     * @param newVersion 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTable(db);
        createTable(db);
    }
    //创建表
    private void createTable(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT);
    }
    //删除表
    private void dropTable(SQLiteDatabase db) {
        db.execSQL(DROP_STUDENT);
    }
}
