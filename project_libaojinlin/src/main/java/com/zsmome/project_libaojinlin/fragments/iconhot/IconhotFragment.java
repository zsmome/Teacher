package com.zsmome.project_libaojinlin.fragments.iconhot;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.iconhot.IconhotGridViewAdapter;
import com.zsmome.project_libaojinlin.adapters.iconhot.IconhotListViewAdapter;
import com.zsmome.project_libaojinlin.fragments.BaseFragment;
import com.zsmome.project_libaojinlin.model.IconhotBoutique;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IconhotFragment extends BaseFragment {
    //视图
    View view;
    //声明组件
    private ListView mIconhot_boutique_lv;
    private GridView mIconhot_hot_gv;
    /*精品列表*/
    //适配器
    private IconhotListViewAdapter mBoutique_lvAda;
    //数据
    private List<IconhotBoutique.InfoBean.Push1Bean> mBoutique_lvData;
    /*热门列表*/
    //适配器
    private IconhotGridViewAdapter mHot_gvAda;
    //数据
    private List<IconhotBoutique.InfoBean.Push2Bean> mHot_gvData;
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_iconhot, container, false);
        return view;
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void initData() {
        mIconhot_boutique_lv = (ListView) view.findViewById(R.id.iconhot_boutique_lv);
        mIconhot_hot_gv = (GridView) view.findViewById(R.id.iconhot_hot_gv);
        //获取Json内容
        JSONParseUtil jpu = new JSONParseUtil();
        String url = MainActivity.IAddress.getUrl_iconhot();
        jpu.parseJSON(url, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                IconhotBoutique iconhot = new Gson().fromJson(json, IconhotBoutique.class);
                mBoutique_lvData = iconhot.getInfo().getPush1();
                mHot_gvData = iconhot.getInfo().getPush2();
                initBoutiqueListView();
                initHotGridView();
            }
        });
    }

    /**
     * 热门列表
     */
    private void initHotGridView() {
        mHot_gvAda = new IconhotGridViewAdapter(getActivity(), mHot_gvData);
        mIconhot_hot_gv.setAdapter(mHot_gvAda);
    }

    /**
     * 精品列表
     */
    private void initBoutiqueListView() {
        mBoutique_lvAda = new IconhotListViewAdapter(getActivity(), mBoutique_lvData);
        mIconhot_boutique_lv.setAdapter(mBoutique_lvAda);
    }
}
