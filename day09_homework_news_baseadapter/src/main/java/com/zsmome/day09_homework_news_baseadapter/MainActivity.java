package com.zsmome.day09_homework_news_baseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;

import com.zsmome.day09_homework_news_baseadapter.model.News;
import com.zsmome.day09_homework_news_baseadapter.model.NewsBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义BaseAdapter步骤：
 *1.准备数据（布局资源，实体类）
 * 2.创建自定义适配器
 * 3.设置适配器
 */
public class MainActivity extends AppCompatActivity {
    //声明组件
    private ListView mNewsLv;
    //网络图片地址
    private String[] imageUrl = null;
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
        imageUrl = getResources().getStringArray(R.array.image);
        //准备数据
        List<News> newsList = new ArrayList<>();
        for(int i=0; i<15; i++) {
            newsList.add(new News(imageUrl[i%imageUrl.length], "标题"+i, "我是新闻内容！"));
        }
        //创建自定义适配器
        NewsBaseAdapter newsAdapter = new NewsBaseAdapter(this, newsList);
        mNewsLv.setAdapter(newsAdapter);
    }
}
