package com.zsmome.day17_history;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.zsmome.day17_history.adapters.CommonAdapter;
import com.zsmome.day17_history.adapters.ViewHolder;
import com.zsmome.day17_history.model.History;
import com.zsmome.day17_history.sqlite.HistoryDAOImpl;
import com.zsmome.day17_history.sqlite.MySqliteHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*声明控件*/
    private EditText mInputEt;
    private ListView mHistoryLv;
    /*获得数据库帮助类*/
    private MySqliteHelper mDBHelper = new MySqliteHelper(this);
    /*历史数据库操作类*/
    private HistoryDAOImpl mHistoryOP = new HistoryDAOImpl(this);
    /*历史集合*/
    private List<History> mHistoryList = new ArrayList<>();
    /*适配器*/
    private CommonAdapter<History> mHistoryAda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBHelper.getWritableDatabase();
        bindViews();
        initListView();
    }

    /**
     * 初始化控件
     */
    private void initListView() {
        /*数据*/
        mHistoryList = mHistoryOP.searchContent("");
        /*适配器*/
        mHistoryAda = new CommonAdapter<History>(this, mHistoryList, R.layout.list_view_history) {
            @Override
            public void setViews(ViewHolder viewHolder, final History data) {
                viewHolder.setTextViewText(R.id.content_tv, data.getContent());
                viewHolder.setTextViewText(R.id.date_tv, data.getDate());
                viewHolder.setTextViewText(R.id.count_tv, data.getCount()+"");
                /*删除按钮*/
                viewHolder.getView(R.id.delete_bt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHistoryOP.delete(data.getContent());
                        mHistoryList = mHistoryOP.searchContent(mInputEt.getText().toString());
                        mHistoryAda.refresh(mHistoryList);
                    }
                });
            }
        };
        /*设置适配器*/
        mHistoryLv.setAdapter(mHistoryAda);
    }

    /**
     * 绑定控件
     */
    private void bindViews() {
        mInputEt = (EditText) findViewById(R.id.input_et);
        mHistoryLv = (ListView) findViewById(R.id.search_Lv) ;
    }

    /**
     * 搜索历史记录
     * @param view
     */
    public void onSearch(View view) {
        String content = mInputEt.getText().toString();
        /*更新数据*/
        mHistoryList = mHistoryOP.searchContent(content);
        mHistoryAda.refresh(mHistoryList);
        if(!TextUtils.isEmpty(content)) {
            /*如果存在更新次数*/
            if (mHistoryOP.exists(content)) {
                mHistoryOP.update(content);
            } else {
                Date time = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
                String date = sdf.format(time);
                mHistoryOP.insert(new History(content, date, 0));
            }
        }
    }
}