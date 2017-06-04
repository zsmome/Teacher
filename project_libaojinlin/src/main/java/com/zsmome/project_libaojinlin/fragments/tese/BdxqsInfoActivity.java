package com.zsmome.project_libaojinlin.fragments.tese;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zsmome.project_libaojinlin.MainActivity;
import com.zsmome.project_libaojinlin.R;
import com.zsmome.project_libaojinlin.adapters.tese.BdxqsInfoGridViewAdapter;
import com.zsmome.project_libaojinlin.model.TeseBdxqsInfo;
import com.zsmome.project_libaojinlin.util.ImageLoader;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;

import java.util.List;

public class BdxqsInfoActivity extends AppCompatActivity {
    //声明组件
    private TextView mTitleTv;
    private ImageView mImageIv;
    private TextView mDateTv;
    private TextView mIntroductionTv;
    private GridView mDownGv;
    /*列表*/
    //适配器
    private BdxqsInfoGridViewAdapter mInfoGvAda;
    //数据
    private List<TeseBdxqsInfo.ListBean> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdxqs_info);
        initActivity();
    }

    /**
     * 初始化界面
     */
    private void initActivity() {
        //找到所有控件
        initViews();
        //接收数据
        Intent intent = getIntent();

        //图片
        String url = intent.getStringExtra("icon");
        new ImageLoader().loadImage(url, new ImageLoader.OnImageLoadListener() {
            @Override
            public void onImageLoadComplete(String url, Bitmap bitmap) {
                mImageIv.setImageBitmap(bitmap);
            }
        });
        //标题
        mTitleTv.setText(intent.getStringExtra("name"));
        //时间
        mDateTv.setText(intent.getStringExtra("addtime"));
        //描述
        mIntroductionTv.setText(intent.getStringExtra("descs"));
        //下载号
        String sidUrl = intent.getStringExtra("sid");
        initGridView(sidUrl);
    }

    /**
     * 初始化列表
     */
    private void initGridView(String sidUrl) {
        new JSONParseUtil().parseJSON(sidUrl, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                TeseBdxqsInfo info = new Gson().fromJson(json, TeseBdxqsInfo.class);
                mData = info.getList();
                mInfoGvAda = new BdxqsInfoGridViewAdapter(BdxqsInfoActivity.this, mData);
                mDownGv.setAdapter(mInfoGvAda);
            }
        });
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
    /**
     * 初始组件
     */
    private void initViews() {
        mTitleTv = (TextView) findViewById(R.id.bdxqs_info_top_title_tv);
        mTitleTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mTitleTv.setSingleLine(true);
        mTitleTv.setSelected(true);
        mTitleTv.setFocusable(true);
        mTitleTv.setFocusableInTouchMode(true);
        mImageIv = (ImageView) findViewById(R.id.tese_bdxqs_info_iv);
        mDateTv = (TextView) findViewById(R.id.tese_bdxqs_info_date_tv);
        mIntroductionTv = (TextView) findViewById(R.id.tese_bdxqs_info_introduction_tv);
        mDownGv = (GridView) findViewById(R.id.tese_bdxqs_info_gv);
    }
}
