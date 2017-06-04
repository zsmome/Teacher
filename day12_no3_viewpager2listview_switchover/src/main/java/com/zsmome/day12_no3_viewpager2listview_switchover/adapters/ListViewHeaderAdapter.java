package com.zsmome.day12_no3_viewpager2listview_switchover.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * ListView头部适配器
 * 图片轮播效果
 * Created by Administrator on 2017/4/17.
 */

public class ListViewHeaderAdapter extends PagerAdapter {
    //集合
    private List<View> mDataList;

    public ListViewHeaderAdapter(List<View> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mDataList.get(position));
        return mDataList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mDataList.get(position));
    }
}
