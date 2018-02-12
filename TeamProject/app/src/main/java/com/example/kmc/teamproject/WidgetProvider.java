package com.example.kmc.teamproject;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.kmc.teamproject.R;

public class WidgetProvider extends AppWidgetProvider {


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

//        String action = intent.getAction();
//        if(AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
//        {
//            Bundle extras = intent.getExtras();
//
//            if(extras!=null)
//            {
//                int [] appWidgetIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS);
//                if(appWidgetIds!=null && appWidgetIds.length>0)
//                    this.onUpdate(context,AppWidgetManager.getInstance(context),appWidgetIds);
//            }
//        }//업데이트인 경우
//        else if(action.equals("Click1"))
//        {
//            Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();
//        }
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
//        final int N = appWidgetIds.length;
//        for(int i=0;i<N;i++)
//        {
//            int appWidgetId = appWidgetIds[i];
//            RemoteViews views = buildViews(context);
//            appWidgetManager.updateAppWidget(appWidgetId,views);
//        }
    }

    private PendingIntent buildURIIntent(Context context)
    {
//        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://puzzleleaf.tistory.com"));
//        PendingIntent pi = PendingIntent.getActivity(context,0,intent,0);
//        return pi;
    }

    //Click1 이라는 Action을 onReceive로 보낸다.
    private PendingIntent buildToastIntent(Context context)
    {
        Intent in = new Intent("Click1");
        PendingIntent pi = PendingIntent.getBroadcast(context,0,in,PendingIntent.FLAG_UPDATE_CURRENT);
        return pi;
    }

    //위젯에 멀티 버튼 추가하기
    private RemoteViews buildViews(Context context)
    {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_widget);
        views.setOnClickPendingIntent(R.id.btnClose,buildURIIntent(context));
        views.setOnClickPendingIntent(R.id.btnOpen,buildToastIntent(context));

        return views;
    }
}
