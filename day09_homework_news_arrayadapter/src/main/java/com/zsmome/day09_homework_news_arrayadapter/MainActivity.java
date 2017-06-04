package com.zsmome.day09_homework_news_arrayadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.准备数据
 * 2.创建ArrayAdapter
 * 3.设置Adapter
 */
public class MainActivity extends AppCompatActivity {
    //声明组件
    private ListView mNewsLv;
    //声明ArrayAdapter
    private ArrayAdapter<String> mNewsAdapter;
    //列表长度
    private static final int LIST_VIEW_LEN = 10;
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
        mNewsLv = (ListView) findViewById(R.id.newsLv);
        //准备数据
        List<String> newsList = new ArrayList<>();
        for(int i=0; i<LIST_VIEW_LEN; i++) {
            newsList.add("对，好新闻就是我,我就是好新闻-->"+i);
        }
        //创建数据
        mNewsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                android.R.id.text1,
                newsList);
        //设置数据
        mNewsLv.setAdapter(mNewsAdapter);
    }
}
