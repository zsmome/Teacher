package com.zsmome.giftgagelves.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zsmome.giftgagelves.InterfaceAddress;
import com.zsmome.giftgagelves.MainActivity;
import com.zsmome.giftgagelves.R;
import com.zsmome.giftgagelves.adapters.CommonAdapter;
import com.zsmome.giftgagelves.adapters.ViewHolder;
import com.zsmome.giftgagelves.model.LiBao;
import com.zsmome.giftgagelves.utils.JSONParseUtil;

/**
 * 礼包页面
 * A simple {@link Fragment} subclass.
 */
public class LibaoFragment extends BaseFragment {
    //声明视图
    private View mView;
    //声明控件
    private ListView mLv;
    //页号
    private int mpageNo = 0;
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        Log.i("libao", "123");
        mView = inflater.inflate(R.layout.fragment_libao, container, false);
        return mView;
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void initData() {
        Log.i("libao", "initData");
        mLv = (ListView) mView.findViewById(R.id.listview_libao);
        String url = MainActivity.IAddress.getUrl_liBaoLv();
        new JSONParseUtil().parseJSON(url + mpageNo, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Log.i("libao", json);
                LiBao libao = new Gson().fromJson(json, LiBao.class);
                initListView(libao);
            }
        });
    }

    /**
     * 初始化礼包列表
     * @param libao
     */
    private void initListView(LiBao libao) {
        CommonAdapter<LiBao.ListBean> mListAda = new CommonAdapter<LiBao.ListBean>(getActivity(), libao.getList(), R.layout.listview_libao) {
            @Override
            public void setViews(ViewHolder viewHolder, LiBao.ListBean data) {
                //图标
                String iconUrl = MainActivity.IAddress.getUrl_base()+data.getIconurl();
                viewHolder.setImageViewURL(R.id.listview_libao_icon, iconUrl);
                //名字
                viewHolder.setTextViewText(R.id.listview_libao_name, data.getGname());
                //类型
                viewHolder.setTextViewText(R.id.listview_libao_teqian, data.getPtype());
                //剩余
                String remain = "剩余:"+data.getNumber();
                viewHolder.setTextViewTextWithColor(R.id.listview_libao_remain, remain, 3, remain.length(), Color.GREEN);
                //时间
                viewHolder.setTextViewText(R.id.listview_libao_time, "时间:"+data.getAddtime());
            }
        };
        mLv.setAdapter(mListAda);
    }
}
