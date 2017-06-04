package com.example.day9_homework5.utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/4/13.
 */

public class JSONLoader {

    //创建接口
    public interface OnJSONLoaderListener{
        void onJSONLoader(String json);
    }

    private OnJSONLoaderListener mListener;

    public void JSONLoad(String url, OnJSONLoaderListener mListener){
        this.mListener = mListener;
        new JSONLoaderTask().execute(url);
    }
    /**
     * JSON文件加载任务
     */
    class JSONLoaderTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection conn = null;
            InputStream inputStream = null;
            try {
                //1、创建URL，指定JSON位置
                URL url = new URL(strings[0]);
                //2、打开连接获得HttpURLConnection对象
                conn = (HttpURLConnection) url.openConnection();
                //3、获得文件输入流
                inputStream = conn.getInputStream();
                //4、读取JSON文件中的内容
                byte[] buffer = new byte[1024];
                int len = 0;

                StringBuilder builder = new StringBuilder();
                while((len = inputStream.read(buffer)) != -1){
                    //将字节码转换为String
                    String str = new String(buffer,0,len);
                    builder.append(str);
                }
                return builder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(conn != null){
                    conn.disconnect();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //回调接口
            if(mListener != null){
                mListener.onJSONLoader(s);
            }
        }
    }
}
