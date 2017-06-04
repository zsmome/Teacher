package com.zsmome.fanyiscreen.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/5/13.
 */

public class AutoStartService extends BroadcastReceiver
{
    public AutoStartService()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            context.startService(new Intent(context, LockService.class));
        }
    }
}