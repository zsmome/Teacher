package com.zsmome.screen2.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zsmome.screen2.models.UserTable;

/**
 * Created by Administrator on 2017/5/15.
 */

public class UserDAOImpl implements UserDAO {
    private MySqliteOpenHelper mySqliteOpenHelper = null;
    public UserDAOImpl(Context context) {
        mySqliteOpenHelper = MySqliteOpenHelper.getInstance(context);
    }

    @Override
    public void insert(UserTable userTable) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        db.execSQL("insert into user_tb(username,password,date,hintRemainCount) values(?,?,?,?)",
                new Object[]{userTable.getUsername(), userTable.getPassword(), userTable.getDate(), userTable.getHintRemainCount()});
        db.close();
    }

    @Override
    public UserTable getUser(String username) {
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_tb where username = ?",new String[]{username});
        while (cursor.moveToNext()) {
            String password = cursor.getString(cursor.getColumnIndex("password"));
            Long date = cursor.getLong(cursor.getColumnIndex("date"));
            int hintRemainCount = cursor.getInt(cursor.getColumnIndex("hintRemainCount"));
            int loseWordsCount = cursor.getInt(cursor.getColumnIndex("loseWordsCount"));
            return new UserTable(username, password, date, hintRemainCount, loseWordsCount);
        }
        return null;
    }

    @Override
    public void updateHintRemainCount(String username, int count) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        db.execSQL("update user_tb set hintRemainCount = ? where username = ?",
                new Object[]{count, username});
        db.close();
    }

    @Override
    public void updateLoseWordsCount(String username) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        db.execSQL("update user_tb set loseWordsCount = loseWordsCount + 1 where username = ?",
                new Object[]{username});
        db.close();
    }
}
