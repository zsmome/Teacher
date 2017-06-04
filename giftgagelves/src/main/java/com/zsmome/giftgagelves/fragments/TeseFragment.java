package com.zsmome.giftgagelves.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsmome.giftgagelves.R;

/**
 * 特色页面
 * A simple {@link Fragment} subclass.
 */
public class TeseFragment extends BaseFragment {
    //声明视图
    private View mView;
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.fragment_tese, container, false);
        return mView;
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void initData() {

    }
}
