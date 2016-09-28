package com.runhuaoil.anotherbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by RunHua on 2016/9/28.
 */

public class CustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "我也被激活啦,拦截掉", Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
