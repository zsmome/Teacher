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

public class LoginActivity extends AppCompatActivity {
    private EditText mNameEt;
    private EditText mPasswordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
    }

    /**
     * 绑定组件
     */
    private void bindViews() {
        mNameEt = (EditText) findViewById(R.id.login_name_et);
        mPasswordEt = (EditText) findViewById(R.id.login_password_et);
    }
    /**
     * 登录
     * @param view
     */
    public void loginButton(View view) {
        String name = mNameEt.getText().toString();
        String password = mPasswordEt.getText().toString();
        //非空判断
        boolean isEmpty = TextUtils.isEmpty(name)
                ||  TextUtils.isEmpty(password);
        if(isEmpty) {
            Toast.makeText(this, "不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        UserTable userTable = new UserDAOImpl(this).getUser(name);
        //登录成功跳转
        if( userTable != null && userTable.getPassword().equals(password)) {
            new InternalStorage(this).saveData("user_info", "username", name);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
