package com.zsmome.screen2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zsmome.screen2.models.UserTable;
import com.zsmome.screen2.models.YouDao;
import com.zsmome.screen2.services.LockService;
import com.zsmome.screen2.sqlite.UserDAOImpl;
import com.zsmome.screen2.utils.InternalStorage;
import com.zsmome.screen2.utils.YouDaoParse;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    public static final String SERVICE_SCREEN_STATE = "service_screen_state";
    public static final String SCREEN_STATE = "screen_state";
    //声明控件
    private RelativeLayout mMainRl;
    private LinearLayout mLoginLl;
    private Switch mScreenServiceSwitch;
    //服务
    private Intent mServiceIntent = null;
    //SharedPreferences存储
    private boolean screen_service_state;
    //存储本地文件
    private InternalStorage mIStorage = null;
    //服务名
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIStorage = new InternalStorage(this);
        setContentView(R.layout.activity_main);
        initViews();
        //是否已经登录，是登录，删除登录和注册控件
        isDelView();
        //屏幕服务状态
        screenServiceState();

    }

    /**
     * 屏幕服务状态
     */
    private void screenServiceState() {
        //锁屏监听
        mScreenServiceSwitch.setOnCheckedChangeListener(this);
        //初始为开启服务
        if(TextUtils.isEmpty(mIStorage.getData(SERVICE_SCREEN_STATE, SCREEN_STATE))) {
            mIStorage.saveData(SERVICE_SCREEN_STATE, SCREEN_STATE, "true");
        }
        //获取数据,设置状态
        String state = mIStorage.getData(SERVICE_SCREEN_STATE, SCREEN_STATE);
        screen_service_state = state.equals("true") ? true : false;
        mScreenServiceSwitch.setChecked(screen_service_state);
    }

    /**
     * 如果已经登录，则删除控件
     */
    private void isDelView() {
        //new InternalStorage(this).saveData("user_info", "username", name);
        String name = mIStorage.getData("user_info", "username");
        if(!TextUtils.isEmpty(name)) {
            showUserInfoView(name);
        }
    }

    /**
     * 显示用户信息
     * @param name
     */
    private void showUserInfoView(String name) {
        //删除登录和注册按钮
        int count = mLoginLl.getChildCount();
        for(int i=0; i<count; i++) {
            mLoginLl.removeView(mLoginLl.getChildAt(0));
        }
        //获取用户信息
        UserTable userTable = new UserDAOImpl(this).getUser(name);
        //添加用户信息
        View view = LayoutInflater.from(this).inflate(R.layout.user_info, null);
        mLoginLl.addView(view);
        TextView userName = (TextView)view.findViewById(R.id.user_info_name);
        TextView userDay = (TextView)view.findViewById(R.id.user_info_day);
        TextView userHintCount = (TextView)view.findViewById(R.id.user_info_hintCount);
        userName.setText(userTable.getUsername());
        userHintCount.setText(userTable.getHintRemainCount()+"");
        //注册时，和现在的天数差
        //日期
        Date d1 = new Date(userTable.getDate());
        Date d2 = new Date();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c1.setTime(d2);
        int day1 = c1.get(Calendar.DAY_OF_YEAR);
        int day2 = c2.get(Calendar.DAY_OF_YEAR);
        userDay.setText(day2-day1+"天");
    }

    /**
     * 绑定控件
     */
    private void initViews() {
        mMainRl = (RelativeLayout) findViewById(R.id.activity_main);
        mLoginLl = (LinearLayout) findViewById(R.id.login_ll);
        mScreenServiceSwitch = (Switch) findViewById(R.id.screen_service_switch);
    }

    /**
     * 开关服务
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.screen_service_switch:
                if(buttonView.isChecked()) {
                    mIStorage.saveData(SERVICE_SCREEN_STATE, SCREEN_STATE, "true");
                    //打开服务
                    mServiceIntent = new Intent(MainActivity.this, LockService.class);
                    startService(mServiceIntent);
                    Toast.makeText(MainActivity.this, "打开锁屏服务", Toast.LENGTH_SHORT).show();
                } else {
                    mIStorage.saveData(SERVICE_SCREEN_STATE, SCREEN_STATE, "false");
                    stopService(mServiceIntent);
                    Toast.makeText(MainActivity.this, "关闭锁屏服务", Toast.LENGTH_SHORT).show();
                }
        }
    }

    /**
     * 翻译
     * @param view
     */
    public void fanyiButton(View view) {
        new YouDaoParse().parseJSON("one", new YouDaoParse.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                YouDao youDao = new Gson().fromJson(json, YouDao.class);
                //解析接口，是否正常
                if(json.isEmpty() || youDao.getErrorCode() != 0) {
                    Toast.makeText(MainActivity.this, "啧啧,请检查网络！", Toast.LENGTH_SHORT).show();
                    Log.i("lockactivity", "啧啧,请检查网络！");
                    return;
                } else {
                    Intent intent = new Intent(MainActivity.this, FanYiActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 登录
     * @param view
     */
    public void loginButton(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    /**
     * 注册
     * @param view
     */
    public void registButton(View view) {
        startActivity(new Intent(this, RegistActivity.class));
    }
}
