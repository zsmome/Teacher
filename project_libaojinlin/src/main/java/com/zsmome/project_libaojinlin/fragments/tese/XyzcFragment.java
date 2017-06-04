package com.zsmome.project_libaojinlin.fragments.tese;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.tese.XyzcListViewAdapter;
import com.zsmome.project_libaojinlin.fragments.BaseFragment;
import com.zsmome.project_libaojinlin.model.TeseXyzc;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class XyzcFragment extends BaseFragment {
    //整个视图
     private View view;
    //声明组件
    private ListView mTeseXyzcLv;
    //新游周刊适配器
    private XyzcListViewAdapter mTeseXyzcLvAda;
    //数据
    private List<TeseXyzc.ListBean> mData;
    //页码
    private static int mPageNo = 0;
    //基础网址
    public static final String URL_BASE = MainActivity.IAddress.getUrl_base();
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_xyzc, container, false);
        return view;
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void initData() {
        mTeseXyzcLv = (ListView) view.findViewById(R.id.tese_xyzc_lv);
        JSONParseUtil jpu = new JSONParseUtil();
        jpu.parseJSON(MainActivity.IAddress.getUrl_tese_xyzk() + mPageNo, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Log.i("tese", json);
                TeseXyzc xyzc = new Gson().fromJson(json, TeseXyzc.class);
                mData = xyzc.getList();
                initXyzkListView();
            }
        });
    }

    private void initXyzkListView() {
        mTeseXyzcLvAda = new XyzcListViewAdapter(getActivity(), mData);
        mTeseXyzcLv.setAdapter(mTeseXyzcLvAda);
        //设置列表监听
        mTeseXyzcLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转页面
                gotoXyzcInfoActivity(mData.get(position));
            }
        });
    }

    /**
     * 跳转详情页面
     */
    private void gotoXyzcInfoActivity( TeseXyzc.ListBean data) {
        Intent intent = new Intent(getActivity(), XyzcInfoActivity.class);
        intent.putExtra("id", data.getId()+"");
        intent.putExtra("name", data.getName());
        intent.putExtra("iconUrl", URL_BASE + data.getIconurl());
        intent.putExtra("authorimg", URL_BASE + data.getAuthorimg());
        intent.putExtra("descs", data.getDescs());
        startActivity(intent);
    }
}
