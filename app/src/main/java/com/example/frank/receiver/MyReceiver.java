package com.example.frank.receiver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyReceiver extends BroadcastReceiver {

    static int id = 70000;
    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("KEY_MSG");

        Intent newintent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_MSG",msg);
        newintent.putExtras(bundle);

        newintent.setClass(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newintent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notify = newNotification(context, pendingIntent, "(New) Broadcast is received.",msg);

        NotificationManager notificationManager =(NotificationManager)context.
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id++, notify);

    }
    @SuppressLint("NewApi")
    private Notification newNotification(
            Context context, PendingIntent pi,
            String title, String msg) {
        Notification.Builder builder =
                new Notification.Builder(context);

        builder.setContentTitle("Hello ");
        builder.setContentText(msg);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pi);
        builder.setTicker(msg);
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        Notification notify = builder.build();
        return notify;
    }

}
