package com.runhuaoil.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private Button startDownload;
    private Button startIntentService;
    private TextView textView;

    private boolean isBindService = false;//记录 service 绑定和取消绑定的状态

    private MyService.DownloadTask downloadTask = null;
    private Intent serviceIntent = null;

    private static final  int UPDATE_TEXT = 1;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText("当前下载: " + msg.arg1 + "%");
                    if (msg.arg1 == 100){
                        unbindService(connection);
                        isBindService = false;
                        //下载任务完成后，解绑服务
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            downloadTask = (MyService.DownloadTask) service;
            downloadTask.setOnProgressListener(new onProgressListener() {
                @Override
                public void onProgressUpdate(int downloadProgress) {
                    Message message = new Message();
                    message.arg1 = downloadProgress;//存储 下载进度，以便后面handler使用
                    message.what = UPDATE_TEXT;
                    handler.sendMessage(message);
                    //利用handler来异步更新UI
                }
            });

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        startDownload = (Button) findViewById(R.id.start_download);
        startIntentService = (Button) findViewById(R.id.start_intentService);

        startIntentService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        startDownload.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);

        serviceIntent = new Intent(this, MyService.class);//启动 service 的 Intent
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_intentService:
                Log.d("Test", "Thread id is " + Thread.currentThread().getId());
                Intent intent = new Intent(this, MyIntentService.class);
                startService(intent);
                break;
            case R.id.start_download:
                if(downloadTask != null){
                    downloadTask.startDownload();//开始启动 service 中的 downloadTask 去下载
                }
                break;
            case R.id.start_service:
                startService(serviceIntent); // 启动服务
                break;
            case R.id.stop_service:
                stopService(serviceIntent); // 停止服务
                break;
            case R.id.bind_service:
                bindService(serviceIntent, connection, BIND_AUTO_CREATE); //  绑定服务
                isBindService = true;
                break;
            case R.id.unbind_service:
                unbindService(connection); //  解绑服务
                isBindService = false;
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        if (isBindService){
            unbindService(connection);
            /* service 和 Activity 绑定后，Activity销毁时 绑定的 service 会自动销毁，但是会报异常，这里
            手动 解绑可以解决异常问题，又由于如果没有先使用 bindService 而  unbindService，将会报错异常，
            这里用 isBindService 来判定 service 的绑定和解绑两个状态，以供判断
            */
        }
        //stopService(serviceIntent);
        super.onDestroy();
    }


}
