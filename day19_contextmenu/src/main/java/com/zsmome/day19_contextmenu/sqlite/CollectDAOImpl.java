package com.zsmome.day19_contextmenu.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zsmome.day19_contextmenu.model.Collect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */

public class CollectDAOImpl implements CollectDAO {
    private MySqliteOpenHelper helper;

    public CollectDAOImpl(Context context) {
        helper = MySqliteOpenHelper.getInstance(context);
    }

    @Override
    public void insert(Collect collect) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into collect_tb(fruit_tb_id) values(?)", new Object[]{collect.get_id()});
    }

    @Override
    public void delete(Collect collect) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from collect_tb where fruit_tb_id = ?", new Object[]{collect.get_id()});
    }

    @Override
    public List<Collect> getCollectList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from collect_tb", null);
        /*集合*/
        List<Collect> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("fruit_tb_id"));
            list.add(new Collect(id));
        }
        return list;
    }
}
