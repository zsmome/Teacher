package com.zsmome.day19_contextmenu.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zsmome.day19_contextmenu.model.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库，操作学生信息
 * Created by Administrator on 2017/5/19.
 */

public class FruitDAOImpl implements FruitDAO {
    private MySqliteOpenHelper mySqliteOpenHelper;
    public FruitDAOImpl(Context context) {
        mySqliteOpenHelper = MySqliteOpenHelper.getInstance(context);
    }

    @Override
    public void insert(Fruit fruit) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        db.execSQL("insert into fruit_tb(icon, content, date) values (?, ?, ?)",
                new Object[]{fruit.getIcon(), fruit.getContent(), fruit.getDate()});
        db.close();
    }

    @Override
    public void delete(String content) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        db.execSQL("delete from fruit_tb where content = ?",
                new Object[] {content});
    }

    @Override
    public List<Fruit> getFruitList() {
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from fruit_tb", null);
        /*集合*/
        List<Fruit> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            int icon = cursor.getInt(cursor.getColumnIndex("icon"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            list.add(new Fruit(_id, icon, content, date));
        }
        return list;
    }
    /**
     * 表中是否有数据
     * @return
     */
    @Override
    public boolean isExists(){
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        boolean exits = false;
        String sql = "select * from fruit_tb";
        Cursor cursor = db.rawQuery(sql, null);
        Log.i("count", cursor.getCount()+"");
        if(cursor.getCount()!=0){
            exits = true;
        }
        db.close();
        return exits;
    }

    @Override
    public Fruit getFruit(int id) {
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from fruit_tb where _id = ?", new String[] {id+""});
        cursor.moveToNext();
        int _id = cursor.getInt(cursor.getColumnIndex("_id"));
        int icon = cursor.getInt(cursor.getColumnIndex("icon"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        String date = cursor.getString(cursor.getColumnIndex("date"));
        return new Fruit(_id, icon, content, date);
    }
}
