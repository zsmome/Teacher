package com.zsmome.day09_homework_beating3_baseadapter.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 异步加载图片
 * 注意：加上网络权限
 * Created by Administrator on 2017/4/8.
 */

public class LoadImageUtil {
    /**
     * 定义回调接口
     */
    public interface OnLoadImageListener {
        void OnLoadImageComplete(Bitmap bitmap);
    }
    //定义接口
    private OnLoadImageListener mListener;
    /**
     * 加载图片
     */
    public void loadImage(String url, OnLoadImageListener listener) {
        mListener = listener;
        //执行加载图片
        new LoadImageTask().execute(url);
    }
    /**
     * 异步加载图片类
     */
    class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
        /**
         * 加载网络图片
         * @param params 网络图片地址
         * @return 图片
         * 步骤：
         * 1.创建URL
         * 2.打开连接
         * 3.获取输入流
         * 4.解析图片
         */
        @Override
        protected Bitmap doInBackground(String... params) {
            HttpURLConnection conn = null;
            InputStream is = null;
            try {
                URL url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();
                is = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /**
         * 回载图片完成后
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap != null) {
                mListener.OnLoadImageComplete(bitmap);
            }
        }
    }
}
