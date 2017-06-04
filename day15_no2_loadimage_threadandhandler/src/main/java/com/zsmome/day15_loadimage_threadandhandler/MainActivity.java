package com.zsmome.day15_loadimage_threadandhandler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
    //声明组件
    private ImageView mImageIv;
    //消息
    public static final int MESSAGE_BITMAP = 1;
    //网址
    public static final String URL = "https://www.baidu.com/img/bd_logo1.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 找到控件
     */
    private void initViews() {
        mImageIv = (ImageView) findViewById(R.id.image_iv);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MESSAGE_BITMAP:
                mImageIv.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    /**
     * 点击加载图片
     * @param view
     */
    public void loadImage(View view) {
        new LoadImage(URL).start();
    }

    /**
     * 加载图片类
     */
    private class LoadImage extends Thread {
        private String urlStr;
        public  LoadImage(String urlStr) {
            this.urlStr = urlStr;
        }
        @Override
        public void run() {
            URL url = null;
            HttpURLConnection conn = null;
            //加载图片
            try {
                url = new URL(urlStr);
                conn = (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                //发送图片到主线程
                mHandler.obtainMessage(MESSAGE_BITMAP, bitmap).sendToTarget();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
