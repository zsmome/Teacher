package com.zsmome.day09_homework_news_simpleadapter;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.zsmome.day09_homework_news_simpleadapter.util.LoadImageUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SimpleAdapter步骤：
 * 1.准备数据（布局资源，填充数据，依情形造实体类）
 * 2.创建SimpleAdapter
 * 3.设置Adapter
 */
public class MainActivity extends AppCompatActivity {
    //声明组件
    private ListView mNewsLv;
    //声明SimpleAdapter
    private SimpleAdapter mNewsSimpleAdapter;
    //列表长度
    private static final int LIST_VIEW_LEN = 15;
    //网络图片地址
    String url = "https://leanote.com/public/upload/375/58411590ab64417032005d16/images/logo/dd6a0bf3076b53c7263565eec792e9f3.jpg";
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
     * 初始化组件
     */
    private void initViews() {
        mNewsLv = (ListView) findViewById(R.id.newsLv);
        //准备数据
        List<Map<String, Object>> newsList = new ArrayList<>();
        for(int i=0; i<LIST_VIEW_LEN; i++) {
            final Map<String, Object> item = new HashMap<>();
            //加载网络图片
            LoadImageUtil liu = new LoadImageUtil();
            liu.loadImage(url, new LoadImageUtil.OnLoadImageListener() {
                @Override
                public void OnLoadImageComplete(Bitmap bitmap) {
                    item.put("image", bitmap);
                }
            });
            item.put("title", "标题"+i);
            item.put("content", "最新研究表明，多吃水果可以延年益寿！");
            newsList.add(item);
        }
        mNewsSimpleAdapter = new SimpleAdapter(this,
                newsList,
                R.layout.news_item_simple,
                new String[] {"image", "title", "content"},
                new int[] {R.id.imageIv, R.id.titleTv, R.id.contentTv});
        //加载网络图片，必须设置
        mNewsSimpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if(view instanceof ImageView && data instanceof Bitmap) {
                    ImageView image = (ImageView)view;
                    image.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        mNewsLv.setAdapter(mNewsSimpleAdapter);
    }
}
