package com.zsmome.day11_news_by_activity_fragmentcommunication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.zsmome.day11_news_by_activity_fragmentcommunication.fragments.LeftFragment;
import com.zsmome.day11_news_by_activity_fragmentcommunication.fragments.RightFragment;

/**
 * 中转：
 * MainActivity：中转方法
 * LeftFragment:返回字符串方法
 * RightFragment:设置字符串方法
 */
public class MainActivity extends AppCompatActivity {
    //声明
    private FrameLayout mLeftFl;
    private FrameLayout mRightFl;
    //Fragment
    private LeftFragment left;
    private RightFragment right;
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
        left = new LeftFragment();
        right = new RightFragment();
        //设置回调
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
    public void left2Right() {
        //中转
        right.showTitle(left.getTitle());
    }
}
