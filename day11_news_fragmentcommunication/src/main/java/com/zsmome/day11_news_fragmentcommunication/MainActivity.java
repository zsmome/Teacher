package com.zsmome.day11_news_fragmentcommunication;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.zsmome.day11_news_fragmentcommunication.fragments.LeftFragment;
import com.zsmome.day11_news_fragmentcommunication.fragments.RightFragment;

/**
 * 1、定义回调接口
 * 2、让被调用的Fragment实现回调接口
 * 3、在调用方的Fragment中，定义回调接口对象，和设置回调接口的方法
 * 4、在调用方的事件监听中，调用回调接口的方法
 * 5、在Activity中，给调用方的Fragment设置回调接口（被调用方的Fragment）
 */
public class MainActivity extends AppCompatActivity {
    //声明
    private FrameLayout mLeftFl;
    private FrameLayout mRightFl;
    //Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragments();
    }

    /**
     * 加载Fragments
     */
    private void initFragments() {
        LeftFragment left = new LeftFragment();
        RightFragment right = new RightFragment();
        //设置回调
        left.setOnNewsCallBack(right);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.left_fl, left)
                .add(R.id.right_fl, right)
                .commit();
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        mLeftFl = (FrameLayout) findViewById(R.id.left_fl);
        mRightFl = (FrameLayout) findViewById(R.id.right_fl);
    }
}
