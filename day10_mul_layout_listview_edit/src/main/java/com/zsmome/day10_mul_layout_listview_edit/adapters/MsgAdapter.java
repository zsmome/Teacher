package com.zsmome.day10_mul_layout_listview_edit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsmome.day10_mul_layout_listview_edit.R;
import com.zsmome.day10_mul_layout_listview_edit.models.Msg;

import java.util.List;

/**
 * 实现多布局
 * 必须实现getItemViewType和getItemViewTypeCount
 * Created by Administrator on 2017/4/9.
 */

public class MsgAdapter extends BaseAdapter {
    //上下文，集合
    private Context mContext;
    private List<Msg> mData;

    public static final int TYPE_ME = 0;
    public static final int TYPE_OTHER = 1;

    public MsgAdapter(Context mContext, List<Msg> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public MsgAdapter() {
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 返回不同类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if("me".equals(mData.get(position).getWho())) {
            return TYPE_ME;
        }
        return TYPE_OTHER;
    }

    /**
     * 类型的数量
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MeHolder me = null;
        OtherHolder other = null;
        int type = getItemViewType(position);
        //进行复用
        if(convertView == null) {
            switch(type) {
                case TYPE_ME:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.me_item_listview, parent, false);
                    me = new MeHolder(convertView);
                    break;
                case TYPE_OTHER:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.other_item_listview, parent, false);
                    other = new OtherHolder(convertView);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_ME:
                    me = (MeHolder) convertView.getTag();
                    break;
                case TYPE_OTHER:
                    other = (OtherHolder) convertView.getTag();
                    break;
            }
        }
        Msg msg = mData.get(position);
        switch (type) {
            case TYPE_ME:
                me.img.setImageResource(msg.getImgRes());
                me.time.setText(msg.getTime());
                me.content.setText(msg.getContent());
                break;
            case TYPE_OTHER:
                other.img.setImageResource(msg.getImgRes());
                other.time.setText(msg.getTime());
                other.content.setText(msg.getContent());
                break;
        }
        return convertView;
    }
    /**
     * 添加数据
     */
    public void addData(Msg msg) {
        mData.add(msg);
        notifyDataSetChanged();
    }
    /**
     * 删除数据
     */
    public void removeData(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }
    /**
     * 自已的视图类
     */
    static class MeHolder {
        private ImageView img;
        private TextView time;
        private TextView content;
        public MeHolder(View view) {
            img = (ImageView) view.findViewById(R.id.meCiv);
            time = (TextView) view.findViewById(R.id.timeTv);
            content = (TextView) view.findViewById(R.id.meTv);
            view.setTag(this);
        }
    }
    /**
     * 其他人的视图类
     */
    static class OtherHolder {
        private ImageView img;
        private TextView time;
        private TextView content;
        public OtherHolder(View view) {
            img = (ImageView) view.findViewById(R.id.otherCiv);
            time = (TextView) view.findViewById(R.id.timeTv);
            content = (TextView) view.findViewById(R.id.otherTv);
            view.setTag(this);
        }
    }
}
