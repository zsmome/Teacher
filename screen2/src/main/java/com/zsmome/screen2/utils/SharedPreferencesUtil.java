package com.zsmome.screen2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/5/15.
 */

public class SharedPreferencesUtil {
    private Context mContext = null;
    public SharedPreferencesUtil() {

    }
    public SharedPreferencesUtil(Context context) {
        mContext  = context;
    }
    public void putString(String user, String key, String value) {
        mContext.getSharedPreferences(user,MODE_PRIVATE)
                .edit()
                .putString(key,value)
                .commit();
    }

    /**
     * 读取用户名
     * @return
     */
    public String getString(String user, String key){
        //获得SharedPrerences对象
//        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        //通过key获得值, 没有则 ""
        return mContext.getSharedPreferences(user,MODE_PRIVATE).getString(key, "");
    }
}
