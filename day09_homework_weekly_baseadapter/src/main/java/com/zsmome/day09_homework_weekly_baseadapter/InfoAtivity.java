package com.zsmome.day09_homework_weekly_baseadapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zsmome.day09_homework_weekly_baseadapter.util.LoadImageUtil;

public class InfoAtivity extends AppCompatActivity {
    //声明
    private RelativeLayout mTopRl;
    private ImageView mImgIv;
    private TextView mDateTv;
    private TextView mRemainTv;
    private TextView mDescTv;
    private TextView mTypeTv;
    //接收数据
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ativity);
        bundle = this.getIntent().getExtras();
        initViews();
    }

    /**
     * 初始化
     */
    private void initViews() {
        mTopRl = (RelativeLayout) findViewById(R.id.topRl);
        mImgIv = (ImageView) findViewById(R.id.imgIv);
        mDateTv = (TextView) findViewById(R.id.dateTv);
        mRemainTv = (TextView) findViewById(R.id.remainTv);
        mDescTv = (TextView) findViewById(R.id.descTv);
        mTypeTv = (TextView) findViewById(R.id.typeTv);

        Log.i("text", bundle.getString("date"));
        Log.i("text", bundle.getString("remain"));
        Log.i("text", bundle.getString("desc"));
        Log.i("text", bundle.getString("type"));

        mDateTv.setText(bundle.getString("date"));
        mRemainTv.setText(bundle.getString("remain"));
        mDescTv.setText(bundle.getString("desc"));
        mTypeTv.setText(bundle.getString("type"));
        LoadImageUtil liu = new LoadImageUtil();
        Log.i("url", bundle.getString("imgurl"));
        //设置图片
        liu.loadImage(bundle.getString("imgurl"), new LoadImageUtil.OnLoadImageListener() {
            @Override
            public void OnLoadImageComplete(Bitmap bitmap) {
                mImgIv.setImageBitmap(bitmap);
            }
        });
    }
}
