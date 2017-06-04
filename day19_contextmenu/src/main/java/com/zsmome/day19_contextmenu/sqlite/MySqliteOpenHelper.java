package com.zsmome.day19_contextmenu.sqlite;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zsmome.day19_contextmenu.R;
import com.zsmome.day19_contextmenu.model.Fruit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    //水果表
    public static final String CREATE_FRUIT =
            "create table fruit_tb(_id integer primary key autoincrement," +
                    "icon integer," +
                    "content text," +
                    "date text)";
    public static final String DROP_FRUIT = "drop table if exists fruit_tb";
    //收藏表
    public static final String CREATE_COLLECT =
            "create table collect_tb(_id integer primary key autoincrement," +
                    "fruit_tb_id integer)";
    public static final String DROP_COLLECT = "drop table if exists collect_tb";
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
        db.execSQL(CREATE_FRUIT);
        db.execSQL(CREATE_COLLECT);
    }
    //删除表
    private void dropTable(SQLiteDatabase db) {
        db.execSQL(DROP_FRUIT);
        db.execSQL(DROP_COLLECT);
    }
}
