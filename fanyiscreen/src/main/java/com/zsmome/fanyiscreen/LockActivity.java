package com.zsmome.fanyiscreen;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.zsmome.fanyiscreen.adapters.CommonAdapter;
import com.zsmome.fanyiscreen.adapters.VPagerAdapter;
import com.zsmome.fanyiscreen.adapters.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by GUOQI on 16/10/23.
 */

public class LockActivity extends AppCompatActivity {
    /*====声明控件====*/
    private ViewPager mBackgroundVp;
    private GridView mWordGv;
    private TextView mHintTv;
    private TextView mInputTv;
    /*====自动播放头部图片====*/
    private Timer mTimer;
    //当前图片索引号
    public static int mCurrentDotIv_Index;
    //需要自己定义标志
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //显示在锁屏的上层界面
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_lock);
        super.onCreate(savedInstanceState);
        initViews();
    }

    /**
     * 找到组件
     */
    private void initViews() {
        mHintTv = (TextView) findViewById(R.id.hint_tv);
        mInputTv = (TextView) findViewById(R.id.input_tv);
        mBackgroundVp = (ViewPager) findViewById(R.id.background_vp);
        mWordGv = (GridView) findViewById(R.id.word_button_gv);
        initHintTv();
        initViewPager();
        initGridView();

    }

    /**
     * 提示文字
     */
    private void initHintTv() {
        mHintTv.setText("zsmome");
    }

    /**
     * 初始化字母按钮表
     */
    private void initGridView() {
        String hint = mHintTv.getText().toString();
        List<String> mData = new ArrayList<>();
        for(int i=0; i<hint.length(); i++) {
            String word = hint.charAt(i) + "";
            if(mData.contains(word)) {
                continue;
            }
            mData.add(word);
        }
        Collections.sort(mData);
        CommonAdapter<String> commAda = new CommonAdapter<String>(this, mData, R.layout.button_word) {
            @Override
            public void setViews(ViewHolder viewHolder, final String data) {
                /*设置文字*/
                viewHolder.setButtonText(R.id.bt, data);
                /*设置监听*/
                viewHolder.getView(R.id.bt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = mInputTv.getText().toString();
                        mInputTv.setText(str+data);
                    }
                });
            }
        };
        mWordGv.setAdapter(commAda);
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        //图片集合
        List<View> backgroundImageList = getImageArr();
        VPagerAdapter mVpAda = new VPagerAdapter(backgroundImageList);
        mBackgroundVp.setAdapter(mVpAda);
    }

    /**
     * 获取本地图片
     * @return
     */
    private List<View> getImageArr() {
        //获取本地背景图片
        TypedArray backgroundImageArr = getResources().obtainTypedArray(R.array.arrays_background_image);
        int len = backgroundImageArr.length();
        //图片资源数组
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++)
            resIds[i] = backgroundImageArr.getResourceId(i, 0);
        //图片集合
        List<View> backgroundImageList = new ArrayList<>();
        for(int i=0; i<resIds.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(resIds[i]);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            backgroundImageList.add(iv);
        }
        //自动播放图片
        autoBrowseImage(len);
        return backgroundImageList;
    }
    /**
     * 自动播放图片
     */
    private void autoBrowseImage(final int len) {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCurrentDotIv_Index = mCurrentDotIv_Index % len;
                        mBackgroundVp.setCurrentItem(mCurrentDotIv_Index);
                        mCurrentDotIv_Index ++;
                    }
                });
            }
        }, 1000, 3000);
    }
    //使menu,back键，音量加减键失效
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return disableKeycode(keyCode, event);
    }
    private boolean disableKeycode(int keyCode, KeyEvent event) {
        int key = event.getKeyCode();
        switch (key) {
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_HOME:
            case KeyEvent.KEYCODE_MENU:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onUserLeaveHint() {
        exitAction();
        super.onUserLeaveHint();
    }
    private void exitAction() {
        try {
//            Intent intent = new Intent(this, LockActivity.class);
//            startActivity(intent);
            Toast.makeText(this, "嘿，离四级又远了一步！", Toast.LENGTH_LONG).show();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除按钮
     * @param view
     */
    public void delButton(View view) {
        String str = mInputTv.getText().toString();
        mInputTv.setText(str.substring(0, str.length()-1));
    }

    /**
     * 确认按钮
     * @param view
     */
    public void okButton(View view) {
        String hint = mHintTv.getText().toString();
        String input = mInputTv.getText().toString();
        if(hint.equals(input)) {
            finish();
        } else {
            mInputTv.setText("");
            Toast.makeText(this, "再接再励哟~", Toast.LENGTH_SHORT).show();
        }
    }
}
