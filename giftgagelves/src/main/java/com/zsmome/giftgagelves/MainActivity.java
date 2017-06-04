package com.zsmome.giftgagelves;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zsmome.giftgagelves.fragments.HotFragment;
import com.zsmome.giftgagelves.fragments.LibaoFragment;
import com.zsmome.giftgagelves.fragments.TeseFragment;
import com.zsmome.giftgagelves.fragments.YouxiFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //声明控件
    private SlidingPaneLayout mSlidingPaneLayout;
    private RelativeLayout mBodyRl;
    private BottomNavigationBar mNavBar;
    //Fragments
    private List<Fragment> mFragmentList;
    //记录当前的Fragment
    private Fragment mCurrentFragment;
    //网址
    public static InterfaceAddress IAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 找到控件
     */
    private void initViews() {
        IAddress = new InterfaceAddress(getResources());
        mSlidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.slidingpane);
        mBodyRl = (RelativeLayout) findViewById(R.id.body_rl);
        mNavBar = (BottomNavigationBar) findViewById(R.id.bottom_nav);
        //侧滑监听事件
        slidingPane();
        //初始化底部导航条
        initNavBar();
        //初始化Fragment
        initFragments();
    }

    /**
     * 初始化底部导航条
     */
    private void initFragments() {
        mFragmentList = new ArrayList<>();
        //添加到集合
        mFragmentList.add(new LibaoFragment());
        mFragmentList.add(new YouxiFragment());
        mFragmentList.add(new HotFragment());
        mFragmentList.add(new TeseFragment());
        //默认显示礼包页面
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_fl, mFragmentList.get(0))
                .commit();
        mCurrentFragment = mFragmentList.get(0);
    }

    /**
     * 测滑监听事件
     */
    public void slidingPane() {
        mSlidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                //缩放的布局
                mBodyRl.setScaleX(1f - slideOffset * 0.2f);
                mBodyRl.setScaleY(1f - slideOffset * 0.2f);
            }
            @Override
            public void onPanelOpened(View panel) {

            }
            @Override
            public void onPanelClosed(View panel) {

            }
        });
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
        mNavBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                Fragment fragment = mFragmentList.get(position);
                if(fragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction()
//                            .hide(mCurrentFragment)
                            .show(fragment)
                            .commit();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.content_fl, fragment)
                            .show(fragment)
//                            .hide(mCurrentFragment)
                            .commit();
                }
                mCurrentFragment = fragment;
            }
            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
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
}
