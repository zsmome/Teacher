package com.zsmome.day12_no2_viewpager2listview;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zsmome.day12_no2_viewpager2listview.adapters.HeaderViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 将ViewPager添加到ListView的头部视图
 */
public class MainActivity extends AppCompatActivity {
    //声明组件
    private ListView mViewPagerLv;
    private View mHeaderView;
    private ViewPager mHeaderVp;
    //图片资源
    private final int[] mImageId = {R.mipmap.a1, R.mipmap.a2, R.mipmap.a3};
    //适配器
    private ArrayAdapter<String> mArrayAda;
    private List<View> mHeaderVpImageData;
    private List<String> mData;
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
        mViewPagerLv = (ListView) findViewById(R.id.viewpager_lv);
        //初始化头部
        initHeaderView();
        mData = new ArrayList<>();
        //准备数据
        for(int i=0; i<20; i++) {
            mData.add("Item -- "+i);
        }
        mArrayAda = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                mData);
        mViewPagerLv.addHeaderView(mHeaderView);
        mViewPagerLv.setAdapter(mArrayAda);
    }
    /**
     * 初始化头部
     */
    private void initHeaderView() {
        //动态加载布局
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.header_listview, mViewPagerLv, false);
        //图片轮播
        mHeaderVp = (ViewPager) mHeaderView.findViewById(R.id.header_vp);
        //加载图片轮播
        initHeaderVp();
    }
    /**
     * 改成网络图片
     * 初始头部ViewPager
     */
    private void initHeaderVp() {
        mHeaderVpImageData = new ArrayList<>();
        for(int i : mImageId) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(i);
            mHeaderVpImageData.add(iv);
        }
        mHeaderVp.setAdapter(new HeaderViewPagerAdapter(mHeaderVpImageData));
    }
}
