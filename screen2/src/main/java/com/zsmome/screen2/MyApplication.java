package com.zsmome.screen2;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.zsmome.screen2.models.UserTable;
import com.zsmome.screen2.models.WordsTable;
import com.zsmome.screen2.sqlite.MySqliteOpenHelper;
import com.zsmome.screen2.sqlite.UserDAOImpl;
import com.zsmome.screen2.sqlite.WordsDAOImpl;
import com.zsmome.screen2.utils.WordsXmlParse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/13.
 */

public class MyApplication extends Application {
    private Intent mIntent = null;
    @Override
    public void onCreate() {
        Log.i("start", "onCreate: ");
        //第一次调用getWritableDatabase或getReadableDatabase方法时
        //会创建数据库
        MySqliteOpenHelper helper = MySqliteOpenHelper.getInstance(this);
        helper.getWritableDatabase();
        boolean isExists = new WordsDAOImpl(this).tableRowCount(MySqliteOpenHelper.TABLES_WORDS);
        //不存在就解析数据
        if(!isExists) {
            mIntent = new Intent(this, WelcomeActivity.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mIntent);
//            parseWordXML();
            new WordsXmlParse().execute();
        }
        super.onCreate();
    }
    /**
     * 解析本地单词-存储到words_tb表中
     */
    class WordsXmlParse extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            InputStream is = null;
            try {
                Resources rs = getResources();
                is = rs.openRawResource(R.raw.words_level_four);
                ArrayList<WordsTable> list = new com.zsmome.screen2.utils.WordsXmlParse().getWordsArrayList(is);
                Log.i("list", list.size()+"----------------------------------------------");
                //插入数据
                for(WordsTable w : list) {
                    new WordsDAOImpl(MyApplication.this).insert(w);
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
            return null;
        }
    }

//    private void parseWordXML() {
//        InputStream is = null;
//        try {
//            Resources rs = getResources();
//            is = rs.openRawResource(R.raw.words_level_four);
//            ArrayList<WordsTable> list = new com.zsmome.screen2.utils.WordsXmlParse().getWordsArrayList(is);
//            Log.i("list", list.size()+"----------------------------------------------");
//            //插入数据
//            for(WordsTable w : list) {
//                new WordsDAOImpl(this).insert(w);
//                Log.i("word", w+"");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(is != null)
//                    is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
