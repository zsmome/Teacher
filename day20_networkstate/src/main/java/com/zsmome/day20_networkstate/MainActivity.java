package com.zsmome.day20_networkstate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zsmome.day20_networkstate.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNetChange(int netMobile) {
        Log.i("state", netMobile+"");
        super.onNetChange(netMobile);
    }
}
