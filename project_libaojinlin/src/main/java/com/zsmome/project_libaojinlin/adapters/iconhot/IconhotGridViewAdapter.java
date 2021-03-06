package com.zsmome.project_libaojinlin.adapters.iconhot;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.model.IconhotBoutique;
import com.zsmome.project_libaojinlin.util.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class IconhotGridViewAdapter extends BaseAdapter {
    /*上下文，集合*/
    private Context mContext;
    private List<IconhotBoutique.InfoBean.Push2Bean> mData;
    //填充视图
    private LayoutInflater mInflater;
    //基本网址
    public static final String URL_BASE = MainActivity.IAddress.getUrl_base();
    public IconhotGridViewAdapter(Context mContext, List<IconhotBoutique.InfoBean.Push2Bean> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
        View view = convertView;
        ViewHolder holder = null;
        if(view == null) {
            view = mInflater.inflate(R.layout.iconhot_hot_gv, parent, false);
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //设置控件内容
        data2View(mData.get(position), holder);
        return view;
    }

    private void data2View(IconhotBoutique.InfoBean.Push2Bean push2Bean, final ViewHolder holder) {
        holder.mDescTv.setText(push2Bean.getName());
        String url = URL_BASE + push2Bean.getLogo();
        holder.mIconIv.setTag(url);
        holder.mIconIv.setImageResource(R.mipmap.iconhot);
        //设置网络图片
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.loadImage(url, new ImageLoader.OnImageLoadListener() {
            @Override
            public void onImageLoadComplete(String url, Bitmap bitmap) {
                String url2 = holder.mIconIv.getTag().toString();
                if(url != null && url2.equals(url)) {
                    holder.mIconIv.setImageBitmap(bitmap);
                }
            }
        });
    }

    /**
     * 设置控件内容
     */

    /**
     * 热门推荐控件
     */
    static class ViewHolder {
        private ImageView mIconIv;
        private TextView mDescTv;
        public ViewHolder(View view) {
            mIconIv = (ImageView) view.findViewById(R.id.iconhot_gv_iv);
            mDescTv = (TextView) view.findViewById(R.id.iconhot_gv_title_tv);
            view.setTag(this);
        }
    }
}
