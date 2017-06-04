package com.zsmome.day12_no1_viewpager_basicuse;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zsmome.day12_no1_viewpager_basicuse.adapters.SwitchoverAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //声明组件
    private ViewPager mSwitchoverVp;
    //图片资源
    private final int[] mImageId = {R.mipmap.a1, R.mipmap.a2, R.mipmap.a3};
    //准备数据
    private SwitchoverAdapter mSwitchoverAdapter;
    private List<View> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        mData = new ArrayList<>();
        for(int i=0; i<mImageId.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(mImageId[i]);
            mData.add(iv);
        }
        mSwitchoverAdapter = new SwitchoverAdapter(this, mData);
        mSwitchoverVp.setAdapter(mSwitchoverAdapter);
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        mSwitchoverVp = (ViewPager) findViewById(R.id.switchover_vp);
    }
}
