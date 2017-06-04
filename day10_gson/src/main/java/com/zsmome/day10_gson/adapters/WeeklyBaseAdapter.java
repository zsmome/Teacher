package com.zsmome.day10_gson.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsmome.day10_gson.R;
import com.zsmome.day10_gson.model.WeeklyList;
import com.zsmome.day10_gson.util.LoadImageUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */

public class WeeklyBaseAdapter extends BaseAdapter {
    private Context mContext;
    private List<WeeklyList.ListBean> mData;

    public WeeklyBaseAdapter(Context mContext, List<WeeklyList.ListBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    //必须返回
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_simple, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        WeeklyList.ListBean data = mData.get(position);
        //加载图片
        LoadImageUtil liu = new LoadImageUtil();
        liu.loadImage(data.getNewsicon(), new LoadImageUtil.OnLoadImageListener() {
            @Override
            public void OnLoadImageComplete(Bitmap bitmap) {
                holder.image.setImageBitmap(bitmap);
            }
        });
        holder.title.setText(data.getAuthor());
        holder.content.setText(data.getDescs());
        return convertView;
    }
    public void addAll(List<WeeklyList.ListBean> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }
    static class ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView content;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.imageIv);
            title = (TextView) view.findViewById(R.id.titleTv);
            content = (TextView) view.findViewById(R.id.contentTv);
            view.setTag(this);
        }
    }
}
