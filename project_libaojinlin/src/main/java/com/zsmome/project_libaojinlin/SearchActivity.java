package com.zsmome.project_libaojinlin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zsmome.project_libaojinlin.model.SearchWeekly;
import com.zsmome.project_libaojinlin.util.JSONParseUtil;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    //声明组件
    private EditText mSearchEt;
    //网址
    public static final String URL_SEARCH = MainActivity.IAddress.getUrl_search();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
    }

    /**
     * 找到控件
     */
    private void initViews() {
        mSearchEt = (EditText) findViewById(R.id.libao_search_et);
    }

    /**
     * 回退
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * 搜索
     * @param view
     */
    public void search(View view) {
        String key = mSearchEt.getText().toString();
        if(key.isEmpty()) {
            Toast.makeText(this, "搜索不能为空！", Toast.LENGTH_SHORT).show();
            return ;
        }
        String url = URL_SEARCH;
        new JSONParseUtil().loadJSONWithPost(url, key, new JSONParseUtil.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                Log.i("SearchActivity", json);
                SearchWeekly searchWeekly = new Gson().fromJson(json, SearchWeekly.class);
                /*初始化列表*/
                initListView(searchWeekly.getList());
            }
        });
    }

    /**
     * 初始化列表
     * @param list
     */
    private void initListView(List<SearchWeekly.ListBean> list) {

    }
}
