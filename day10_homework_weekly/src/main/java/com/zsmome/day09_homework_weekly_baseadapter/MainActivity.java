package com.zsmome.day09_homework_weekly_baseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zsmome.day09_homework_weekly_baseadapter.adapters.WeeklyAdapter;
import com.zsmome.day09_homework_weekly_baseadapter.model.Weekly;
import com.zsmome.day09_homework_weekly_baseadapter.util.JSONParseUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //声明组件
    private ListView mWeeklyLv;
    //适配器
    private WeeklyAdapter mWeeklyAdapter;
    //数据
    private List<Weekly.ListBean> mAdBeanData;
    //Json网址
    private String JSON_URL = "http://www.1688wan.com/majax.action?method=getGiftList&pageno=0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 初始界面
     */
    private void initViews() {
        //找到ListView
        mWeeklyLv = (ListView) findViewById(R.id.weeklyLv);
        mAdBeanData = new ArrayList<>();
        //创建Adapter
        mWeeklyAdapter = new WeeklyAdapter(this, mAdBeanData);
        //设置Adapter
        mWeeklyLv.setAdapter(mWeeklyAdapter);
        loadDatas();
    }
    /**
     * 初始化数据
     */
    private void loadDatas() {
        //解析json
        JSONParseUtil jpu = new JSONParseUtil();
        jpu.parseJSON(JSON_URL, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Gson gson = new Gson();
                Weekly weekly = gson.fromJson(json, Weekly.class);
                mWeeklyAdapter.addAll(weekly.getList());
            }
        });
    }


}
