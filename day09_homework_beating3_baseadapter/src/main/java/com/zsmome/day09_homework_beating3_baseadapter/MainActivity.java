package com.zsmome.day09_homework_beating3_baseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zsmome.day09_homework_beating3_baseadapter.adapters.BeatenAdapter;
import com.zsmome.day09_homework_beating3_baseadapter.models.Beaten;
import com.zsmome.day09_homework_beating3_baseadapter.util.JSONParseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 步骤：
 * 1.设置连网权限
 * 2.布局
 * 3.model(加入Gson)
 * 4.util(Json+图片)
 * 5.自定义Adapter
 */
public class MainActivity extends AppCompatActivity {
    //声明组件
    private ListView mBeatenLv;
    //网址
    private String BEANTEN_URL = "http://www.1688wan.com/majax.action?method=bdxqs&pageNo=0";
    //适配器
    private BeatenAdapter mBeatenAdapter;
    //数据
    private List<Beaten.ListBean> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 初始化界面
     */
    private void initViews() {
        mBeatenLv = (ListView) findViewById(R.id.beatenLv);
        mData = new ArrayList<>();
        mBeatenAdapter = new BeatenAdapter(this, mData);
        mBeatenLv.setAdapter(mBeatenAdapter);
        //加载数据
        loadData();
    }

    /**
     * 加载数据
     */
    private void loadData() {
        JSONParseUtil jpu = new JSONParseUtil();
        jpu.parseJSON(BEANTEN_URL, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Gson gson = new Gson();
                Beaten beaten = gson.fromJson(json, Beaten.class);
                mBeatenAdapter.addAll(beaten.getList());
            }
        });
    }
}
