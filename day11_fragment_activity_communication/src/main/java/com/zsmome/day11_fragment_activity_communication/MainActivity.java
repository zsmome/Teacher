package com.zsmome.day11_fragment_activity_communication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zsmome.day11_fragment_activity_communication.fragments.MyFragment;

/**
 * PS：
 * Activity中调用Fragment方法
 * 1.通过Fragment引用
 * 2.无引用
 * Fragment中调用Activity方法
 * 1.getActivity()获得Fragment所在Activity对象
 */
public class MainActivity extends AppCompatActivity {
    //声明组件
    private Button mBtn;
    private static int count = 0;
    //Fragment相关
    private MyFragment myFragment;
    private FragmentManager mFmanager;
    private FragmentTransaction mFtrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragments();
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        mBtn = (Button) findViewById(R.id.Activity_bt);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用Fragment方法
                myFragment.plus();
            }
        });
    }

    /**
     * 初始Fragment
     */
    private void initFragments() {
        myFragment = new MyFragment();
        mFmanager = getSupportFragmentManager();
        mFtrans = mFmanager.beginTransaction();
        mFtrans.add(R.id.fragment_fl, myFragment);
        mFtrans.commit();
    }
    /**
     *
     */
    public void plus() {
        count ++;
        mBtn.setText("Activity -- "+count);
    }
}
