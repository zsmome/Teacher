package com.zsmome.day09_listview_arrayadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //声明组件
    private ListView mNameLv;
    //列表的长度
    private static final int LIST_LEN = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        mNameLv = (ListView) findViewById(R.id.nameLv);
        //准备数据
        List<String> name = new ArrayList<>();
        for(int i=0; i<LIST_LEN; i++) {
            name.add("我是--->"+i);
        }
        //创建ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                name);
        //设置Adapter
        mNameLv.setAdapter(adapter);
    }
}
