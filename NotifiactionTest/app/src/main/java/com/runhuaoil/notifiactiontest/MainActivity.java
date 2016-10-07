package com.runhuaoil.notifiactiontest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSend = (Button) findViewById(R.id.button_send);
        buttonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_send:
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Intent intent1 = new Intent(this,MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);

                long[] vibrates = {0, 1000, 1000, 1000};
                //振动频率设置
                String uriStr = "android.resource://" + this.getPackageName() + "/" + R.raw.notify;
                //一般将一些音视频文件放在 raw 文件夹中，通过以上方式转成 uri 字段以便解析
                Uri soundUri =Uri.parse(uriStr);


                NotificationCompat.Builder notifibuilder = new NotificationCompat.Builder(this)
                        .setContentTitle("标题")
                        .setContentText("内容")
                        .setContentInfo("Info")
                        //.setDefaults(NotificationCompat.DEFAULT_ALL) 该方法通过 DEFAULT_ALL 常量默认实现铃声和振动以及提示灯
                        .setSound(soundUri)//设置通知铃声
                        .setVibrate(vibrates)//设置振动
                        .setContentIntent(pi)//设置通知的 点击触发事件 PendingIntent.
                        .setAutoCancel(true)//设置点击通知后自动清楚该通知
                        .setSmallIcon(R.mipmap.ic_launcher);

                Notification notification = notifibuilder.build();
                // build 方法生成 Notification 对象
                manager.notify(1, notification);


                break;
            default:
                break;
        }
    }


}
