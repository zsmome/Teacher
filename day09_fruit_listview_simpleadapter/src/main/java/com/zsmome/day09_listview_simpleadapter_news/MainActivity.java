package com.zsmome.day09_listview_simpleadapter_news;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView mNewsLv;
    private List<Map<String, Object>> mNews;
    //图片资源
    private int[] imageId = {R.mipmap.a1, R.mipmap.a2, R.mipmap.a3, R.mipmap.a4, R.mipmap.a5,
            R.mipmap.a6, R.mipmap.a7, R.mipmap.a8, R.mipmap.a9, R.mipmap.a10,
            R.mipmap.a11, R.mipmap.a12, R.mipmap.a13, R.mipmap.a14, R.mipmap.a15,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.准备数据(显示数据, 布局资源, 显示的View布局资源)
        //2.创建SimpleAdapter
        //3.假设显示视图为v.，v.setAdapter(创建的ArrayAdapter)
        initData();
        initViews();
    }

    /**
     * 准备数据
     */
    private void initData() {
        mNews = new ArrayList<>();
        for(int i=0; i<imageId.length; i++) {
            Map<String, Object> map = new HashMap<>();
            //图片
            map.put("image", imageId[i]);
            //标题
            map.put("title", "标题"+i);
            //描述
            map.put("desc", "描述性文字，这是水果图片!");
            mNews.add(map);
        }
    }
    private void initViews() {
        mNewsLv = (ListView) findViewById(R.id.newsLv);

        SimpleAdapter fruitAdapter = new SimpleAdapter(this,
                mNews,
                R.layout.fruit_item,
                new String[] {"image", "title", "desc"},
                new int[] {R.id.imageIv, R.id.titleTv, R.id.descTv});
        mNewsLv.setAdapter(fruitAdapter);
        fruitAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                switch(view.getId()) {
                    case R.id.imageIv:
                        ImageView image = (ImageView) view;
                        int id = Integer.parseInt(data.toString());
                        image.setImageResource(id);
                        break;
                    case R.id.titleTv:
                        TextView textView = (TextView) view;
                        SpannableString ss = new SpannableString(textRepresentation);
                        ss.setSpan(new ForegroundColorSpan(Color.RED),
                                0,
                                textRepresentation.length(),
                                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        textView.setText(ss);
                        break;
                    case R.id.descTv:
                        TextView textView1 = (TextView) view;
                        textView1.setText(textRepresentation);
                }
                //必须返回true
                return true;
            }
        });
    }
}
