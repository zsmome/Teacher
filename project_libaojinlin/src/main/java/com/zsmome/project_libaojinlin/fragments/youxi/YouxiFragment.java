package com.zsmome.project_libaojinlin.fragments.youxi;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.FragmentViewPagerAdapter;
import com.zsmome.project_libaojinlin.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class YouxiFragment extends BaseFragment {
    //主视图
    private View mRootView;
    //声明组件
    private ViewPager mYouxiVp;
    private TabLayout mTitleTab;
    /*fragment相关*/
    //列表集合
    private List<Fragment> mFragmentList;
    //Fragment集合
    private FragmentViewPagerAdapter mFragAda;
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.fragment_youxi, container, false);
        return mRootView;
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void initData() {
        mYouxiVp = (ViewPager) mRootView.findViewById(R.id.youxi_vp);
        mTitleTab = (TabLayout) mRootView.findViewById(R.id.youxi_title_tab);
        initYouxiVp();
    }

    /**
     * 初始化Youxi ViewPager
     */
    private void initYouxiVp() {
        /*添加显示的Fragment*/
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new Youxi_KaiFfu_Fragment());
        mFragmentList.add(new Youxi_KaiCe_Fragment());
        mFragAda = new FragmentViewPagerAdapter(getFragmentManager(), mFragmentList);
        mYouxiVp.setAdapter(mFragAda);
        //设置关联
        mTitleTab.setupWithViewPager(mYouxiVp);
        /*获得标题资源*/
        Resources res = getResources();
        String[] title = res.getStringArray(R.array.youxi_fragment_title);
        for(int i=0; i<mFragmentList.size(); i++) {
            mTitleTab.getTabAt(i).setText(title[i]);
        }
    }
}
