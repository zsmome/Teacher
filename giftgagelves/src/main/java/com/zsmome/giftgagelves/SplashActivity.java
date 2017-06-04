package com.zsmome.giftgagelves;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 功能：
 *      完成闪屏功能
 * 知识点：
 *      Handle类
 * 注意：os下的
 *
 * 问题：
 *      怎么杀死Handler任务
 */
public class SplashActivity extends AppCompatActivity {
    //声明组件
    private Button mAdBt;
    private Handler mHandler = new Handler();
    //广告时间
    public static int  AD_TIME = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
    }

    /**
     * 声明组件
     */
    private void initViews() {
        mAdBt = (Button) findViewById(R.id.ad_bt);
        //执行广告任务
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //到时间，跳出广告
                if(AD_TIME == 0) {
                    gotoMainActivity();
                    mHandler.removeCallbacks(this);
                }
                mAdBt.setText(AD_TIME + " 跳过广告 >");
                AD_TIME--;
                mHandler.postDelayed(this, 1000);
            }
        });
    }

    /**
     * 跳转到主页面
     */
    private void gotoMainActivity() {
        finish();
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }

    /**
     * 跳过广告
     * @param view
     */
    public void skipAD(View view) {
        AD_TIME = 0;
    }
}
