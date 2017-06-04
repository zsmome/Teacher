package com.zsmome.fanyiscreen.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 礼包列表的头部适配器
 * Created by Administrator on 2017/4/20.
 */

public class VPagerAdapter extends PagerAdapter {
    private List<View> list;

    public VPagerAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
    public void refresh(List<View> list) {
        list = new ArrayList<>();
        list.addAll(list);
        notifyDataSetChanged();
    }
}
