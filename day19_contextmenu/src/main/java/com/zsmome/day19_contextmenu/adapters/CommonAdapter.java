package com.zsmome.day19_contextmenu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的适配器
 * Created by xray on 17/4/22.
 */

public abstract class CommonAdapter<T> extends BaseAdapter{

    private Context mContext;
    private List<T> mData;
    private LayoutInflater mInflater;
    private int mLayoutId;

    public CommonAdapter(Context mContext, List<T> mData, int mLayoutId) {
        this.mContext = mContext;
        this.mData = mData;
        this.mLayoutId = mLayoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //创建ViewHolder
        ViewHolder viewHolder = ViewHolder.get(mContext,convertView,parent,mLayoutId);
        //进行视图的设置
        setViews(viewHolder,mData.get(position));
        return viewHolder.getConvertView();
    }

    /**
     * 添加更多数据
     * @param data
     */
    public void addAll(List<T> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     * @param data
     */
    public void refresh(List<T> data){
        mData = new ArrayList<>();
        addAll(data);
    }

    /**
     * 用于设置视图
     * @param viewHolder
     * @param data
     */
    public abstract void setViews(ViewHolder viewHolder,T data);

}
