package com.zsmome.project_libaojinlin.fragments.youxi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.libao.VPagerAdapter;
import com.zsmome.project_libaojinlin.model.YouxiKaifuInfo;
import com.zsmome.project_libaojinlin.util.ImageLoader;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;

import java.util.ArrayList;
import java.util.List;

public class KaifuInfoActivity extends AppCompatActivity {
    //声明组件
    private TextView mTopTitleTv;
    private ImageView mIconIv;
    private TextView mNameTv;
    private TextView mTypeTv;
    private TextView mSizeTv;
    private ViewPager mImageVp;
    private TextView mDescTv;
    //ViewPager相关
    private List<View> mVpData;
    //数据
    private YouxiKaifuInfo mData;
    //基本网址
    public static final String URL_BASE = MainActivity.IAddress.getUrl_base();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaifu_info);
        initViews();
        initActivity();
    }

    /**
     * 初始化活动界面
     */
    private void initActivity() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        //解析数据
        new JSONParseUtil().parseJSON(MainActivity.IAddress.getUrl_kaifu_info() + id, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                mData = new Gson().fromJson(json, YouxiKaifuInfo.class);
                data2View(mData);
            }
        });
    }

    /**
     * 将数据设置到控件上
     */
    private void data2View(YouxiKaifuInfo mData) {
        YouxiKaifuInfo.AppBean data = mData.getApp();
        List<YouxiKaifuInfo.ImgBean> mImgList = mData.getImg();
        mTopTitleTv.setText(data.getName());
        mNameTv.setText(data.getName());
        mTypeTv.setText("类型:"+data.getTypename());
        //是不是未知大小
        String size = "".equals(data.getAppsize()) ? "未知" : data.getAppsize();
        mSizeTv.setText("大小:"+size);
        mDescTv.setText(data.getDescription());
        //小图标
        String imageUrl = MainActivity.IAddress.getUrl_base() + data.getLogo();
        new ImageLoader().loadImage(imageUrl, new ImageLoader.OnImageLoadListener() {
            @Override
            public void onImageLoadComplete(String url, Bitmap bitmap) {
                mIconIv.setImageBitmap(bitmap);
            }
        });
        //图片集合
        mVpData = new ArrayList<>();
        //设置图片
        for(YouxiKaifuInfo.ImgBean img : mImgList) {
            String url = URL_BASE + img.getAddress();
            final ImageView iv = new ImageView(this);
            new ImageLoader().loadImage(url, new ImageLoader.OnImageLoadListener() {
                @Override
                public void onImageLoadComplete(String url, Bitmap bitmap) {
                    iv.setImageBitmap(bitmap);
                }
            });
            mVpData.add(iv);
            VPagerAdapter mVpAda =  new VPagerAdapter(mVpData);
            mImageVp.setAdapter(mVpAda);
        }
    }

    /**
     * 初始化组件
     */
    private void initViews() {
        mTopTitleTv = (TextView) findViewById(R.id.kaifu_info_top_title_tv);
        mIconIv = (ImageView) findViewById(R.id.kaifu_info_icon_iv);
        mNameTv = (TextView) findViewById(R.id.kaifu_info_title_tv);
        mTypeTv = (TextView) findViewById(R.id.kaifu_info_type_tv);
        mSizeTv = (TextView) findViewById(R.id.kaifu_info_size_tv);
        mImageVp = (ViewPager) findViewById(R.id.kaifu_info_vp);
        mDescTv = (TextView) findViewById(R.id.kaifu_info_desc_tv);
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
