package com.example.day9_homework5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.day9_homework5.adapter.LiBaoAdapter;
import com.example.day9_homework5.model.Libao;
import com.example.day9_homework5.utils.JSONLoader;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLibaoLv;
    private List<Libao.ListBean> mList;
    private LiBaoAdapter mLibaoAdapter;
    private int mPageNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mLibaoLv = (ListView) findViewById(R.id.libao_lv);
        mList = new ArrayList<>();
        mLibaoAdapter = new LiBaoAdapter(this,mList);
        mLibaoLv.setAdapter(mLibaoAdapter);

        /**
         * 第三种方法：（对数据进行分页，避免一次性加载大量数据导致内存消耗过多和运行卡顿）
         */
        //添加尾部视图
        Button loadMoreBtn = new Button(this);
        //设置按钮的大小
        loadMoreBtn.setLayoutParams(
                new AbsListView.LayoutParams(
                        AbsListView.LayoutParams.MATCH_PARENT,
                        AbsListView.LayoutParams.WRAP_CONTENT));
        loadMoreBtn.setText("加载更多");
        mLibaoLv.addFooterView(loadMoreBtn);
        //点击按钮，加载更多数据
        loadMoreBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        loadData();
        //添加点击项目监听
        mLibaoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String te = mList.get(i-1).toString();
                Toast.makeText(MainActivity.this,"test--"+te,Toast.LENGTH_SHORT).show();
//                //页面跳转
//                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
//                //启动界面
//                startActivity(intent);
//                changeJIemian();
            }
        });
    }

    private void changeJIemian() {
    }

    /**
     * 加载礼包数据
     */
    private void loadData() {
        mPageNo++;
        JSONLoader json = new JSONLoader();
        json.JSONLoad("http://www.1688wan.com/majax.action?method=getGiftList&pageno="+mPageNo,
                new JSONLoader.OnJSONLoaderListener() {
                    @Override
                    public void onJSONLoader(String json) {
                        Gson gson = new Gson();
                        Libao libao = gson.fromJson(json,Libao.class);
                        //添加数据
                        mLibaoAdapter.addAll(libao.getList());
                        if(mPageNo > libao.getPageno()){
                            Toast.makeText(MainActivity.this,"没有更多的数据了",Toast.LENGTH_LONG).show();
                            mPageNo = libao.getPageno();
                        }
                    }
                });

    }

}
