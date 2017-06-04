package com.zsmome.day11_fragment_nav;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zsmome.day11_fragment_nav.fragments.BlueFragment;
import com.zsmome.day11_fragment_nav.fragments.GreenFragment;
import com.zsmome.day11_fragment_nav.fragments.RedFragment;

/**
 * 1.底部导航条（单选按钮）
 * 2.多个Fragment
 * 3.点击按钮切换Fragment（优化：隐藏，显示）
 */
public class MainActivity extends AppCompatActivity {
    //声明组件
    private FrameLayout mRgbFl;
    private RadioGroup mRgbRg;
    private RadioButton mRedRb;
    private RadioButton mGreenRb;
    private RadioButton mBlueRb;
    //Fragment
    private RedFragment mRedFrag;
    private GreenFragment mGreenFrag;
    private BlueFragment mBlueFrag;
    //保存当前的Fragment
    private Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragments();
    }

    /**
     * 初始化Fragments
     */
    private void initFragments() {
        mRedFrag = new RedFragment();
        mGreenFrag = new GreenFragment();
        mBlueFrag = new BlueFragment();
        //默认显示红色
        //添加回退栈
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rgb_fl, mRedFrag)
                .addToBackStack(null)
                .commit();
        currentFragment = mRedFrag;
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        mRgbFl = (FrameLayout) findViewById(R.id.rgb_fl);

        mRgbRg = (RadioGroup) findViewById(R.id.rgb_rg);
        mRedRb = (RadioButton) findViewById(R.id.red_rb);
        mGreenRb = (RadioButton) findViewById(R.id.green_rb);
        mBlueRb = (RadioButton) findViewById(R.id.blue_rb);

        //选中红色
        mRedRb.setChecked(true);
        mRgbRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.red_rb:
                        switchoverFragment(mRedFrag);
                        break;
                    case R.id.green_rb:
                        switchoverFragment(mGreenFrag);
                        break;
                    case R.id.blue_rb:
                        switchoverFragment(mBlueFrag);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    /**
     * 切换Fragment
     */
    public void switchoverFragment(Fragment fragment) {
        if(fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(currentFragment)
                    .show(fragment)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rgb_fl, fragment)
                    .show(fragment)
                    .commit();
        }
        currentFragment = fragment;
    }
}
