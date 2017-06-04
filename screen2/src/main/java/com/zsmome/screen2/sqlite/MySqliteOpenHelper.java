package com.zsmome.screen2.sqlite;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zsmome.screen2.R;
import com.zsmome.screen2.models.WordsTable;
import com.zsmome.screen2.utils.WordsXmlParse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
    public static final String TABLES_WORDS = "words_tb";
    //创建单词表
    public static final String CREATE_WORDS =
            "create table words_tb(_id integer primary key autoincrement," +
                    "word text," +
                    "trans text," +
                    "phonetic text)";
    //删除单词表
    public static final String DROP_WORDS = "drop table if exists words_tb";
    //创建用户表
    public static final String CREATE_USER =
            "create table user_tb(_id integer primary key autoincrement," +
                    "username text," +
                    "password text," +
                    "date bigint," +
                    "hintRemainCount integer," +
                    "loseWordsCount integer)";
    //删除用户表
    public static final String DROP_USER = "drop table if exists user_tb";
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
        db.execSQL(CREATE_WORDS);
        db.execSQL(CREATE_USER);
//        parseWordXML();
    }
    //删除表
    private void dropTable(SQLiteDatabase db) {
        db.execSQL(DROP_WORDS);
        db.execSQL(DROP_USER);
    }
    /**
     * 解析本地单词-存储到words_tb表中
     */
    private void parseWordXML() {
        InputStream is = null;
        try {
            Resources rs = mContext.getResources();
            is = rs.openRawResource(R.raw.words_level_four);
            ArrayList<WordsTable> list = new WordsXmlParse().getWordsArrayList(is);
            Log.i("list", list.size()+"----------------------------------------------");
            //插入数据
            for(WordsTable w : list) {
                new WordsDAOImpl(mContext).insert(w);
                Log.i("word", w+"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
