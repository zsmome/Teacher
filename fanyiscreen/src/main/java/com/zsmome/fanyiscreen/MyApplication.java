package com.zsmome.fanyiscreen;

import android.app.Application;

import com.zsmome.fanyiscreen.sqlite.MySqliteOpenHelper;
import com.zsmome.fanyiscreen.threads.FanYiThread;


/**
 * Created by Administrator on 2017/5/13.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        new FanYiThread().start();
        //第一次调用getWritableDatabase或getReadableDatabase方法时
        //会创建数据库
        MySqliteOpenHelper helper = new MySqliteOpenHelper(this);
        helper.getWritableDatabase();
        super.onCreate();
    }
}
