package com.runhuaoil.servicetest;

/**
 * Created by RunHua on 2016/10/10.
 */

public interface onProgressListener {
    //观察着模式来主动更新 UI 下载进度
    void onProgressUpdate(int downloadProgress);
}
