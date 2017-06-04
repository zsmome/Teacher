package com.zsmome.day12_no3_viewpager2listview_switchover;

import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.zsmome.day12_no3_viewpager2listview_switchover.adapters.ListViewHeaderAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.加载ListView
 * 2.给ListView头部视图添加ViewPager实现图片轮播
 * 3.原点指示器实现图片切换
 * 4.图片切换时，原点指示器随着改变
 * 5.实现自动轮播
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    /*常量*/
    //ViewPager中图片的个数
    public static final int VIEWPAGER_IV_COUNT = 3;
    //图片轮播延迟时间
    public static int TIME = 500;
    public static int DELAYED_TIME = 1500;
    public static int ADD_DELAYED = 1000;
    //声明组件
    private ListView mGiftLv;
    //ListView相关数据
    private ArrayAdapter<String> mArrayAda;
    private List<String> mGiftLvDataList;
    /*头部视图相关数据 */
    //头部视图总布局
    private FrameLayout mGiftLvHeaderView;
    //图片
    private ViewPager mGiftLvHeaderVp;
    private ListViewHeaderAdapter mGiftLvHeaderAda;
    private List<View> mGiftLvHeader_IvDataList;
    private TypedArray mImageId_TypeArray;
    private int[] mImageId;
    //原点指示器
    private LinearLayout mGiftLvHeaderLl;
    private List<ImageView> mGiftLvHeader_DotIvDataList;
    private int mCurrentDot_Index = 0 ;
    private ImageView mCurrentDot_Iv;
    /*自动播放*/
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 初始组件
     */
    private void initViews() {
        //初始化图片资源
        mImageId = getImageId();
        mGiftLv = (ListView) findViewById(R.id.gift_lv);
        initGiftLv();
    }

    /**
     * 获得图片资源
     * @return
     */
    private int[] getImageId() {
        mImageId_TypeArray = getResources().obtainTypedArray(R.array.image_id);
        int[] id = new int[mImageId_TypeArray.length()];
        for(int i=0; i<id.length; i++) {
            id[i] = mImageId_TypeArray.getResourceId(i, 0);
        }
        return id;
    }

    /**
     *　初始化列表数据
     */
    private void initGiftLv () {
        initGiftLvHeaderVp();
        mGiftLvDataList = new ArrayList<>();
        for(int i=0; i<20; i++) {
            mGiftLvDataList.add("列表 -- "+i);
        }
        mArrayAda = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                mGiftLvDataList);
        //添加头部视图
        mGiftLv.addHeaderView(mGiftLvHeaderView);
        mGiftLv.setAdapter(mArrayAda);
    }

    /**
     * 初始ListView头部视图
     */
    private void initGiftLvHeaderVp() {
        View view = LayoutInflater.from(this).inflate(R.layout.header_listview_gift, null);
        //注意：添加头部视图，必须是父容器
        mGiftLvHeaderView = (FrameLayout) view;
        initGiftLvHeaderVp_Image();
        initGiftLvHeaderVp_DotImage();
        /*自动播放*/
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mGiftLvHeaderVp.setCurrentItem(mCurrentDot_Index++);
                if(mCurrentDot_Index >= VIEWPAGER_IV_COUNT){
                    mCurrentDot_Index = 0;
                }
                //延迟多少秒
                mHandler.postDelayed(this, DELAYED_TIME);
                DELAYED_TIME  = TIME;
            }
        });
    }
    /**
     * 初始化ListView头部视图中的图片
     */
    private void initGiftLvHeaderVp_Image() {
        //找到头部视图中的ViewPager
        mGiftLvHeaderVp  = (ViewPager) mGiftLvHeaderView.findViewById(R.id.gift_header_vp);
        //初始ViewPager中的图片
        mGiftLvHeader_IvDataList = new ArrayList<>();
        for(int i = 0; i< VIEWPAGER_IV_COUNT; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(mImageId[i%mImageId.length]);
            mGiftLvHeader_IvDataList.add(iv);
        }
        mGiftLvHeaderAda = new ListViewHeaderAdapter(mGiftLvHeader_IvDataList);
        mGiftLvHeaderVp.setAdapter(mGiftLvHeaderAda);
        mGiftLvHeaderVp.addOnPageChangeListener(this);
    }

    /**
     * 需修改
     * 初始化ListView头部视图中的原点
     */
    private void initGiftLvHeaderVp_DotImage() {
        //找到头部视图中的LinearLayout
        mGiftLvHeaderLl = (LinearLayout) mGiftLvHeaderView.findViewById(R.id.gift_header_dotLl);
        mGiftLvHeader_DotIvDataList = new ArrayList<>();
        for(int i=0; i<VIEWPAGER_IV_COUNT; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.dot_header_listview_gift , mGiftLvHeaderLl, false);
            //找到原点图片所在的布局
            final ImageView dot = (ImageView) view.findViewById(R.id.dot_Iv);
            dot.setTag(i);
            dot.setOnClickListener(this);
            //保存原点
            mGiftLvHeader_DotIvDataList.add(dot);
            //注意必须 添加父容器
            mGiftLvHeaderLl.addView(view);
        }
        //默认选中第一个
        mCurrentDot_Iv = mGiftLvHeader_DotIvDataList.get(0);
        mCurrentDot_Iv.setEnabled(false);
    }

    /**
     * 圆点指示器
     * @param v
     */
    @Override
    public void onClick(View v) {
        DELAYED_TIME += ADD_DELAYED;
        //改变圆点指示器状态
        //得到当前的圆点的位置
        int index = Integer.parseInt(v.getTag().toString());
        //切换图片
        mGiftLvHeaderVp.setCurrentItem(index);
        //切换圆点
        mCurrentDot_Iv.setEnabled(true);
        mCurrentDot_Iv = mGiftLvHeader_DotIvDataList.get(index);
        mCurrentDot_Iv.setEnabled(false);
        //保存当前索引
        mCurrentDot_Index = index;
    }

    /**
     * 切换图片监听器
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 改变圆点状态
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //之前选中的圆点
        mCurrentDot_Iv.setEnabled(true);
        //当前选中的圆点
        mCurrentDot_Iv = mGiftLvHeader_DotIvDataList.get(position);
        mCurrentDot_Iv.setEnabled(false);
        //保存索引
        mCurrentDot_Index = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
