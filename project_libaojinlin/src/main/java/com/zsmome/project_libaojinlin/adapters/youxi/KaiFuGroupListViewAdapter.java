package com.zsmome.project_libaojinlin.adapters.youxi;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.model.YouXi;
import com.zsmome.project_libaojinlin.model.YouxiKaifu;
import com.zsmome.project_libaojinlin.util.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/23.
 */

public class KaiFuGroupListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> mGrouopList;
    private Map<String, List<YouxiKaifu>> mChildMap;
    private LayoutInflater mInflater;
    //其础网址
    static final String URL_BASE = MainActivity.IAddress.getUrl_base();
    public KaiFuGroupListViewAdapter(Context mContext, List<String> mGrouopList, Map<String, List<YouxiKaifu>> mChildMap) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mGrouopList = mGrouopList;
        this.mChildMap = mChildMap;
    }
    @Override
    public int getGroupCount() {
        return mGrouopList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String name = mGrouopList.get(groupPosition);
        return mChildMap.get(name).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGrouopList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String name = mGrouopList.get(groupPosition);
        return mChildMap.get(name).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 返回组
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        GroupHolder group = null;
        if(view == null) {
            view = mInflater.inflate(R.layout.youxi_kaifu_elv_group, parent, false);
            group = new GroupHolder(view);
        } else {
            group = (GroupHolder) view.getTag();
        }
        //设置控件参数
        String name = mGrouopList.get(groupPosition);
        group.mTitleTv.setText(""+name);
        return view;
    }

    /**
     * 返回子项
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ChildHolder child = null;
        if(view == null) {
            view = mInflater.inflate(R.layout.youxi_kaifu_elv_child, parent, false);
            child = new ChildHolder(view);
        } else {
            child = (ChildHolder) view.getTag();
        }
        YouxiKaifu data = mChildMap.get(mGrouopList.get(groupPosition)).get(childPosition);
        //将数据设置控件参数
        data2View(data, child);
        return view;
    }

    /**
     * 将数据设置控件参数
     * @param child
     */
    private void data2View(YouxiKaifu data,final ChildHolder child) {
        child.mTitleTv.setText(data.getTitle());
        child.mDateTv.setText(data.getDate());
        child.mServerNoTv.setText(data.getServerNo());
        child.mOperatorTv.setText("运营商:"+data.getOperator());
        //设置图片地址标记，目的：防止图片错位
        child.mIv.setTag(data.getImageUrl());
        //设置默认图片
        child.mIv.setImageResource(R.mipmap.youxi);
        //获取图片
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.loadImage(data.getImageUrl(), new ImageLoader.OnImageLoadListener() {
            @Override
            public void onImageLoadComplete(String url, Bitmap bitmap) {
                String url2 = child.mIv.getTag().toString();
                if(url != null && url2.equals(url)) {
                    child.mIv.setImageBitmap(bitmap);
                }
            }
        });
        child.mCatBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "查看", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * @param groupPosition
     * @param childPosition
     * @return true 可以点击子项
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    /**
     * 父ViewHolder
     */
    static class GroupHolder {
        private TextView mTitleTv;
        public GroupHolder(View view) {
            mTitleTv = (TextView) view.findViewById(R.id.kaifu_elv_group_date_tv);
            view.setTag(this);
        }
    }
    /**
     private String imageUrl;
     private String title;
     private String date;
     private String serverNo;
     private String operator;
     * 子ViewHolder
     */
    static class ChildHolder {
        private ImageView mIv;
        private TextView mTitleTv;
        private TextView mDateTv;
        private TextView mServerNoTv;
        private TextView mOperatorTv;
        private Button mCatBt;
        public ChildHolder(View view) {
            mIv = (ImageView) view.findViewById(R.id.kaifu_elv_image_iv);
            mTitleTv = (TextView) view.findViewById(R.id.kaifu_elv_title_tv);
            mDateTv = (TextView) view.findViewById(R.id.kaifu_elv_date_tv);
            mServerNoTv = (TextView) view.findViewById(R.id.kaifu_elv_server_no_tv);
            mOperatorTv = (TextView) view.findViewById(R.id.kaifu_elv_operator_tv);
            mCatBt = (Button) view.findViewById(R.id.kaifu_elv_cat_bt);
            view.setTag(this);
        }
    }
}
