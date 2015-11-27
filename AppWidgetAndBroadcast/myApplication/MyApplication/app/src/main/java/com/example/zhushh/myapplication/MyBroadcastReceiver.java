package com.example.zhushh.myapplication;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by zhushh on 2015/11/16.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // use RemoteViews to update Widget
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        // set editText
        remoteViews.setTextViewText(R.id.widget, intent.getStringExtra("message"));

        AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(),
                MyWidgetProvider.class), remoteViews);
    }
}
