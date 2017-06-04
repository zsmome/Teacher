package com.zsmome.project_libaojinlin.adapters.youxi;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.model.KaiCe;
import com.zsmome.project_libaojinlin.util.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class KaiCeListViewAdapter extends BaseAdapter {
    //上下文，集合
    private Context mContext;
    private List<KaiCe.InfoBean> mData;
    //填充布局
    private LayoutInflater mInflater;
    //基本网址
    String URL_Base = MainActivity.IAddress.getUrl_base();
    public KaiCeListViewAdapter(Context mContext, List<KaiCe.InfoBean> mData) {
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
            view = mInflater.inflate(R.layout.youxi_kaice_lv, parent, false);
            holder = new ViewHolder(view);
        } else {
             holder = (ViewHolder) view.getTag();
        }
        /**
         * 设置控件
         */
        KaiCe.InfoBean data = mData.get(position);
        data2View(data, holder);
        return view;
    }

    /**
     * 将数据设置到控件视图中
     */
    private void data2View(KaiCe.InfoBean data, final ViewHolder holder) {
        holder.titleTv.setText(data.getGname());
        holder.operatorTv.setText(data.getOperators());
        holder.dateTv.setText(data.getAddtime());
        //默认图片
        holder.iconIv.setImageResource(R.mipmap.youxi);
        //设置标记
        String url = URL_Base + data.getIconurl();
        holder.iconIv.setTag(url);
        /*设置网络图片*/
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.loadImage(url, new ImageLoader.OnImageLoadListener() {
            @Override
            public void onImageLoadComplete(String url, Bitmap bitmap) {
                String url2 = holder.iconIv.getTag().toString();
                if(url != null && url2.equals(url)) {
                    holder.iconIv.setImageBitmap(bitmap);
                }
            }
        });
        /**
         * 查看按钮监听
         */
        holder.mCatBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "查看", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 子控件
     * 目的：减少findViewById次数
     */
    static class ViewHolder {
        private ImageView iconIv;
        private TextView titleTv;
        private TextView operatorTv;
        private TextView dateTv;
        private Button mCatBt;
        public ViewHolder(View view) {
            iconIv = (ImageView) view.findViewById(R.id.kaice_lv_icon_iv);
            titleTv = (TextView) view.findViewById(R.id.kaice_lv_title_tv);
            operatorTv = (TextView) view.findViewById(R.id.kaice_lv_operator_tv);
            dateTv = (TextView) view.findViewById(R.id.kaice_lv_date_tv);
            mCatBt = (Button) view.findViewById(R.id.kaice_lv_cat_bt);
            view.setTag(this);
        }
    }
}
