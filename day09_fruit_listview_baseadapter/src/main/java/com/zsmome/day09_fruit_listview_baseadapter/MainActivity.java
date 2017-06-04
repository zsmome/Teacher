package com.zsmome.day09_fruit_listview_baseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.zsmome.day09_fruit_listview_baseadapter.fruit.Fruit;
import com.zsmome.day09_fruit_listview_baseadapter.fruit.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //声明组件
    private ListView mFruitLv;
    //列表长度
    private static final int LIST_LEN = 100;
    private FruitAdapter mFruitAdapter;
    private List<Fruit> mFruits;
    //图片资源
    private int[] imageId = {R.mipmap.a1, R.mipmap.a2, R.mipmap.a3, R.mipmap.a4, R.mipmap.a5,
            R.mipmap.a6, R.mipmap.a7, R.mipmap.a8, R.mipmap.a9, R.mipmap.a10,
            R.mipmap.a11, R.mipmap.a12, R.mipmap.a13, R.mipmap.a14, R.mipmap.a15,};
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
        mFruitLv = (ListView) findViewById(R.id.fruitLv);
        //准备数据
        mFruits = new ArrayList<>();
        for(int i=0; i<LIST_LEN; i++) {
            mFruits.add(new Fruit(imageId[i%imageId.length],
                    "标题"+i,
                    "我是水果哟~"));
        }
        mFruitAdapter = new FruitAdapter(this, mFruits);
        mFruitLv.setAdapter(mFruitAdapter);
    }
}
