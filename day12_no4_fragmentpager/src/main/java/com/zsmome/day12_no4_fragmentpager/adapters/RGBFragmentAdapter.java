package com.zsmome.day12_no4_fragmentpager.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ViewPager的适配器
 * Created by Administrator on 2017/4/19.
 */

public class RGBFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public RGBFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
