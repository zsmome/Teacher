package com.zsmome.project_libaojinlin.fragments.tese;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.tese.BdxqsListViewAdapter;
import com.zsmome.project_libaojinlin.fragments.BaseFragment;
import com.zsmome.project_libaojinlin.model.TeseBdxqs;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BdxqsFragment extends BaseFragment {
    //整个视图
    private View view;
    //声明组件
    private ListView mBdxqsLv;
    //适配器
    private BdxqsListViewAdapter mBdxqsLvAda;
    //暴打星期三数据
    private List<TeseBdxqs.ListBean> mData;
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_bdxqs, container, false);
        return view;
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void initData() {
        mBdxqsLv = (ListView) view.findViewById(R.id.tese_bdxqs_lv);
        //获取Json数据
        JSONParseUtil jpu = new JSONParseUtil();
        String url = MainActivity.IAddress.getUrl_tese_bdxqs();
        jpu.parseJSON(url, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                TeseBdxqs bdxqs = new Gson().fromJson(json, TeseBdxqs.class);
                mData = bdxqs.getList();
                initBdxqsListView();
            }
        });
    }

    /**
     * 暴打星期三列表
     */
    private void initBdxqsListView() {
        mBdxqsLvAda = new BdxqsListViewAdapter(getActivity(), mData);
        mBdxqsLv.setAdapter(mBdxqsLvAda);

        //设置列表监听
        mBdxqsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
                //跳转页面
                gotoBdxqsInfoActivity(position);
            }
        });
    }
    /**
     * 跳转页面
     */
    private void gotoBdxqsInfoActivity(int position) {
        //获取数据
        TeseBdxqs.ListBean data = mData.get(position);
        //跳转页面
        Intent intent = new Intent(getActivity(), BdxqsInfoActivity.class);
        //详情网址
        String url = MainActivity.IAddress.getUrl_bdxqs_info();
        //游戏id号
        intent.putExtra("sid", url + data.getSid());
        //图片
        intent.putExtra("icon", MainActivity.IAddress.getUrl_base()+data.getIconurl());
        //标题
        intent.putExtra("name", data.getName());
        //时间
        intent.putExtra("addtime", data.getAddtime());
        //描述
        intent.putExtra("descs", data.getDescs());
        //跳转
        startActivity(intent);
    }
}
