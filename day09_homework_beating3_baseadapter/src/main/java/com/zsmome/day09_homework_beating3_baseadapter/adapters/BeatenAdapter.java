package com.zsmome.day09_homework_beating3_baseadapter.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsmome.day09_homework_beating3_baseadapter.R;
import com.zsmome.day09_homework_beating3_baseadapter.models.Beaten;
import com.zsmome.day09_homework_beating3_baseadapter.util.LoadImageUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class BeatenAdapter extends BaseAdapter {
    //上下文，集合
    private Context mContext;
    private List<Beaten.ListBean> mData;

    public BeatenAdapter(Context mContext, List<Beaten.ListBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.beating3_item_baseadapter, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        Beaten.ListBean data = mData.get(position);
        holder.name.setText(data.getName());
        LoadImageUtil liu = new LoadImageUtil();
        liu.loadImage("http://www.1688wan.com" + data.getIconurl(), new LoadImageUtil.OnLoadImageListener() {
            @Override
            public void OnLoadImageComplete(Bitmap bitmap) {
                holder.img.setImageBitmap(bitmap);
            }
        });
        return convertView;
    }
    //布局实体类
    static class ViewHolder {
        private ImageView img;
        private TextView name;
        public ViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.imgIv);
            name = (TextView) view.findViewById(R.id.nameTv);
            view.setTag(this);
        }
    }
    //添加数据
    public void  addAll(List<Beaten.ListBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
