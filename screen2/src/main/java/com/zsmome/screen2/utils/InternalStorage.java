package com.zsmome.screen2.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 在内储卡中存储文件
 * Created by Administrator on 2017/5/15.
 */

public class InternalStorage {
    private Context mContext;
    public InternalStorage() {}
    public InternalStorage(Context context) {
        mContext = context;
    }
    /**
     * 根据文件名存储数据
     */
    public void saveData(String fileName, String key, String value) {
        OutputStream outputStream = null;
        try {
            outputStream = mContext.openFileOutput(fileName+".txt", Context.MODE_PRIVATE);

            //存储
            String str = key+":"+value;
            outputStream.write(str.getBytes("utf-8"));
            //刷新流
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 根据文件名获取数据
     */
    public String getData(String fileName, String key) {
        InputStream is = null;
        try {
            is = mContext.openFileInput(fileName+".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            //不断读取数据
            while((line = br.readLine()) != null) {
                if(line.contains(key)) {
                    String[] arr = line.split(":");
                    return arr[1];
                }
                Log.i(key, line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
