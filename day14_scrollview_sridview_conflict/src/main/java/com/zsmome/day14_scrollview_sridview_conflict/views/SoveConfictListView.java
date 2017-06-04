package com.zsmome.day14_scrollview_sridview_conflict.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 为什么：将只显示一行数据
 * 解决方法:
 * 1.自定义控件继承GridView或ListView
 * 2.重写onMeasure方法
 * Created by Administrator on 2017/4/23.
 */

public class SoveConfictListView extends ListView {
    public SoveConfictListView(Context context) {
        super(context);
    }

    public SoveConfictListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套ListView也是同样的道理，不再赘述。
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
