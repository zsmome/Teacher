package com.zsmome.project_libaojinlin.adapters.iconhot;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
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

public class IconhotListViewAdapter extends BaseAdapter {
    /*上下文，集合*/
    private Context mContext;
    private List<IconhotBoutique.InfoBean.Push1Bean> mData;
    //填充视图
    private LayoutInflater mInflater;
    //基本网址
    public static final String URL_BASE = MainActivity.IAddress.getUrl_base();
    public IconhotListViewAdapter(Context mContext, List<IconhotBoutique.InfoBean.Push1Bean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return  mData.size();
    }

    @Override
    public Object getItem(int position) {
        return  mData.get(position);
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
            view = mInflater.inflate(R.layout.iconhot_boutique_lv, parent, false);
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //为组件设置参数
        data2View(mData.get(position), holder);
        return view;
    }

    /**
     * 将数据设置到控件上
     * @param push1Bean
     * @param holder
     */
    private void data2View(IconhotBoutique.InfoBean.Push1Bean push1Bean, final ViewHolder holder) {
        holder.mTitleTv.setText(push1Bean.getName());
        holder.mTypeTv.setText("类型:"+push1Bean.getTypename());
        holder.mSizeTv.setText("大小:"+push1Bean.getSize());
        holder.mPlayerNumberTv.setText(push1Bean.getClicks()+"");
        String url = URL_BASE + push1Bean.getLogo();
        //设置标记
        holder.mIconIv.setTag(url);
        //设置默认图片
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
     * 精品推荐视图
     * 目的：减少findViewById次数
     */
    static class ViewHolder {
        private ImageView mIconIv;
        private TextView mTitleTv;
        private TextView mTypeTv;
        private TextView mPlayerNumberTv;
        private TextView mSizeTv;
        public ViewHolder(View view) {
            mIconIv = (ImageView) view.findViewById(R.id.iconhot_lv_icon_iv);
            mTitleTv = (TextView) view.findViewById(R.id.iconhot_lv_title_tv);
            mTypeTv = (TextView) view.findViewById(R.id.iconhot_lv_type_tv);
            mPlayerNumberTv = (TextView) view.findViewById(R.id.iconhot_lv_player_number_tv);
            mSizeTv = (TextView) view.findViewById(R.id.iconhot_lv_size_tv);
            view.setTag(this);
        }
    }
}
