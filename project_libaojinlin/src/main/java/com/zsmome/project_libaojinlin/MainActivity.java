package com.zsmome.project_libaojinlin;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zsmome.project_libaojinlin.adapters.FragmentViewPagerAdapter;
import com.zsmome.project_libaojinlin.fragments.iconhot.IconhotFragment;
import com.zsmome.project_libaojinlin.fragments.libao.LibaoFragment;
import com.zsmome.project_libaojinlin.fragments.tese.TeseFragment;
import com.zsmome.project_libaojinlin.fragments.youxi.YouxiFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    //声明组件
    private ViewPager mNavVp;
    private BottomNavigationBar mNavBar;
    //侧滑
    private SlidingPaneLayout mSlidingPaneLayout;
    /*侧滑主体内容*/
    private RelativeLayout mContentPane;
    /*ViewPager相关*/
    //适配器
    private FragmentViewPagerAdapter mFragAda;
    //Fragment集合
    List<Fragment> mFragmentList;
    //当前Fragment
    private Fragment mCurrentFrag;
    /*网址*/
    public static InterfaceAddress IAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IAddress = new InterfaceAddress(getResources());
        initViews();
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        /*侧滑*/
        mSlidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane);
        mContentPane = (RelativeLayout) findViewById(R.id.content_pane);
        mSlidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                //缩放的布局
                mContentPane.setScaleX(1f - slideOffset * 0.2f);
                mContentPane.setScaleY(1f - slideOffset * 0.2f);
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });

        mNavVp = (ViewPager) findViewById(R.id.nav_vp);
        mNavBar = (BottomNavigationBar) findViewById(R.id.bottom_nav);
        initViewPager();
        initNavBar();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mFragmentList = new ArrayList<>();
        //添加Fragment页面
        mFragmentList.add(new LibaoFragment());
        mFragmentList.add(new YouxiFragment());
        mFragmentList.add(new IconhotFragment());
        mFragmentList.add(new TeseFragment());
        mFragAda = new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        //设置Fragment适配器
        mNavVp.setAdapter(mFragAda);
        mNavVp.addOnPageChangeListener(this);
    }

    /**
     * 初始化底部导航
     */
    private void initNavBar() {
        mNavBar.setMode(BottomNavigationBar.MODE_FIXED);
        mNavBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mNavBar.setInActiveColor(R.color.colorNavUnSelect)//未选中颜色
                .setBarBackgroundColor(R.color.colorNavSelect)//当前选项颜色
                .setActiveColor(R.color.colorNavBg)//背景色
                .addItem(new BottomNavigationItem(R.mipmap.libao, "礼包"))
                .addItem(new BottomNavigationItem(R.mipmap.youxi, "开服"))
                .addItem(new BottomNavigationItem(R.mipmap.iconhot, "热门"))
                .addItem(new BottomNavigationItem(R.mipmap.tese, "特色"))
                .setFirstSelectedPosition(0)
                .initialise();
        //设置监听
        mNavBar.setTabSelectedListener(this);
    }
    /*导航监听器*/
    @Override
    public void onTabSelected(int position) {
        mNavVp.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {
        Log.i("tab", "未选");
    }

    @Override
    public void onTabReselected(int position) {
    }
    /*ViewPager监听器*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mNavBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
    /**
     * 打开侧滑
     */
    public void openSlidingPane(View view) {
        if(mSlidingPaneLayout.isOpen()) {
            mSlidingPaneLayout.closePane();
        } else {
            mSlidingPaneLayout.openPane();
        }
    }

    /**
     * 搜索页面
     * @param view
     */
    public void search(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
