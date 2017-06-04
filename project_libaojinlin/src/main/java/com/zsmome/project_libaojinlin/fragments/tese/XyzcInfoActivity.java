package com.zsmome.project_libaojinlin.fragments.tese;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.tese.XyzcInfoListViewAdapter;
import com.zsmome.project_libaojinlin.adapters.tese.XyzcListViewAdapter;
import com.zsmome.project_libaojinlin.model.TeseXyzcInfo;
import com.zsmome.project_libaojinlin.util.ImageLoader;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;

import java.util.List;

public class XyzcInfoActivity extends AppCompatActivity {
    //声明组件
    private TextView mNameTv;
    private ImageView mImageIv;
    private ImageView mIconIv;
    private TextView mDescTv;
    private ListView mLv;
    //列表相关
    //适配器
    private XyzcInfoListViewAdapter mLvAda;
    //数据
    private List<TeseXyzcInfo.ListBean> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xyzc_info);
        initViews();
        initXyzInfoActivity();
    }

    /**
     * 初始化详情页面
     */
    private void initXyzInfoActivity() {
        //获得XyzcFragment传来的数据
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        mNameTv.setText(intent.getStringExtra("name"));
        mDescTv.setText(intent.getStringExtra("descs"));
        String imageUrl = intent.getStringExtra("iconUrl");
        new ImageLoader().loadImage(imageUrl, new ImageLoader.OnImageLoadListener() {
            @Override
            public void onImageLoadComplete(String url, Bitmap bitmap) {
                mImageIv.setImageBitmap(bitmap);
            }
        });
        String iconUrl = intent.getStringExtra("authorimg");
        new ImageLoader().loadImage(iconUrl, new ImageLoader.OnImageLoadListener() {
            @Override
            public void onImageLoadComplete(String url, Bitmap bitmap) {
                mIconIv.setImageBitmap(bitmap);
            }
        });
        initListView(intent.getStringExtra("id"));
    }

    /**
     * 初始化新游周刊详情中的ListView
     */
    private void initListView(String id) {
        //数据
        String url = MainActivity.IAddress.getUrl_xyzk_info() + id;
        new JSONParseUtil().parseJSON(url, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                TeseXyzcInfo info = new Gson().fromJson(json, TeseXyzcInfo.class);
                mData = info.getList();
                mLvAda = new XyzcInfoListViewAdapter(XyzcInfoActivity.this,  mData);
                mLv.setAdapter(mLvAda);
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        mNameTv = (TextView) findViewById(R.id.xyzc_info_top_title_tv);
        //走马灯效果
        mNameTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mNameTv.setSingleLine(true);
        mNameTv.setSelected(true);
        mNameTv.setFocusable(true);
        mNameTv.setFocusableInTouchMode(true);
        mImageIv = (ImageView) findViewById(R.id.xyzc_info_image_iv);
        mIconIv = (ImageView) findViewById(R.id.xyzc_info_icon_iv);
        mDescTv = (TextView) findViewById(R.id.xyzc_info_desc_tv);
        mLv = (ListView) findViewById(R.id.xyzc_info_lv);
    }
    /**
     * 返回键
     */
    public void back(View view) {
        finish();
    }
    /**
     * 分享键
     */
    public void share(View view) {
        Toast.makeText(this, "草民，莫慌！小编正在努力实现中...", Toast.LENGTH_SHORT).show();
    }
}
