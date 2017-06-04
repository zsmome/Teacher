package com.zsmome.project_libaojinlin.adapters.tese;

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
import com.zsmome.project_libaojinlin.model.TeseXyzc;
import com.zsmome.project_libaojinlin.util.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class XyzcListViewAdapter extends BaseAdapter {
    //上下文，集合
    private Context mContext;
    private List<TeseXyzc.ListBean> mData;
    private LayoutInflater mInflater;
    //其本网址
    public static final String URL_BASE = MainActivity.IAddress.getUrl_base();
    public XyzcListViewAdapter(Context mContext, List<TeseXyzc.ListBean> mData) {
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
            view = mInflater.inflate(R.layout.tese_xyzc_lv, parent, false);
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //设置控件内容
        data2View(mData.get(position), holder);
        return view;
    }

    /**
     * 设置控件内容
     * @param listBean
     * @param holder
     */
    private void data2View(TeseXyzc.ListBean listBean, final ViewHolder holder) {
        holder.mTitleTv.setText(listBean.getName());
//        //设置大图片
        String url = URL_BASE + listBean.getIconurl();
        //设置标记
        holder.mImageIv.setTag(url);
        //设置默认图片
        holder.mImageIv.setImageResource(R.mipmap.tese);
        //设置网络图片
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.loadImage(url, new ImageLoader.OnImageLoadListener() {
            @Override
            public void onImageLoadComplete(String url, Bitmap bitmap) {
                String url2 = holder.mImageIv.getTag().toString();
                if(url !=null && url.equals(url2)) {
                    holder.mImageIv.setImageBitmap(bitmap);
                }
            }
        });
        //设置小图标
        String iconImgUrl2 = URL_BASE + listBean.getIconurl();
        holder.mIconIv.setTag(iconImgUrl2);
        holder.mIconIv.setImageResource(R.mipmap.tese);
        ImageLoader imageLoader2 = new ImageLoader();
        imageLoader2.loadImage(iconImgUrl2, new ImageLoader.OnImageLoadListener() {
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
     * 新游周刊控件
     */
    static class ViewHolder {
        private ImageView mImageIv;
        private TextView mTitleTv;
        private ImageView mIconIv;
        public ViewHolder(View view) {
            mImageIv = (ImageView) view.findViewById(R.id.tese_xyzc_iv);
            mTitleTv = (TextView) view.findViewById(R.id.tese_xyzc_title_tv);
            mIconIv = (ImageView) view.findViewById(R.id.tese_xyzc_icon_iv);
            view.setTag(this);
        }
    }
}
