package com.zsmome.day10_mul_layout_listview_edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zsmome.day10_mul_layout_listview_edit.adapters.MsgAdapter;
import com.zsmome.day10_mul_layout_listview_edit.models.Msg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //声明组件
    private ListView mMsgLv;
    private EditText mOtherEt;
    private EditText mMeEt;
    private Button mOtherSendBt;
    private Button mMeSendBt;
    //列表适配器
    private MsgAdapter mMsgAdapter;
    //数据
    private List<Msg> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
    }

    /**
     * 设置监听器
     */
    private void initListener() {
        mOtherSendBt.setOnClickListener(this);
        mMeSendBt.setOnClickListener(this);
    }

    /**
     *  初始化组件
     */
    private void initViews() {
        mMsgLv = (ListView) findViewById(R.id.msgLv);

        mOtherEt = (EditText) findViewById(R.id.otherEt);
        mMeEt = (EditText) findViewById(R.id.meEt);
        mOtherSendBt = (Button) findViewById(R.id.otherSendBt);
        mMeSendBt = (Button) findViewById(R.id.meSendBt);
        //设置ListView
        mData = new ArrayList<>();
        mMsgAdapter = new MsgAdapter(this, mData);
        mMsgLv.setAdapter(mMsgAdapter);
    }

    /**
     * 监听两个
     * @param v
     */
    @Override
    public void onClick(View v) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        String currentTime = sdf.format(date);
        switch(v.getId()) {
            case R.id.meSendBt:
                if(mMeEt.getText().toString().isEmpty()) {
                    mMeEt.setHint("内容不能为空！");
                } else {
                    mMsgAdapter.addData(new Msg(R.mipmap.a1, mMeEt.getText().toString(), currentTime, "me"));
                }
                break;
            case R.id.otherSendBt:
                if(mOtherEt.length() == 0) {
                    mMeEt.setHint("内容不能为空！");
                }else {
                    mMsgAdapter.addData(new Msg(R.mipmap.a2, mOtherEt.getText().toString(), currentTime, "other"));
                }
                break;
        }
        mMsgLv.setSelection(mMsgAdapter.getCount()-1);
    }
}
