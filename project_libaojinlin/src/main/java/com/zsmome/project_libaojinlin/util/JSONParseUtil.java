package com.zsmome.project_libaojinlin.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
    /**
     * 通过Post进行关键字搜索
     * @param url
     * @param search
     * @param listener
     */
    public void loadJSONWithPost(String url,String search,OnJSONParseListener listener){
        this.mListener = listener;
        new JSONLoadPostTask().execute(url,search);
    }

    /**
     * 使用Post请求的网络连接任务
     */
    class JSONLoadPostTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                //定义URL
                URL url = new URL(params[0]);
                //打开连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //设置请求的方法类型为POST
                conn.setRequestMethod("POST");
                //设置向服务器进行输出
                conn.setDoOutput(true);
                //获得输出流
                OutputStream outputStream = conn.getOutputStream();
                //通过输出流发送参数
                for(int i = 1; i < params.length;i++){
                    outputStream.write(params[i].getBytes());
                }
                //获得输入流
                InputStream inputStream = conn.getInputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                //StringBuffer 线程安全，性能低 VS StringBuilder 线程不安全，性能高
                StringBuilder strB = new StringBuilder();
                while((len = inputStream.read(buffer)) != -1){
                    //将字节码转换为String
                    String str = new String(buffer,0,len);
                    strB.append(str);
                }
                inputStream.close();
                return strB.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if(mListener != null){
                mListener.OnJSONParseComplete(result);
            }
        }

    }

}
