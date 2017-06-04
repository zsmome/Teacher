package com.zsmome.screen2.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zsmome.screen2.models.WordsTable;


/**
 * History表数据操作实现类
 * Created by xray on 17/5/14.
 */

public class WordsDAOImpl implements WordsDAO {
    public static int count = 0;
    private MySqliteOpenHelper mySqliteOpenHelper = null;

    public WordsDAOImpl(Context context){
        mySqliteOpenHelper = MySqliteOpenHelper.getInstance(context);
    }

    /**
     * 插入单词
     * @param wordsTable
     */
    @Override
    public void insert(WordsTable wordsTable) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        db.execSQL("insert into words_tb(word,trans,phonetic) values(?,?,?)",
                new Object[]{wordsTable.getWord(), wordsTable.getTrans(), wordsTable.getPhonetic()});
        db.close();
    }

    /**
     * 表中是否有数据
     * @param table
     * @return
     */
    @Override
    public boolean tableRowCount(String table){
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        boolean exits = false;
        String sql = "select * from words_tb";
        Cursor cursor = db.rawQuery(sql, null);
        Log.i("count", cursor.getCount()+"");
        if(cursor.getCount()!=0){
            exits = true;
        }
        return exits;
    }

    /**
     * 随机获取一个单词
     */
    @Override
    public WordsTable getWords() {
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        //获得个数
        if(count == 0) {
            String sql = "select * from words_tb";
            Cursor cursorCount = db.rawQuery(sql, null);
            count = cursorCount.getCount();
        }
        int id = (int) (Math.random()*count);
        Cursor cursor = db.rawQuery("select * from words_tb where _id = ?",new String[]{id+""});
        WordsTable word = new WordsTable();
        while(cursor.moveToNext()) {
            word.setWord(cursor.getString(cursor.getColumnIndex("word")));
            word.setTrans(cursor.getString(cursor.getColumnIndex("trans")));
            word.setPhonetic(cursor.getString(cursor.getColumnIndex("phonetic")));
        }
        Log.i("getWord", word+"");
        return word;
    }
}
