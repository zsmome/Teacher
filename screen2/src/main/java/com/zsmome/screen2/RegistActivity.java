package com.zsmome.screen2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zsmome.screen2.models.UserTable;
import com.zsmome.screen2.sqlite.UserDAOImpl;
import com.zsmome.screen2.utils.InternalStorage;

import java.util.Date;

public class RegistActivity extends AppCompatActivity {
    public static final int HINT_REMAIN_COUNT = 20;
    private EditText mNameEt;
    private EditText mPasswordEt;
    private EditText mConfirmEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        bindViews();
    }

    /**
     * 绑定组件
     */
    private void bindViews() {
        mNameEt = (EditText) findViewById(R.id.regist_name_et);
        mPasswordEt = (EditText) findViewById(R.id.regist_password_et);
        mConfirmEt = (EditText) findViewById(R.id.regist_confirm_password_et);
    }

    /**
     * 注册
     * @param view
     */
    public void registButton(View view) {
        String name = mNameEt.getText().toString();
        String p1 = mPasswordEt.getText().toString();
        String p2 = mConfirmEt.getText().toString();
        //非空判断
        boolean isEmpty = TextUtils.isEmpty(name)
                ||  TextUtils.isEmpty(p1)
                || TextUtils.isEmpty(p2);
        if(isEmpty) {
            Toast.makeText(this, "不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断密码是不是一致
        if(!p1.equals(p2)) {
            Toast.makeText(this, "密码不一致！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(new UserDAOImpl(this).getUser(name) != null) {
            Toast.makeText(this, "用户名已经存在！", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
            new UserDAOImpl(this).insert(new UserTable(name, p1, new Date().getTime(), HINT_REMAIN_COUNT, 0));
            new InternalStorage(this).saveData("user_info", "username", name);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
