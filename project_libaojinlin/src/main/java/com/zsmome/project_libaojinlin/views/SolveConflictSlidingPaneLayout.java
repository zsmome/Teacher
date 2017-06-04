package com.zsmome.project_libaojinlin.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;

import com.zsmome.project_libaojinlin.R;

/**
 * 侧滑菜单
 * 目的：解决ViewPager冲突问题
 * Created by Administrator on 2017/4/23.
 */

public class SolveConflictSlidingPaneLayout extends SlidingPaneLayout {

    public SolveConflictSlidingPaneLayout(Context context) {
        super(context);
    }

    public SolveConflictSlidingPaneLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 确定是否拦截事件
     * @param ev 触摸事件
     * @return true 拦截 false 不拦截
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ViewPager vp = (ViewPager) findViewById(R.id.nav_vp);
        if(vp.getCurrentItem() != 0) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
