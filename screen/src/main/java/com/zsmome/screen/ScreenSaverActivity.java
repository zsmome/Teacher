package com.zsmome.screen;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class ScreenSaverActivity extends Activity {
	protected static final String TAG = "ScreenSaverActivity";
	private static PowerManager.WakeLock mWakeLock;
//	private PlayControl mPlayControl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_saver);
		PowerManager pm = (PowerManager)getSystemService(POWER_SERVICE); 
		mWakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | 
	  			PowerManager.SCREEN_DIM_WAKE_LOCK | 
	  			PowerManager.ON_AFTER_RELEASE, "SimpleTimer");
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
	    //mHandler01.postDelayed(mTasks01, intervalKeypadeSaver);
		mWakeLock.acquire();
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub   
		mWakeLock.release();
		super.onPause();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_MENU) {
	        //���/���ز˵���
	    	finish();
	    } else if(keyCode == KeyEvent.KEYCODE_HOME) {
	        //����Home��Ϊϵͳ�����˴����ܲ�����Ҫ��дonAttachedToWindow()
	    	finish();
	    	//return false;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		finish();
		return super.onTouchEvent(event);
	}
	//������
    @Override  
    public boolean onSearchRequested() {  
	     // TODO Auto-generated method stub   
	    	finish();
	     return super.onSearchRequested();  
    }

}
