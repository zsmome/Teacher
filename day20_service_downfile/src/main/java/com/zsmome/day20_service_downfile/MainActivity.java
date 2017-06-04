package com.zsmome.day20_service_downfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 下载文件流程
 * 1、点击下载
 * 2、启动服务
 * 3、通过广播将进度发送给activity
 * 4、activity接收广播
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 下载文件
     * @param view
     */
    public void onDownFile(View view) {
    }
}
