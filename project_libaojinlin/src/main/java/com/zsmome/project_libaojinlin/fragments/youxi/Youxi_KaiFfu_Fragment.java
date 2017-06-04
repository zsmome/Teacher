package com.zsmome.project_libaojinlin.fragments.youxi;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zsmome.project_libaojinlin.InterfaceAddress;
import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.youxi.KaiFuGroupListViewAdapter;
import com.zsmome.project_libaojinlin.fragments.BaseFragment;
import com.zsmome.project_libaojinlin.model.YouXi;
import com.zsmome.project_libaojinlin.model.YouxiKaifu;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Youxi_KaiFfu_Fragment extends BaseFragment {
    //视图
    private View view;
    //声明组件
    private ExpandableListView mKaiFuLv;
    /*KaiFu列表*/
    //适配器
    private KaiFuGroupListViewAdapter mKaiFuLvAda;
    //数据
    private List<YouXi.InfoBean> mKaiFuData;
    private List<String> mGrouopList = new ArrayList<>();
    private Map<String, List<YouxiKaifu>> mChildMap = new HashMap<>();
    //其础网址
    static final String URL_BASE = MainActivity.IAddress.getUrl_base();
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_youxi_kai_fu_, container, false);
        return view;
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void initData() {
        mKaiFuLv = (ExpandableListView) view.findViewById(R.id.youxi_kaifu_elv);
        //获得数据
        JSONParseUtil liu = new JSONParseUtil();
        String url = MainActivity.IAddress.getUrl_kaifu_kaifu();
        liu.parseJSON(url, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Log.i("kaifu", json);
                YouXi youxi = new Gson().fromJson(json, YouXi.class);
                mKaiFuData = youxi.getInfo();
                initKaiFuListView(mKaiFuData);
            }
        });
    }

    /**
     * 初始化开服列表
     */
    private void initKaiFuListView(List<YouXi.InfoBean> mData) {
        data2KaiFu(mKaiFuData);
        //创建适配器
        mKaiFuLvAda = new KaiFuGroupListViewAdapter(getActivity(), mGrouopList, mChildMap);
        //设置适配器
        mKaiFuLv.setAdapter(mKaiFuLvAda);
        //展开所有项
        expandListView();
        //设置固定展开
        fixedExpand();
        //设置子项监听器
        childListener();
        mKaiFuLv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
    }

    /**
     * 子项监听器
     */
    private void childListener() {
        mKaiFuLv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), ""+childPosition, Toast.LENGTH_SHORT).show();
                String group = mGrouopList.get(groupPosition);
                YouxiKaifu kaifu = mChildMap.get(group).get(childPosition);
                gotoKaifuInfoActivity(kaifu);
                return false;
            }
        });
    }

    /**
     * 跳转到开服详情页面
     */
    private void gotoKaifuInfoActivity(YouxiKaifu kaifu) {
        Intent intent = new Intent(getActivity(), KaifuInfoActivity.class);
        intent.putExtra("id", kaifu.getId());
        startActivity(intent);
    }

    /**
     * 转换数据
     * @param mData
     */
    private void data2KaiFu(List<YouXi.InfoBean> mData) {
        for(YouXi.InfoBean info : mData) {
            String id = info.getGid();
            String addTime = info.getAddtime();
            String iconUrl = URL_BASE + info.getIconurl();
            String name = info.getGname();
            String startTime = info.getLinkurl();
            String area = info.getArea();
            String operator = info.getOperators();
            YouxiKaifu kaifu = new YouxiKaifu(id, iconUrl, name, startTime, area, operator);
            //不存在，就添加到mGroup中
            if(!mGrouopList.contains(addTime)) {
                Log.i("kaifu", addTime);
                mGrouopList.add(addTime);
                //添加数据到mChildMap中
                List<YouxiKaifu> list = new ArrayList<>();
                list.add(kaifu);
                mChildMap.put(addTime, list);
            } else {
                mChildMap.get(addTime).add(kaifu);
            }
        }
    }
    /**
     * 固定展开
     */
    private void fixedExpand() {
        mKaiFuLv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    /**
     * 展开所有项
     */
    private void expandListView() {
        for(int i=0; i<mKaiFuLvAda.getGroupCount(); i++) {
            mKaiFuLv.expandGroup(i);
        }
    }
}
