package com.zsmome.day17_student_table.adapters;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通用的ViewHolder
 * Created by xray on 17/4/22.
 */

public class ViewHolder {

    //保存所有视图的集合
    private SparseArray<View> mViews;
    //转换视图
    private View mConvertView;
    private Context mContext;

    private ViewHolder(Context context, ViewGroup parent, int layoutId){
        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
        mContext = context;
    }

    /**
     * 用于获得ViewHolder的实例
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */
    public static ViewHolder get(Context context, View convertView,ViewGroup parent,int layoutId){
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder(context,parent,layoutId);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        return holder;
    }

    /**
     * 通过id获得视图对象
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        //判断视图是否保存过
        if(view == null){
            //从ConvertView中查找视图，并添加到集合中
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    /**
     * 返回ConvertView
     * @return
     */
    public View getConvertView(){
        return mConvertView;
    }

    /**
     * 设置文字
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setTextViewText(int viewId,String text){
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 设置文字+颜色
     * @param viewId
     * @param text
     * @param start
     * @param end
     * @param color
     * @return
     */
    public ViewHolder setTextViewTextWithColor(int viewId,String text,int start,int end,int color){
        TextView textView = getView(viewId);
        SpannableString ss = new SpannableString(text);
        ss.setSpan(new ForegroundColorSpan(color),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        return this;
    }

    /**
     * 设置本地图片
     * @param viewId
     * @param resId
     * @return
     */
    public ViewHolder setImageViewResource(int viewId,int resId){
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }
}
