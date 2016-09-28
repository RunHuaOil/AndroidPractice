package com.runhuaoil.broadcastoffline;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by RunHua on 2016/9/23.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}



