package com.zsmome.day13_no1_baseadaptercommpand.adapters;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsmome.day13_no1_baseadaptercommpand.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public class ListViewGeneralAdapter<T> extends BaseAdapter {
    //上下文，集合
    private Context mContext;
    private List<T> mData;
    //布局id
    private int mLayoutId;
    private LayoutInflater mInflater;

    public ListViewGeneralAdapter(Context mContext, List<T> mData, int mLayoutId) {
        this.mContext = mContext;
        this.mData = mData;
        this.mLayoutId = mLayoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //实例ViewHolder
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, mLayoutId);
        //获得控件
        TextView tv = viewHolder.getView(R.id.image_iv);
        //设置控件
        tv.setText("列表" + position);
        return viewHolder.getConvertView();
    }
    /**
     * ViewHolder存储控件
     */
    static class ViewHolder {
        //存储所有视图的集合
        private SparseArray<View> mViews;
        //转换视图
        private View mConvertView;
        private ViewHolder(Context context, ViewGroup parent, int layoutId) {
            this.mViews = new SparseArray<>();
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            mConvertView.setTag(this);
        }
        /**
         * 获得一个ViewHolder对象
         * 单例设计
         */
        public static ViewHolder get(Context context, View convertView,
                                     ViewGroup parent, int layoutId) {
            if(convertView == null) {
                return new ViewHolder(context, parent, layoutId);
            }
            return (ViewHolder) convertView.getTag();
        }
        /**
         * 通过控件的Id获取控件，如果没有则加入mViews
         */
        public <T extends  View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if(view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
        /**
         * 获得转换视图
         */
        public View getConvertView() {
            return mConvertView;
        }
    }
}
