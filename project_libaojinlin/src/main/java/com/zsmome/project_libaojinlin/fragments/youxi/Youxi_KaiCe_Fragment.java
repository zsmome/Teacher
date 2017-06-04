package com.zsmome.project_libaojinlin.fragments.youxi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.youxi.KaiCeListViewAdapter;
import com.zsmome.project_libaojinlin.fragments.BaseFragment;
import com.zsmome.project_libaojinlin.model.KaiCe;
import com.zsmome.project_libaojinlin.model.YouXi;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Youxi_KaiCe_Fragment extends BaseFragment {
    /*整个视图*/
    private View view;
    /*声明组件*/
    private ListView mKaiCeLv;
    /*开测适配器*/
    private KaiCeListViewAdapter mKaiCeAda;
    //数据
    private List<KaiCe.InfoBean> mKaiCeData;
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_youxi_kai_ce_, container, false);
        return view;
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void initData() {
        mKaiCeLv = (ListView) view.findViewById(R.id.youxi_kaice_lv);
        JSONParseUtil jpu = new JSONParseUtil();
        jpu.parseJSON(MainActivity.IAddress.getUrl_kaifu_kaice(), new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                KaiCe kaice = new Gson().fromJson(json, KaiCe.class);
                //获得数据
                mKaiCeData = kaice.getInfo();
                //初始开测列表
                initKaiCeListView();
            }
        });
    }

    /**
     * 初始开测列表
     */
    private void initKaiCeListView() {
        mKaiCeAda = new KaiCeListViewAdapter(getActivity(), mKaiCeData);
        mKaiCeLv.setAdapter(mKaiCeAda);
    }
}
