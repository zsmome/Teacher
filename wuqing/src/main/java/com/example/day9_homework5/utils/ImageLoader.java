package com.example.day9_homework5.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 图片工具加载器
 * Created by Administrator on 2017/4/13.
 */

public class ImageLoader {

    //创建接口，图片加载完成的回调接口
    //添加URL参数，用于做图片错位判断
    public interface OnImageLoaderListener{
        void onImageLoader(String url,Bitmap bmp);
    }

    private OnImageLoaderListener mListener;

    //创建一个图片加载方法，启动图片加载任务
    public void imageLoad(String url,OnImageLoaderListener mListener){
        this.mListener = mListener;
        new ImageLoaderTask().execute(url);
    }
    /**
     * 图片加载任务类，异步任务
     */
    class ImageLoaderTask extends AsyncTask<String,Void,ImageLoaderTask.UrlAndBitmap>{

        @Override
        protected UrlAndBitmap doInBackground(String... strings) {
            HttpURLConnection conn = null;
            InputStream inputStream = null;

            try {
                //1、创建Url ,指定图片位置
                URL url = new URL(strings[0]);
                //2、打开连接获得HttpURLOconnection对象
                conn = (HttpURLConnection) url.openConnection();
                //3、获得文件输入流
                inputStream = conn.getInputStream();
                //4、把输入流转换为图片
                Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                return new UrlAndBitmap(strings[0],bmp);
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

        //包装图片地址和图片
        class UrlAndBitmap{
            String url;
            Bitmap bitmap;

            public UrlAndBitmap(String url,Bitmap bitmap){
                this.url = url;
                this.bitmap = bitmap;
            }
        }
        @Override
        protected void onPostExecute(UrlAndBitmap bitmap) {
//            super.onPostExecute(bitmap);
            //进行接口回调
            if(mListener != null){
                mListener.onImageLoader(bitmap.url,bitmap.bitmap);
            }
        }
    }
}
