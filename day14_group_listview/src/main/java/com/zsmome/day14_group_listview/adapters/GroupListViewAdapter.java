package com.zsmome.day14_group_listview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsmome.day14_group_listview.R;
import com.zsmome.day14_group_listview.models.Fruit;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/23.
 */

public class GroupListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> mGroupList;
    private Map<String, List<Fruit>> mChildMap;
    private LayoutInflater mInflater;

    public GroupListViewAdapter(Context mContext, List<String> mGroupList, Map<String, List<Fruit>> mChildMap) {
        this.mContext = mContext;
        this.mGroupList = mGroupList;
        this.mChildMap = mChildMap;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 组的数量
     * @return
     */
    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    /**
     * 每组有多少列表
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildMap.get(mGroupList.get(groupPosition)).size();
    }

    /**
     * 返回组
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildMap.get(groupPosition).get(childPosition);
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
     * 返回父视图
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
            view = mInflater.inflate(R.layout.fruit_group, parent, false);
            group = new GroupHolder(view);
        } else {
            group = (GroupHolder) view.getTag();
        }
        //设置参数
        group.mGroupTv.setText(mGroupList.get(groupPosition));
        return view;
    }

    /**
     * 返回子视图
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
            view = mInflater.inflate(R.layout.fruit_child, parent, false);
            child = new ChildHolder(view);
        } else {
            child = (ChildHolder) view.getTag();
        }
        //设置控件参数
        Fruit fruit = mChildMap.get(mGroupList.get(groupPosition)).get(childPosition);
        child.mChildIv.setImageResource(fruit.getImage());
        child.mChildTv.setText(fruit.getDesc());
        return view;
    }

    /**
     * 返回true,不然不会触发 子Item的点击事件！
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    /**
     * 父ViewHolder
     */
    static class GroupHolder {
        private TextView mGroupTv;
        public GroupHolder(View view) {
            mGroupTv = (TextView) view.findViewById(R.id.fruit_group_tv);
            view.setTag(this);
        }
    }
    /**
     * 子ViewHolder
     */
    static class ChildHolder {
        private ImageView mChildIv;
        private TextView mChildTv;
        public ChildHolder(View view) {
            mChildIv = (ImageView) view.findViewById(R.id.fruit_child_iv);
            mChildTv = (TextView) view.findViewById(R.id.fruit_child_tv);
            view.setTag(this);
        }
    }
}
