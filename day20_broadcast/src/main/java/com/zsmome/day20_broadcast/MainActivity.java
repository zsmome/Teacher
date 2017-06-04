package com.zsmome.day20_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zsmome.day20_broadcast.broadcast.MsgBroadcast;

import java.util.Date;

/**
 * 静态注册广播-AndroidManifest.xml中配置
 * 动态注册广播-onCreate中注册,onDestroy中注销
 */
public class MainActivity extends AppCompatActivity {
    /*声明控件*/
    private TextView mNetworkStateTv;
    /*发送消息广播*/
    private MsgBroadcast msgBroadcast = new MsgBroadcast();
    /*网络状态广播*/
    private ConnectivityManager mConnectivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        /*注册消息广播*/
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ACTION_MSG");
        registerReceiver(msgBroadcast, intentFilter);

        /*网络状态管理*/
        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //注册广播接收器，监控网络状态
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkBroadcast,intentFilter2);
    }

    /**
     * 绑定控件
     */
    private void bindViews() {
        mNetworkStateTv = (TextView) findViewById(R.id.network_state_tv);
    }

    /**
     * 监听网络状态广播
     */
    BroadcastReceiver mNetworkBroadcast = new BroadcastReceiver(){
        /**
         * 网络类型
         * @return
         */
        public String getNetworkType() {
            NetworkInfo network = mConnectivityManager.getActiveNetworkInfo();
            if(network == null) {
                return "没有可用网络";
            }
            return network.getTypeName();
        }

        /**
         * 网络连接状态
         * @return
         */
        public boolean getNetworkState() {
            NetworkInfo mobile = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifi = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return mobile.isConnected() || wifi.isConnected();
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                /*获得网络状态*/
                boolean state = getNetworkState();
                String net;
                if(state) {
                    net = getNetworkType();
                }else {
                    net = "没有网络";
                }
                mNetworkStateTv.setText(net);
            }
        }
    };

    /**
     * 发送广播消息
     * @param view
     */
    public void send(View view) {
        Intent intent = new Intent(MsgBroadcast.ACTION_MSG);
        intent.putExtra("msg", "广播消息："+new Date().toString());
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        /*注销广播*/
        unregisterReceiver(msgBroadcast);
        unregisterReceiver(mNetworkBroadcast);
        super.onDestroy();
    }
}
