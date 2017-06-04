package com.zsmome.project_libaojinlin.adapters.tese;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.model.TeseXyzcInfo;
import com.zsmome.project_libaojinlin.util.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

public class XyzcInfoListViewAdapter extends BaseAdapter {
    //上下文，集合
    private Context mContext;
    private List<TeseXyzcInfo.ListBean> mData;
    //填充视图
    private LayoutInflater mInflater;
    //基础网址
    public static final String URL_BASE = MainActivity.IAddress.getUrl_base();
    public XyzcInfoListViewAdapter(Context mContext, List<TeseXyzcInfo.ListBean> mData) {
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
            view = mInflater.inflate(R.layout.tese_xyzc_info_lv, parent, false);
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //设置列表控件内容
        data2View(mData.get(position), holder);
        return view;
    }

    private void data2View(TeseXyzcInfo.ListBean listBean, final ViewHolder holder) {
        holder.mNameTv.setText(listBean.getAppname());
        holder.mTypeTv.setText(listBean.getTypename());
        holder.mSizeTv.setText(listBean.getAppsize());
        holder.mDescTv.setText(listBean.getDescs());
        //设置标记，防止图片错位
        String url = URL_BASE + listBean.getIconurl();
        holder.mIconIv.setTag(url);
        holder.mIconIv.setImageResource(R.mipmap.tese);
        //设置网络图片
        new ImageLoader().loadImage(url, new ImageLoader.OnImageLoadListener() {
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
     * 新游周刊详情列表控件视图
     */
    static class ViewHolder {
        private ImageView mIconIv;
        private TextView mNameTv;
        private TextView mTypeTv;
        private TextView mSizeTv;
        private TextView mDescTv;
        private Button mDownBt;
        public ViewHolder(View view) {
            mIconIv = (ImageView) view.findViewById(R.id.xyzc_info_lv_icon_iv);
            mNameTv = (TextView) view.findViewById(R.id.xyzc_info_lv_title_tv);
            mTypeTv = (TextView) view.findViewById(R.id.xyzc_info_lv_type_tv);
            mSizeTv = (TextView) view.findViewById(R.id.xyzc_info_lv_size_tv);
            mDescTv = (TextView) view.findViewById(R.id.xyzc_info_lv_desc_tv);
            mDownBt = (Button) view.findViewById(R.id.xyzc_info_lv_down_bt);
            view.setTag(this);
        }
    }
}
