package com.zsmome.day14_group_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.zsmome.day14_group_listview.adapters.GroupListViewAdapter;
import com.zsmome.day14_group_listview.models.Fruit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    /*声明组件*/
    private ExpandableListView mFruitGroupLv;
    /*适配器*/
    private GroupListViewAdapter mFruitAda;
    /*准备数据*/
    List<String> mGroupList;
    Map<String, List<Fruit>> mChildMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFruitGroupListView();
    }

    /**
     * 初始化水果分组列表
     */
    private void initFruitGroupListView() {
        //准备数据
        mGroupList = new ArrayList<>();
        mChildMap = new HashMap<>();
        //组
        mGroupList.add("川菜");
        mGroupList.add("湘菜");
        mGroupList.add("广西菜");
        //子
        for(int i=0; i<mGroupList.size(); i++) {
            int count = (int) (Math.random() * 10 + 5);
            List<Fruit> fruits = new ArrayList<>();
            for(int j=0; j<count; j++) {
                Fruit fruit = new Fruit(R.mipmap.ic_launcher, mGroupList.get(i) + j);
                fruits.add(fruit);
            }
            mChildMap.put(mGroupList.get(i), fruits);
        }
        //创建适配器
        mFruitAda = new GroupListViewAdapter(this, mGroupList, mChildMap);
        //设置适配器
        mFruitGroupLv.setAdapter(mFruitAda);
        //展开所有项
        expandGroups();
        //设置子选项监听
        mFruitGroupLv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Fruit fruit = mChildMap.get(mGroupList.get(groupPosition)).get(childPosition);
                Toast.makeText(MainActivity.this, fruit.getDesc() , Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mFruitGroupLv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        mFruitGroupLv = (ExpandableListView) findViewById(R.id.fruit_elv);
    }
    /**
     * 展开所有组
     */
    private void expandGroups() {
        for(int i = 0; i< mGroupList.size(); i++) {
            mFruitGroupLv.expandGroup(i);
        }
    }
}
