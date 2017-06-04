package com.zsmome.day09_homework_news_baseadapter.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsmome.day09_homework_news_baseadapter.R;
import com.zsmome.day09_homework_news_baseadapter.util.LoadImageUtil;

import java.util.List;

/**
 * 新闻适配器
 * Created by Administrator on 2017/4/8.
 */

public class NewsBaseAdapter extends BaseAdapter {
    //上下文，集合
    private Context mContext;
    private List<News> mNewsList;
    ViewHolder holder = null;
    static int count = 0;
    public NewsBaseAdapter(Context mContext, List<News> mNewsList) {
        this.mContext = mContext;
        this.mNewsList = mNewsList;
    }

    @Override
    public int getCount() {
        return mNewsList.size();
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
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.news_item_baseadapter, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.imageIv);
            holder.title = (TextView) convertView.findViewById(R.id.titleTv);
            holder.content = (TextView) convertView.findViewById(R.id.contentTv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //加载网络图片
        LoadImageUtil liu = new LoadImageUtil();
        liu.loadImage(mNewsList.get(position).getImageUrl(), new LoadImageUtil.OnLoadImageListener() {
            @Override
            public void OnLoadImageComplete(Bitmap bitmap) {
                holder.image.setImageBitmap(bitmap);
            }
        });
        holder.title.setText(mNewsList.get(position).getTitle());
        holder.content.setText(mNewsList.get(position).getContent());
        return convertView;
    }
    /**
     * 视图类
     */
    static class ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView content;
    }
}
