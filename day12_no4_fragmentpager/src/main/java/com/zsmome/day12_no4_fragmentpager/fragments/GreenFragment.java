package com.zsmome.day12_no4_fragmentpager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsmome.day12_no4_fragmentpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GreenFragment extends Fragment {
    //加载视图
    private boolean isPrepare = false;
    //可见
    private boolean isVisible = false;

    public GreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //视图加载完成
        Log.i("green", "...............");
        isPrepare = true;
        loadData();
        return inflater.inflate(R.layout.fragment_green, container, false);
    }

    /**
     * 模拟加载数据
     */
    private void loadData() {
        if(isVisible && isPrepare) {
            Log.i("load", "加载成功");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        loadData();
    }
}
