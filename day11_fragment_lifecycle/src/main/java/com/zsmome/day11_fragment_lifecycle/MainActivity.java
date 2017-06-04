package com.zsmome.day11_fragment_lifecycle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zsmome.day11_fragment_lifecycle.fragments.BaseFragment;

public class MainActivity extends AppCompatActivity {
    //管理
    FragmentManager mFManager;
    //事务
    FragmentTransaction mFTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
    }

    /**
     * 加载Fragments
     */
    private void initFragments() {
        BaseFragment bf = new BaseFragment();
        //获得管理器
        mFManager = getFragmentManager();
        //开启事务
        mFTrans = mFManager.beginTransaction();
        //添加Fragment
        mFTrans.add(R.id.fragmentFl, bf);
        //提交事务
        mFTrans.commit();
    }
}
