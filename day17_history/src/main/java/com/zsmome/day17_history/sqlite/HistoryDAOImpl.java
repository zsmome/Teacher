package com.zsmome.day17_history.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zsmome.day17_history.model.History;

import java.util.ArrayList;
import java.util.List;

/**
 * 历史记录，数据库操作
 * Created by Administrator on 2017/5/19.
 */

public class HistoryDAOImpl implements HistoryDAO {
    private MySqliteHelper mySqliteHelper;
    public HistoryDAOImpl(Context context) {
        mySqliteHelper = new MySqliteHelper(context);
    }
    @Override
    public void insert(History history) {
        SQLiteDatabase db = mySqliteHelper.getWritableDatabase();
        db.execSQL("insert into history_tb(content, date, count) values(?, ?, ?)",
                new Object[] {history.getContent(), history.getDate(), history.getCount()});
        db.close();
    }

    @Override
    public List<History> searchContent(String content) {
        SQLiteDatabase db = mySqliteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from history_tb where content like '%" + content+ "%' order by count desc",
                new String[] {});
        List<History> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String con = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            int count = cursor.getInt(cursor.getColumnIndex("count"));
            list.add(new History(con, date, count));
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public boolean exists(String content) {
        SQLiteDatabase db = mySqliteHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from history_tb where content = ?",new String[]{content});
        //是否存在该数据
        boolean exists = c.moveToFirst();
        c.close();
        db.close();
        return exists;
    }

    @Override
    public void update(String content) {
        SQLiteDatabase db = mySqliteHelper.getWritableDatabase();
        db.execSQL("update history_tb set count = count + 1 where content = ?",
                new Object[] {content});
        db.close();
    }

    @Override
    public void delete(String content) {
        SQLiteDatabase db = mySqliteHelper.getWritableDatabase();
        db.execSQL("delete from history_tb where content = ?",
                new Object[] {content});
    }
}
