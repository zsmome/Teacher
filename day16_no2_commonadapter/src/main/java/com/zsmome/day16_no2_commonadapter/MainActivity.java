package com.zsmome.day16_no2_commonadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zsmome.day16_no2_commonadapter.adapters.CommonAdapter;
import com.zsmome.day16_no2_commonadapter.adapters.ViewHolder;
import com.zsmome.day16_no2_commonadapter.models.InterfaceAddress;
import com.zsmome.day16_no2_commonadapter.models.Weekly;
import com.zsmome.day16_no2_commonadapter.utils.JSONParseUtil;

public class MainActivity extends AppCompatActivity {
    //声明控件
    private ListView mFruitLv;
    //显示第几页
    public static int pageNo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initUI();
    }

    /**
     * 初始化界面
     */
    private void initUI() {
        final InterfaceAddress IAddress = new InterfaceAddress(getResources());
        new JSONParseUtil().parseJSON(IAddress.getUrl_liBaoLv() + pageNo, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Log.i("json", "OnJSONParseComplete: "+json);
                Weekly weekly = new Gson().fromJson(json, Weekly.class);
                CommonAdapter<Weekly.ListBean> commonAdapter = new CommonAdapter<Weekly.ListBean>(MainActivity.this, weekly.getList(), R.layout.list_view_fruit){
                    //设置控件参数
                    @Override
                    public void setViews(ViewHolder viewHolder, Weekly.ListBean data) {
                        viewHolder.setImageViewURL(R.id.imgIv, IAddress.getUrl_base()+data.getIconurl());
                        viewHolder.setTextViewText(R.id.titleTv, data.getGname());
                        viewHolder.setTextViewText(R.id.timeTv, data.getAddtime());
                    }
                };
                //设置适配器
                mFruitLv.setAdapter(commonAdapter);
            }
        });
    }

    /**
     * 找到控件
     */
    private void initViews() {
        mFruitLv = (ListView) findViewById(R.id.fruit_lv);
    }
}
