package com.zsmome.project_libaojinlin.fragments.libao;


import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zsmome.project_libaojinlin.InfoAtivity;
import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.libao.LibaoLvAdapter;
import com.zsmome.project_libaojinlin.adapters.libao.VPagerAdapter;
import com.zsmome.project_libaojinlin.fragments.BaseFragment;
import com.zsmome.project_libaojinlin.model.Weekly;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;
import com.zsmome.project_libaojinlin.util.LoadImageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 礼包页面
 * 上下文通过getActivity()获得
 * A simple {@link Fragment} subclass.
 */
public class LibaoFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private View view;
    /*====声明组件====*/
    private PullToRefreshListView mLibaoLv;
    private ViewPager mLibaoLv_HeaderVp;
    private View mLibaoLvHeader;
    /*====适配器====*/
    private LibaoLvAdapter mLibaoLvAda;
    //ListView头部适配器
    private VPagerAdapter mLibaoLvHeaderAda;
    //数据
    private static Weekly weekly;
    private List<Weekly.ListBean> mListBean;
    private List<Weekly.AdBean> mAdBean;
    /*====头部====*/
    //图片
    private List<View> mLibaoLvHeader_IvList;
    //圆点指示器
    private LinearLayout mLibaoLvHeader_DotLl;
    private List<ImageView> mLibaoLvHeader_DotLl_List;
    //保存当前圆点
    private ImageView mCurrentDotIv;
    //页数
    private static int pageno = 1;
    /*====自动播放头部图片====*/
    private Timer mTimer;
    //当前图片位置
    private static int mCurrentDotIv_Index;
    /*====网址====*/
    public LibaoFragment() {
    }

    /**
     * 初始大视图
     * @param inflater
     * @param container
     * @return
     */
    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_libao, container, false);
        return view;
    }

    /**
     * 不可见时
     */
    @Override
    public void onInVisible() {

    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        mLibaoLv = (PullToRefreshListView) view.findViewById(R.id.libao_lv);
        initLibaoLv();
    }

    /**
     * 懒加载避免了线程会加载多次
     * 销毁线自动播放线程
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("tab", "autoDestroyView");
        /*停止滚动图片*/
        //stopAutoBrowseImage();
    }
    /**
     * 初始化礼包
     */
    private void initLibaoLv() {
        //ListView头部布局
        mLibaoLvHeader = LayoutInflater.from(getActivity()).inflate(R.layout.libao_listview_header, null);
        mLibaoLv_HeaderVp = (ViewPager) mLibaoLvHeader.findViewById(R.id.libao_lv_header);
        //解析json
        JSONParseUtil jpu = new JSONParseUtil();
        jpu.parseJSON(MainActivity.IAddress.getUrl_liBaoLv()+pageno, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Gson gson = new Gson();
                weekly = gson.fromJson(json, Weekly.class);
                initLibaoLvMiddle();
            }
        });
    }

    /**
     * 加载数据
     */
    private void loadListView() {
        //解析json
        JSONParseUtil jpu = new JSONParseUtil();
        jpu.parseJSON(MainActivity.IAddress.getUrl_liBaoLv()+pageno, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Gson gson = new Gson();
                weekly = gson.fromJson(json, Weekly.class);
                //添加集合
                mLibaoLvAda.addAll(weekly.getList());
            }
        });
    }

    /**
     * 添加ListView头部
     */
    private void initLibaoLvHeader() {
        initLibaoLvHeader_Image();
        initLibaoLvHeader_DotIv();
    }

    /**
     * 初始头部图片
     */
    private void initLibaoLvHeader_Image() {
        mLibaoLvHeader_IvList = new ArrayList<>();
        List<String> mTitle = new ArrayList<>();
        mAdBean = weekly.getAd();
        for(Weekly.AdBean adBean : mAdBean) {
            /*必须在外面定义*/
            final ImageView iv = new ImageView(getActivity());
            iv.setImageResource(R.mipmap.libao);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            LoadImageUtil liu = new LoadImageUtil();
            liu.loadImage(MainActivity.IAddress.getUrl_base() + adBean.getIconurl(), new LoadImageUtil.OnLoadImageListener() {
                @Override
                public void OnLoadImageComplete(Bitmap bitmap) {
                    iv.setImageBitmap(bitmap);
                }
            });
            mLibaoLvHeader_IvList.add(iv);
        }
        //创建适配器
        mLibaoLvHeaderAda = new VPagerAdapter(mLibaoLvHeader_IvList);
        //设置头部适配器
        mLibaoLv_HeaderVp.setAdapter(mLibaoLvHeaderAda);
        /*设置监听器*/
        mLibaoLv_HeaderVp.addOnPageChangeListener(this);
    }

    /**
     * 初始头部圆点指示器
     */
    private void initLibaoLvHeader_DotIv() {
        mLibaoLvHeader_DotLl = (LinearLayout) mLibaoLvHeader.findViewById(R.id.libao_lv_header_dot);
        mLibaoLvHeader_DotLl_List = new ArrayList<>();
        for(int i=0; i<weekly.getAd().size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.libao_listview_header_dot, null);
            final ImageView dot = (ImageView) view.findViewById(R.id.header_dot);
            dot.setTag(i);
            dot.setOnClickListener(this);
            mLibaoLvHeader_DotLl_List.add(dot);
            mLibaoLvHeader_DotLl.addView(view);
        }
        //默认选中第一个值
        mCurrentDotIv_Index = 0;
        mCurrentDotIv = mLibaoLvHeader_DotLl_List.get(mCurrentDotIv_Index);
        mCurrentDotIv.setEnabled(false);
    }
    /**
     * 添加ListView中间列表
     */
    private void initLibaoLvMiddle() {
        mListBean = new ArrayList<>();
        //创建Adapter
        mLibaoLvAda = new LibaoLvAdapter(getActivity(), mListBean);
        mLibaoLvAda.addAll(weekly.getList());
        //加载头部
        initLibaoLvHeader();
        //添加到礼包列表的头部
        //注意：必须添加父容器
        mLibaoLv.getRefreshableView().addHeaderView(mLibaoLvHeader);
        //设置Adapter
        mLibaoLv.setAdapter(mLibaoLvAda);
        //给按钮设置监听
        //自动播放
        autoBrowseImage();
        mLibaoLv.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新监听
        mLibaoLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            //下拉加载第一页
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageno = 1;
//                mLibaoLvAda.refresh();
                //停止刷新
//                mLibaoLv.onRefreshComplete();
                //解析json
                JSONParseUtil jpu = new JSONParseUtil();
                jpu.parseJSON(MainActivity.IAddress.getUrl_liBaoLv()+pageno, new JSONParseUtil.OnJSONParseListener() {
                    @Override
                    public void OnJSONParseComplete(String json) {
                        Gson gson = new Gson();
                        weekly = gson.fromJson(json, Weekly.class);
                        mListBean = new ArrayList<Weekly.ListBean>();
                        mLibaoLvAda.refresh(weekly.getList());
                        mLibaoLv.onRefreshComplete();
                    }
                });
            }
            //上拉加载列表
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (pageno < weekly.getPageno() - 1) {
                    pageno++;
                    loadListView();
                    //停止刷新
                    mLibaoLv.onRefreshComplete();
                }
            }
        });
        //设置列表监听
        mLibaoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String info_id = mListBean.get(position-2).getId();
                gotoInfo(info_id);
            }
        });
    }

    /**
     * 自动播放图片
     */
    private void autoBrowseImage() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCurrentDotIv_Index = mCurrentDotIv_Index % weekly.getAd().size();
                        mLibaoLv_HeaderVp.setCurrentItem(mCurrentDotIv_Index);
                        mCurrentDotIv_Index ++;
                    }
                });
            }
        }, 1000, 1000);
    }
    /**
     * 停止滚动图片
     */
    private void stopAutoBrowseImage() {
         if(mTimer != null) {
             mTimer.cancel();
             mTimer = null;
         }
    }
    /**
     * 跳转详情页
     */
    public void gotoInfo(String id) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), InfoAtivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    /**
     * ListView头部圆点监听器
     * @param v
     */
    @Override
    public void onClick(View v) {
        mCurrentDotIv.setEnabled(true);
        //当前位置索引号
        int index = Integer.parseInt(v.getTag().toString());
        mCurrentDotIv = mLibaoLvHeader_DotLl_List.get(index);
        mCurrentDotIv.setEnabled(false);
        mLibaoLv_HeaderVp.setCurrentItem(index);
        mCurrentDotIv_Index = index;
    }
    /*ListView头部图片监听器*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    //改变圆点状态
    @Override
    public void onPageSelected(int position) {
        mCurrentDotIv.setEnabled(true);
        mCurrentDotIv = mLibaoLvHeader_DotLl_List.get(position);
        mCurrentDotIv.setEnabled(false);
        mCurrentDotIv_Index = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
