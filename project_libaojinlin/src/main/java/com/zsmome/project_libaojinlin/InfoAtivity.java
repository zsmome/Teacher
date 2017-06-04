package com.zsmome.project_libaojinlin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zsmome.project_libaojinlin.model.Info;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;
import com.zsmome.project_libaojinlin.util.LoadImageUtil;


public class InfoAtivity extends AppCompatActivity {
    //声明
    private RelativeLayout mTopRl;
    private ImageView mImgIv;
    private TextView mDateTv;
    private TextView mRemainTv;
    private TextView mDescTv;
    private TextView mTypeTv;
    //接收数据
    private Intent intent;
    //网址
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_weekly_activity);
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
        getInfo();
    }

    /**
     * 获取信息
     */
    private void getInfo() {
        intent = getIntent();
        String id = intent.getStringExtra("id");
        //解析祥情页面Json
        JSONParseUtil jpu = new JSONParseUtil();
        jpu.parseJSON(MainActivity.IAddress.getUrl_libao_info() + id, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Gson gson = new Gson();
                Info info = gson.fromJson(json, Info.class);
                //设置控件信息
                setViews(info);
            }
        });
    }

    private void setViews(Info info) {
        Info.InfoBean infoBean = info.getInfo();
        mDateTv.setText(infoBean.getOvertime());
        mRemainTv.setText(infoBean.getNumber()+"");
        mDescTv.setText(infoBean.getDescs());
        mTypeTv.setText(infoBean.getTypename());
        LoadImageUtil liu = new LoadImageUtil();
        //设置图片
        liu.loadImage(MainActivity.IAddress.getUrl_base() + infoBean.getIconurl(), new LoadImageUtil.OnLoadImageListener() {
            @Override
            public void OnLoadImageComplete(Bitmap bitmap) {
                mImgIv.setImageBitmap(bitmap);
            }
        });
    }
}
