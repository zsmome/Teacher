package com.zsmome.day09_homework_beating3_baseadapter.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 解析JSON
 * Created by Administrator on 2017/3/26.
 */

public class JSONParseUtil {
    /**
     * 定一接口
     */
    public interface OnJSONParseListener {
        void OnJSONParseComplete(String json);
    }
    //定义一个接口成员变量
    private OnJSONParseListener mListener;
    /**
     * 解析网络上的JSON
     */
    public void parseJSON(String url, OnJSONParseListener listener) {
        this.mListener = listener;
        //开始解析
        new ParseJSONTask().execute(url);
    }

    /**
     * 解析JSON
     */
    class ParseJSONTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //输入流
            InputStream is = null;
            try {
                //建立连接
                URL url = new URL(params[0]);
                //打开连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //获得输入流
                is = conn.getInputStream();
                //存放json字符串
                StringBuilder sb = new StringBuilder();
                int len = 0;
                byte[] buffer = new byte[1024];
                while((len = is.read(buffer)) != -1) {
                    String s = new String(buffer, 0, len);
                    sb.append(s);
                }
                return sb.toString();
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
        /**
         * 执行任务之后
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null) {
                mListener.OnJSONParseComplete(s);
                Log.i("JsonStr", s);
            }
        }
    }
}
