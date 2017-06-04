package com.zsmome.day11_news_by_activity_fragmentcommunication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.zsmome.day11_news_by_activity_fragmentcommunication.MainActivity;
import com.zsmome.day11_news_by_activity_fragmentcommunication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/15.
 */

public class LeftFragment extends Fragment {
    //声明组件
    private ListView mNewsLv;
    private List<String> mData;
    private String title;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left, container, false);
        initViews(view);
        return view;
    }

    /**
     * 初始化组件
     */
    private void initViews(View view) {
        mNewsLv = (ListView) view.findViewById(R.id.newsLv);
        //准备数据
        mData = new ArrayList<>();
        for(int i=0; i<10; i++) {
            mData.add("新闻"+i);
        }
        //适配器
        final ArrayAdapter<String> mNewsAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                mData);
        //设置适配器
        mNewsLv.setAdapter(mNewsAdapter);
        //设置监听事件

        mNewsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title = mData.get(position);
                ((MainActivity)getActivity()).left2Right();
            }
        });
    }
    public String getTitle() {
        return title;
    }
}
