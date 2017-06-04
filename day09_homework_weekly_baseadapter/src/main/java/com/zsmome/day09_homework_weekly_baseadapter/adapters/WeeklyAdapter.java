package com.zsmome.day09_homework_weekly_baseadapter.adapters;

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

import com.zsmome.day09_homework_weekly_baseadapter.R;
import com.zsmome.day09_homework_weekly_baseadapter.model.Weekly;
import com.zsmome.day09_homework_weekly_baseadapter.util.ImageLoader;
import com.zsmome.day09_homework_weekly_baseadapter.util.LoadImageUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class WeeklyAdapter extends BaseAdapter {
    //上下文，集合
    private Context mContext;
    private List<Weekly.ListBean> mData;

    public WeeklyAdapter(Context mContext, List<Weekly.ListBean> mData) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.weekly_item_adapter, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Weekly.ListBean listBean = mData.get(position);

        holder.title.setText( listBean.getGname());
        holder.content.setText(listBean.getGiftname());
        holder.remain.setText("剩余："+listBean.getNumber()+"");
        holder.time.setText("时间："+listBean.getAddtime());
        //设置当前图片地址
        holder.img.setTag("http://www.1688wan.com" + listBean.getIconurl());
        ImageLoader il = new ImageLoader();
        il.loadImage("http://www.1688wan.com" + listBean.getIconurl(), new ImageLoader.OnImageLoadListener() {
            @Override
            public void onImageLoadComplete(String url, Bitmap bitmap) {
                String url2 = holder. img.getTag().toString();
                if(url != null && url.equals(url2)) {
                    holder.img.setImageBitmap(bitmap);
                }
            }
        });
        holder.getBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "领取成功!", Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }
    static class ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView content;
        private TextView remain;
        private TextView time;
        private Button getBt;
        public ViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.imgIv);
            title = (TextView) view.findViewById(R.id.titleTv);
            content = (TextView) view.findViewById(R.id.contentTv);
            remain = (TextView) view.findViewById(R.id.remainTv);
            time = (TextView) view.findViewById(R.id.timeTv);
            getBt = (Button) view.findViewById(R.id.getBt);
            view.setTag(this);
        }
    }
    public void addAll(List<Weekly.ListBean> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }
}
