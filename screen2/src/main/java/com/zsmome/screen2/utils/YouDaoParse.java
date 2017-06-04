package com.zsmome.screen2.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 解析JSON
 * Created by Administrator on 2017/3/26.
 */

public class YouDaoParse {
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
    public void parseJSON(String str, OnJSONParseListener listener) {
        this.mListener = listener;
        //开始解析
        new ParseJSONTask().execute(str);
    }

    /**
     * 解析JSON
     */
    class ParseJSONTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://fanyi.youdao.com/openapi.do");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.addRequestProperty("encoding", "UTF-8");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                OutputStream os = connection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                bw.write("keyfrom=zsmome&key=692648589&type=data&doctype=json&version=1.1&q="+params[0]);
                bw.flush();

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);

                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }

                bw.close();
                osw.close();
                os.close();

                br.close();
                isr.close();
                is.close();
                //返回翻译的字符串
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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
                Log.i("youdao", s);
            } else {
                mListener.OnJSONParseComplete("");
            }
        }
    }
}
