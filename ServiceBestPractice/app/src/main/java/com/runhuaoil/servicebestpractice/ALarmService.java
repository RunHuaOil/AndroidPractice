package com.runhuaoil.servicebestpractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by RunHua on 2016/10/11.
 */

public class ALarmService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Test","" + new Date().toString());
            }
        }).start();

        Intent intent1 = new Intent(this,AlarmReceiver.class);
        PendingIntent pd = PendingIntent.getBroadcast(this,0 , intent1,0);


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        long intervalTime = SystemClock.elapsedRealtime() + 5 * 1000;

        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, intervalTime, pd);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
