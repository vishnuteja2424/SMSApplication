package com.example.smsapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    @Override
    public IBinder onBind (Intent arg0) {
        return null;
    }
    @Override
    public int onStartCommand (Intent intent , int flags , int startId) {
        super .onStartCommand(intent , flags , startId) ;

        if(intent != null){
            handler.post(() -> {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "id")
                        .setSmallIcon(R.drawable.ic_baseline_message_24)
                        .setContentTitle(intent.getExtras().getString("number"))
                        .setContentText(intent.getExtras().getString("message"))
                        .setPriority(NotificationCompat.PRIORITY_HIGH).setAutoCancel(true);


                Intent notifIntent = new Intent(getApplicationContext(), DisplayActivity.class);
                notifIntent.putExtra("msg", intent.getExtras().getString("message"));
                notifIntent.putExtra("num", intent.getExtras().getString("number"));
                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notifIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(contentIntent);

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());


            });
        }
        return START_STICKY ;
    }
    @Override
    public void onCreate () {}
    @Override
    public void onDestroy () {
        super .onDestroy() ;
    }
    final Handler handler = new Handler() ;
}
