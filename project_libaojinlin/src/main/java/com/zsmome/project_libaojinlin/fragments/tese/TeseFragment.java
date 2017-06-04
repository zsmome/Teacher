package com.zsmome.project_libaojinlin.fragments.tese;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.FragmentViewPagerAdapter;
import com.zsmome.project_libaojinlin.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 特色页面
 * A simple {@link Fragment} subclass.
 */
public class TeseFragment extends BaseFragment {
    //整个视图
    private View view;
    //声明组件
    private TabLayout mTeseTab;
    private ViewPager mTeseVp;
    /*特色*/
    //特色适配器
    private FragmentViewPagerAdapter mTeseVpAda;
    //特色页面集合
    private List<Fragment> mTeseFragList;
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_tese, container, false);
        return view;
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void initData() {
        mTeseTab = (TabLayout) view.findViewById(R.id.tese_tab);
        mTeseVp = (ViewPager) view.findViewById(R.id.tese_vp);
        //添加两个子页面
        mTeseFragList = new ArrayList<>();
        mTeseFragList.add(new BdxqsFragment());
        mTeseFragList.add(new XyzcFragment());
        mTeseVpAda = new FragmentViewPagerAdapter(getFragmentManager(), mTeseFragList);
        //设置特色适配器
        mTeseVp.setAdapter(mTeseVpAda);
        //设置标签关联
        mTeseTab.setupWithViewPager(mTeseVp);
        //获取标签
        String[] tabs = getResources().getStringArray(R.array.tese_fragment_title);
        //设置标签
        for(int i=0; i<mTeseFragList.size(); i++) {
            mTeseTab.getTabAt(i).setText(tabs[i]);
        }
    }
}
