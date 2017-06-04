package com.example.day9_homework5.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day9_homework5.R;
import com.example.day9_homework5.model.Libao;
import com.example.day9_homework5.utils.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class LiBaoAdapter extends BaseAdapter{

    private Context mContext;
    private List<Libao.ListBean> mList;
    private LayoutInflater mInflater;

    public LiBaoAdapter() {
    }

    public LiBaoAdapter(Context mContext, List<Libao.ListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
    }

    //元素的个数
    @Override
    public int getCount() {
        return mList.size();
    }

    //获得当前位置的项目
    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    //获得当前位置的项目ID
    @Override
    public long getItemId(int i) {
        return i;
    }

    //获得当前位置的视图
    @Override
    public View getView(int i, View covertView, ViewGroup viewGroup) {
        //使用ConvertView，减少内存消耗
        final ViewHolder viewHolder;
        View view = covertView;
        if(view == null){
            //如果covertView为空，加载视图
            view = mInflater.inflate(R.layout.libao_item,viewGroup,false);
            //保存为视图控件
            viewHolder = new ViewHolder(view);
//            view.setTag(i);
        }else{
            //从视图中取出控件
            viewHolder = (ViewHolder)view.getTag();
        }
        Libao.ListBean list = mList.get(i);
        //设置默认图片
        viewHolder.imageIv.setImageResource(R.mipmap.ic_launcher);
        //保存要显示的图片地址
        viewHolder.imageIv.setTag("http://www.1688wan.com" + list.getIconurl());
        //加载图片时，进行URL判断,如果异步图片加载的地址和当前图片的地址相同，就进行显示
        new ImageLoader().imageLoad("http://www.1688wan.com" + list.getIconurl(),
                new ImageLoader.OnImageLoaderListener() {
                    @Override
                    public void onImageLoader(String url, Bitmap bmp) {
                        String str2 = viewHolder.imageIv.getTag().toString();
                        if(url != null && url.equals(str2)) {
                            viewHolder.imageIv.setImageBitmap(bmp);
                        }
                    }

                });
        viewHolder.appnameTv.setText(list.getGname());
        viewHolder.titleTv.setText(list.getGiftname());
        viewHolder.remainTv.setText(""+list.getNumber());
        viewHolder.timeTv.setText("  时间："+list.getAddtime());
        return view;
    }

    /**
     * 包装控件
     *  初始化布局资源
     */
    class ViewHolder{
        ImageView imageIv;
        TextView appnameTv;
        TextView titleTv;
        TextView remainTv;
        TextView timeTv;
        public ViewHolder(View view){
            //初始化布局资源
            imageIv = (ImageView) view.findViewById(R.id.image_iv);
            appnameTv = (TextView) view.findViewById(R.id.appName_tv);
            titleTv = (TextView) view.findViewById(R.id.title_tv);
            remainTv = (TextView) view.findViewById(R.id.remain_tv);
            timeTv = (TextView) view.findViewById(R.id.time_tv);
            //将对象中的控件保存到视图中
            view.setTag(this);
        }
    }

    /*
    * 添加新的数据，并更新列表
    * */
    public void addAll(List<Libao.ListBean> newData){
        mList.addAll(newData);
        notifyDataSetChanged();
    }
}
