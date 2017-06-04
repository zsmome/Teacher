package com.zsmome.day13_no1_baseadaptercommpand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.zsmome.day13_no1_baseadaptercommpand.adapters.ListViewGeneralAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mLv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListView();
    }

    /**
     * 初始列表
     */
    private void initListView() {
        List<String> list = new ArrayList<>();
        for(int i=0; i<20; i++) {
            list.add("列表"+i);
        }
        ListViewGeneralAdapter<List<String>> mAda = new ListViewGeneralAdapter(this, list, R.layout.test_commpand);
        mLv.setAdapter(mAda);
    }

    /**
     * 初始视图
     */
    private void initViews() {
        mLv = (ListView) findViewById(R.id.test_commpand_lv);
    }
}
