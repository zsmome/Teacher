package com.zsmome.screen2.services;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zsmome.screen2.LockActivity;

/**
 * Created by Divano on 2016/10/21.
 */
public class LockService extends Service {
    private Intent mFxLockIntent = null;
    private KeyguardManager mKeyguardManager = null;
    private KeyguardManager.KeyguardLock mKeyguardLock = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mFxLockIntent = new Intent(LockService.this, LockActivity.class);
        mFxLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        registerComponent();
    }
    private void registerComponent() {
        IntentFilter mScreenOnOrOffFilter = new IntentFilter();
        mScreenOnOrOffFilter.addAction("android.intent.action.SCREEN_ON");
        mScreenOnOrOffFilter.addAction("android.intent.action.SCREEN_OFF");
        LockService.this.registerReceiver(mScreenOnOrOffReceiver, mScreenOnOrOffFilter);
    }
    //监听来自用户按Power键点亮点暗屏幕的广播
    private BroadcastReceiver mScreenOnOrOffReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_ON")
                    || intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
//				refreshInfo();
                mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
                mKeyguardLock = mKeyguardManager.newKeyguardLock("FxLock");
                //屏蔽手机内置的锁屏
                mKeyguardLock.disableKeyguard();
                //启动该第三方锁屏
                startActivity(mFxLockIntent);
            }
        }
    };
    //解除广播监听
    public void unregisterComponent() {
        if (mScreenOnOrOffReceiver != null) {
            LockService.this.unregisterReceiver(mScreenOnOrOffReceiver);
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterComponent();
    }
}
