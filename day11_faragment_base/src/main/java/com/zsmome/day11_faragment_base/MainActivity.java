package com.zsmome.day11_faragment_base;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }
    public void initFragment() {
        BaseFragment bf = new BaseFragment();
        //获得管理对象
        FragmentManager fmanger = getFragmentManager();
        FragmentTransaction ft = fmanger.beginTransaction();
        ft.add(R.id.fragmentFl, bf);
        ft.commit();
    }
}
