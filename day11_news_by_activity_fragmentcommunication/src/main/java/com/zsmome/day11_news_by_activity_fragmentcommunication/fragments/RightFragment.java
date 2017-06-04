package com.zsmome.day11_news_by_activity_fragmentcommunication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zsmome.day11_news_by_activity_fragmentcommunication.MainActivity;
import com.zsmome.day11_news_by_activity_fragmentcommunication.R;


/**
 * Created by Administrator on 2017/4/15.
 */

public class RightFragment extends Fragment {
    private TextView mContentTv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right, container, false);
        initViews(view);
        return view;
    }

    /**
     * 初始化组件
     * @param view
     */
    private void initViews(View view) {
        mContentTv = (TextView) view.findViewById(R.id.content_tv);
    }

    public void showTitle(String title) {
        mContentTv.setText(title);
    }
}
