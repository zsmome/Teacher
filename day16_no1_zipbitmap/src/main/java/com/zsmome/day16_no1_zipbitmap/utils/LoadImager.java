package com.zsmome.day16_no1_zipbitmap.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 获取网络图片
 * Created by Administrator on 2017/5/7.
 */

public class LoadImager {
    private double desWidth;
    private double desHeight;
    public LoadImager() {}

    public LoadImager(double desWidth, double desHeight) {
        this.desWidth = desWidth;
        this.desHeight = desHeight;
    }

    //回调接口，目的，调用者要用到图像
    public interface OnLoadImage {
        void loadImageComplete(String url, Bitmap bitmap);
    }
    private OnLoadImage onLoadImage;

    /**
     * 加载图片
     */
    public void loadImage(String url, OnLoadImage onLoadImage) {
        this.onLoadImage = onLoadImage;
        new LoadImageTask().execute(url);
    }

    /**
     * 异步加载图片任务
     */
    private class LoadImageTask extends AsyncTask<String, Void, LoadImageTask.UrlAndBitmap> {
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
        protected UrlAndBitmap doInBackground(String... params) {
            HttpURLConnection conn = null;
            InputStream is = null;
            Bitmap bitmap = null;
            try {
                bitmap = getBitmapLruCache(params[0]);
                //判断图片是否在缓存中
                if(bitmap == null) {
                    Log.i("BitmapLruCache", "缓存图片");
                    URL url = new URL(params[0]);
                    conn = (HttpURLConnection) url.openConnection();
                    is = conn.getInputStream();
                    //原始图像
                    if (desWidth == 0 || desHeight == 0) {
                        bitmap = BitmapFactory.decodeStream(is);
                    } else {
                        //压缩图像
                        bitmap = zipBitmap(streamToByteArray(is), desWidth, desHeight);
                    }
                    //将图片存到内存
                    setBitmapLruCache(params[0], bitmap);
                }
                //该图片缓存了，就直接获得图片
                return new UrlAndBitmap(params[0], bitmap);
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
         * @param result
         */
        @Override
        protected void onPostExecute(UrlAndBitmap result) {
            super.onPostExecute(result);
            if(result != null) {
                onLoadImage.loadImageComplete(result.url, result.bitmap);
            }
        }

        /**
         * 包装图片地址和图片
         */
        class UrlAndBitmap{
            String url;
            Bitmap bitmap;

            public UrlAndBitmap(String url, Bitmap bitmap) {
                this.url = url;
                this.bitmap = bitmap;
            }
        }
    }

    /**
     * 将输入流转换为字节数组
     * @param inputStream
     * @return
     */
    public static byte[] streamToByteArray(InputStream inputStream) throws IOException {
        //字节数组输出流，将数据写到内存中的字节数组中
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = inputStream.read(buffer)) != -1){
            //向字节数组中写入
            outputStream.write(buffer,0,len);
        }
        //清空输出流
        outputStream.flush();
        inputStream.close();
        //返回字节数组
        return outputStream.toByteArray();
    }

    /**
     * 压缩图片
     * @param data 图片的字节数组
     * @param destWidth	目标的宽度
     * @param destHeight 目标的高度
     * @return 压缩后的图片
     */
    public static Bitmap zipBitmap(byte[] data,double destWidth,double destHeight){
        //创建图片参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置只加载边框
        options.inJustDecodeBounds = true;
        //第一次采样，加载图片边框
        BitmapFactory.decodeByteArray(data,0,data.length,options);
        //获得图片原始的宽和高
        int width = options.outWidth;
        int height = options.outHeight;
        //计算压缩比例
        int sampleSize = 1;
        while(width / sampleSize > destWidth || height / sampleSize > destHeight){
            sampleSize *= 2;
        }
        //重新设置参数，加载边框
        options.inJustDecodeBounds = false;
        //压缩比例
        options.inSampleSize = sampleSize;
        //图片格式
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        //第二次采样，获得压缩后的图片
        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length,options);
        return bitmap;
    }

    public static LruCache<String, Bitmap> bitmapLruCache;
    //缓存大小
    public static final int CACHE_SIZE = 10 * 1024 * 1024;
    static {
        bitmapLruCache = new LruCache<>(CACHE_SIZE);
    }
    /**
     * 将地址转成md5字符串
     */
    public static String md5(String str) {
        if(str.isEmpty()) {
            return "";
        }
        //消息加密
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuilder result = new StringBuilder();
            for(byte b : bytes) {
                result.append(Integer.toHexString(b & 0xff));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 对图片进行一级缓存
     */
    public static void setBitmapLruCache(String str, Bitmap bitmap) {
        String md5 = md5(str);
        bitmapLruCache.put(md5, bitmap);
    }
    /**
     * 从一级缓存中取出图片
     */
    public static Bitmap getBitmapLruCache(String str) {
        String md5 = md5(str);
        return bitmapLruCache.get(md5);
    }
}
