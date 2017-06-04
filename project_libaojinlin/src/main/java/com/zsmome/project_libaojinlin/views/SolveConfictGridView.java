package com.zsmome.project_libaojinlin.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/4/24.
 */

public class SolveConfictGridView extends GridView {
    public SolveConfictGridView(Context context) {
        super(context);
    }

    public SolveConfictGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 目的：解决ScorllView + ListView只显示一行数据问题
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
