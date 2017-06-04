package com.zsmome.project_libaojinlin.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsmome.project_libaojinlin.R;

/**
 * Fragment基本类
 * 功能：实现懒加载
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    private View mRootView;
    private Context mContext;
    private boolean isPrepare;
    private boolean isVisible;
    private boolean isFirst = true;
    public BaseFragment() {
        // Required empty public constructor
    }
    /**
     * 是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            Log.i("BaseFragment", "setUserVisibleHint");
            isVisible = true;
            //可见时加载数据
            lazyLoad();
        } else {
            isVisible = false;
            //不可见时的操作
            onInVisible();
        }
    }

    /**
     *  创建时获得上下文
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    /**
     *  创建视图时
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(mRootView == null) {
            //初始化视图
            mRootView = initViews(inflater, container);
        }
        return mRootView;
    }
    /**
     * 创建Activity时
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //视图加载完成
        isPrepare = true;
        //懒加载数据(懒:lazy)
        lazyLoad();
    }
    /**
     * 初始视图
     */
    public abstract View initViews(LayoutInflater inflater, ViewGroup container);
    /**
     * 不可见时的操作
     */
    public abstract void onInVisible();
    /**
     * 懒加载数据
     */
    protected void lazyLoad() {
        if(!isPrepare || !isVisible || !isFirst) {
            return;
        }
        //初始数据
        initData();
        //只加载一次
        isFirst = false;
    }

    /**
     * 初始数据
     */
    public abstract void initData();
}
