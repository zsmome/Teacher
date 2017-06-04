package com.zsmome.day20_broadcast.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/21.
 */

public class MsgBroadcast extends BroadcastReceiver {
    public static final String ACTION_MSG = "ACTION_MSG";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(ACTION_MSG.equals(action)) {
            Toast.makeText(context, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
        }
    }
}
