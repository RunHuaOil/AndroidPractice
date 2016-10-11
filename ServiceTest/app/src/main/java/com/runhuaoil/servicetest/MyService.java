package com.runhuaoil.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by RunHua on 2016/10/9.
 */

public class MyService extends Service {

    private DownloadTask downloadTask = new DownloadTask();
    private boolean isDestroy = false;
    /*该布尔值记录服务是否 销毁 ，以此来让 线程的 run 方法停止运行，
    否则service 被销毁后，线程还在运行会报异常
     */

    @Override
    public void onCreate() {

        super.onCreate();
        Log.d("Test","服务首次被创建.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroy = true;
        Log.d("Test","服务被摧毁.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Test","服务开始.");
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Test","服务绑定.");
        return downloadTask;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("Test","服务解绑.");
        return super.onUnbind(intent);
    }


    class DownloadTask extends Binder {
        public static final int MAX_PROGRESS = 100;
        private int downloadProgress = 0;


        private onProgressListener onProgressListener;

        public void setOnProgressListener(onProgressListener onProgressListener){
            this.onProgressListener = onProgressListener;
        }

        public void startDownload(){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //开启线程下载任务(模拟)
                    while (downloadProgress < MAX_PROGRESS){

                        try {
                            if (isDestroy){
                                /*该布尔值记录服务是否 销毁 ，以此来让 线程的 run 方法停止运行，
                                    否则service 被销毁后，线程还在运行会报异常
                                */
                                break;
                            }
                            downloadProgress += 5;
                            if(onProgressListener != null){
                                onProgressListener.onProgressUpdate(downloadProgress);
                                //回调方法，通知更新UI
                            }
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }
    }
}
