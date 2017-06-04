package com.zsmome.day12_no4_fragmentpager;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zsmome.day12_no4_fragmentpager.adapters.RGBFragmentAdapter;
import com.zsmome.day12_no4_fragmentpager.fragments.BlueFragment;
import com.zsmome.day12_no4_fragmentpager.fragments.GreenFragment;
import com.zsmome.day12_no4_fragmentpager.fragments.RedFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    //声明组件
    private ViewPager mRgbVp;
    /*ViewPager相关*/
    //Fragment集合
    private List<Fragment> mRgbVp_FragList;
    //ViewPager适配器
    private RGBFragmentAdapter mRGBFragAda;
    /*导航条*/
    private RadioGroup mNavRg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        mRgbVp = (ViewPager) findViewById(R.id.rgb_vp);
        mNavRg = (RadioGroup) findViewById(R.id.nav_rg);
        mNavRg.setOnCheckedChangeListener(this);
        initViewPager();
    }

    /**
     * 加载ViewPager
     */
    private void initViewPager() {
        mRgbVp_FragList = new ArrayList<>();
        mRgbVp_FragList.add(new RedFragment());
        mRgbVp_FragList.add(new GreenFragment());
        mRgbVp_FragList.add(new BlueFragment());
        //管理器和集合
        mRGBFragAda = new RGBFragmentAdapter(getSupportFragmentManager(), mRgbVp_FragList);
        mRgbVp.setAdapter(mRGBFragAda);
        mRgbVp.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int index = 0;
        switch (checkedId) {
            case R.id.red_rb:
                index = 0;
                break;
            case R.id.green_rb:
                index = 1;
                break;
            case R.id.blue_rb:
                index = 2;
                break;
            default:
                break;
        }
        mRgbVp.setCurrentItem(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton rb = (RadioButton) mNavRg.getChildAt(position);
         rb.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
