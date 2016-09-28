package com.example.runhua.activitylifecycletest;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by RunHua on 2016/9/22.
 */

public class NormalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);
    }
}
