package com.zsmome.day09_homework_weekly_baseadapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zsmome.day09_homework_weekly_baseadapter.adapters.WeeklyAdapter;
import com.zsmome.day09_homework_weekly_baseadapter.model.Info;
import com.zsmome.day09_homework_weekly_baseadapter.model.Weekly;
import com.zsmome.day09_homework_weekly_baseadapter.util.JSONParseUtil;
import com.zsmome.day09_homework_weekly_baseadapter.util.LoadImageUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //声明组件
    private ListView mWeeklyLv;
    //适配器
    private WeeklyAdapter mWeeklyAdapter;
    //数据
    private static Weekly weekly;
    private List<Weekly.ListBean> mAdBeanData;
    //Json网址
    private String JSON_URL = "http://www.1688wan.com/majax.action?method=getGiftList&pageno=";
    //页数
    private static int pageno = 0;
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
        //ListView 尾
        TextView mMoveTv = new TextView(this);
        mMoveTv.setText("加载更多...");
        mWeeklyLv.addFooterView(mMoveTv);
        mWeeklyLv.setAdapter(mWeeklyAdapter);
        mWeeklyLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == mWeeklyLv.getCount()-1) {
                    if(pageno < weekly.getPageno()-1) {
                        pageno++;
                        loadDatas();
                    }
                } else {
                    Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_LONG).show();
                    String ID = mAdBeanData.get(position).getId();
                    JSONParseUtil jpu = new JSONParseUtil();
                    String ID_URL = "http://www.1688wan.com/majax.action?method=getGiftInfo&id=";
                    jpu.parseJSON(ID_URL + ID, new JSONParseUtil.OnJSONParseListener() {
                        @Override
                        public void OnJSONParseComplete(String json) {
                            Gson gson = new Gson();
                            Info info = gson.fromJson(json, Info.class);
                            //跳转页面
                            gotoInfo(info);
                        }
                    });
                }
            }

        });
        loadDatas();
    }
    /**
     * 初始化数据
     */
    private void loadDatas() {
        //解析json
        JSONParseUtil jpu = new JSONParseUtil();
        jpu.parseJSON(JSON_URL+pageno, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Gson gson = new Gson();
                weekly = gson.fromJson(json, Weekly.class);
                mWeeklyAdapter.addAll(weekly.getList());
            }
        });
    }
    /**
     * 设置详情页
     */
    public void gotoInfo(Info info) {
        Intent intent = new Intent();
        intent.setClass(this, InfoAtivity.class);
        Bundle bundle = new Bundle();
        Info.InfoBean infoBean = info.getInfo();
        String url = "http://www.1688wan.com"+infoBean.getIconurl();
        bundle.putString("imgurl",url);
        bundle.putString("date",infoBean.getOvertime());
        intent.putExtra("remain", infoBean.getNumber()+"");
        intent.putExtra("desc", infoBean.getDescs());
        intent.putExtra("type", infoBean.getTypename());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
