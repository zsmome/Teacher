package com.zsmome.day09_fruit_listview_baseadapter.fruit;

import android.content.Context;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsmome.day09_fruit_listview_baseadapter.R;

import java.util.List;

/**
 * 自定义水果适配器
 * Created by Administrator on 2017/4/8.
 */

public class FruitAdapter extends BaseAdapter {
    //1.上下文
    //2.集合
    private Context mContext;
    private List<Fruit> fruits;

    public FruitAdapter(Context mContext, List<Fruit> fruits) {
        this.mContext = mContext;
        this.fruits = fruits;
    }

    /**
     * 列表的数量
     * @return
     */
    @Override
    public int getCount() {
        return fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 获取一个视图对象
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
        Log.i("test", position+"");
            //动态加载布局
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
            //获得布局子控件
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imageIv);
            viewHolder.title = (TextView) convertView.findViewById(R.id.titleTv);
            viewHolder.desc = (TextView) convertView.findViewById(R.id.descTv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //为子控件填充数据
        Fruit fruit = fruits.get(position);
        viewHolder.image.setImageResource(fruit.getImage());
        viewHolder.title.setText(fruit.getTitle());
        viewHolder.desc.setText(fruit.getContent());
        return convertView;
    }

    /**
     * 视图类
     */
    static class ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView desc;
    }
}
