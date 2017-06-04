package com.zsmome.project_libaojinlin.adapters.tese;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ImageReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.model.TeseBdxqs;
import com.zsmome.project_libaojinlin.util.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class BdxqsListViewAdapter extends BaseAdapter {
    /*上下文，集合*/
    private Context mContext;
    private List<TeseBdxqs.ListBean> mData;
    //填充布局
    private LayoutInflater mInflater;
    //基本网址
    public static final String URL_BASE = MainActivity.IAddress.getUrl_base();
    public BdxqsListViewAdapter(Context mContext, List<TeseBdxqs.ListBean> mData) {
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
            view = mInflater.inflate(R.layout.tese_bdxqs_lv, parent, false);
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //设置控件参数
        data2View(mData.get(position), holder);
        return view;
    }

    /**
     *  设置控件的内容
     * @param listBean
     * @param holder
     */
    private void data2View(TeseBdxqs.ListBean listBean, final ViewHolder holder) {
        holder.mTitleTv.setText(listBean.getName());
        holder.mDateTv.setText(listBean.getAddtime());
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
    }

    /**
     * 暴打星期三控件
     */
    static class ViewHolder {
         private ImageView mImageIv;
        private TextView mTitleTv;
        private TextView mDateTv;

        public ViewHolder(View view) {
            mImageIv = (ImageView) view.findViewById(R.id.tese_bdxqs_iv);
            mTitleTv = (TextView) view.findViewById(R.id.tese_bdxqs_title_tv);
            mDateTv = (TextView) view.findViewById(R.id.tese_bdxqs_date_tv);
            view.setTag(this);
        }
    }
}
