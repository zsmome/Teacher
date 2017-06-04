package com.zsmome.screen2.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.zsmome.screen2.MainActivity;
import com.zsmome.screen2.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Administrator on 2017/5/17.
 */

public class NotifactionUtil {
    private Context mContext;
    public NotifactionUtil(Context context) {
        mContext = context;
        //创建大图标的Bitmap
        LargeBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.hint);
        mNManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
    }
    private NotificationManager mNManager;
    private Notification notify1;
    Bitmap LargeBitmap = null;
    private static final int NOTIFYID_1 = 1;
    public void sendNotifactoin(String contentTitle, String contentText, String subText, String ticker) {
        //定义一个PendingIntent点击Notification后启动一个Activity
        Intent it = new Intent(mContext, MainActivity.class);
        PendingIntent pit = PendingIntent.getActivity(mContext, 0, it, 0);
        //设置图片,通知标题,发送时间,提示方式等属性
        Notification.Builder mBuilder = new Notification.Builder(mContext);
        mBuilder.setContentTitle(contentTitle)                        //标题
                .setContentText(contentText)      //内容
                .setSubText(subText)                    //内容下面的一小段文字
                .setTicker(subText)             //收到信息后状态栏显示的文字信息
                .setWhen(System.currentTimeMillis())           //设置通知时间
                .setSmallIcon(R.mipmap.hint)            //设置小图标
//                .setLargeIcon(LargeBitmap)                     //设置大图标
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
                .setSound(Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.shuidi))  //设置自定义的提示音
                .setAutoCancel(true)                           //设置点击后取消Notification
                .setContentIntent(pit);                        //设置PendingIntent
        notify1 = mBuilder.build();
        mNManager.notify(NOTIFYID_1, notify1);
    }
    public void cancelNotifaction() {
        mNManager.cancel(NOTIFYID_1);
    }
}
