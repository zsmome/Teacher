package com.zsmome.day14_scrollview_sridview_conflict;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Scroll + (GridView | ListView)
 * 将只显示一行数据
 * 解决方法:
 * 1.自定义控件继承GridView或ListView
 * 2.重写onMeasure方法
 */
public class MainActivity extends AppCompatActivity {
    //声明组件
    private GridView mTestGv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initTestGv();
    }

    /**
     * 初始Test GridView控件
     */
    private void initTestGv() {
        //设置Adapter
        mTestGv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 50;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView iv = new ImageView(MainActivity.this);
                iv.setImageResource(R.mipmap.iconhot);
                return iv;
            }
        });
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        mTestGv = (GridView) findViewById(R.id.test_gv);
    }
}
