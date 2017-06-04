package com.zsmome.day10_gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zsmome.day10_gson.adapters.WeeklyBaseAdapter;
import com.zsmome.day10_gson.model.WeeklyList;
import com.zsmome.day10_gson.util.JSONParseUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //声明组件
    private ListView mLv;
    private List<WeeklyList.ListBean> mData;
    private WeeklyBaseAdapter mAdapter;
    private String url = "http://www.1688wan.com/majax.action?method=getWeekll&pageNo=0";
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
        mLv = (ListView) findViewById(R.id.Lv);
        mData = new ArrayList<>();
        mAdapter = new WeeklyBaseAdapter(MainActivity.this, mData);
        mLv.setAdapter(mAdapter);

        //添加数据
        loadData();
    }

    /**
     * 添加数据
     */
    private void loadData() {
        JSONParseUtil jpu = new JSONParseUtil();
        jpu.parseJSON(url, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Gson gson = new Gson();
                WeeklyList weeklyList = gson.fromJson(json, WeeklyList.class);
                //添加数据
                mAdapter.addAll(weeklyList.getList());
            }
        });
    }
}
