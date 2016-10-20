package com.runhuaoil.sensormanagertest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView lightLevel;
    private long time;
    private boolean isToast = false;
    private RelativeLayout activityMainID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = System.currentTimeMillis();
        lightLevel = (TextView) findViewById(R.id.light_level);
        activityMainID = (RelativeLayout) findViewById(R.id.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);
//        for (Sensor s :
//                list) {
//            Log.d("Test",s.getName());
//
//        }  显示手机支持的传感器列表
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Sensor sensorAccelerome = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(lightListener, sensor,  SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(accelerationListener,sensorAccelerome, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lightListener != null ){
            sensorManager.unregisterListener(lightListener);
        }
        if (accelerationListener != null ){
            sensorManager.unregisterListener(accelerationListener);
        }

    }

    private SensorEventListener lightListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            float value = event.values[0];
            lightLevel.setText("当前光照强度:" + value + "lx");

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private SensorEventListener accelerationListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            float xValue = Math.abs(event.values[0]);
            float yValue = Math.abs(event.values[1]);
            float zValue = Math.abs(event.values[2]);
            //Log.d("Test", "x:" + xValue+",y:" +yValue +",z:" + zValue  );

            if (xValue > 15 || yValue > 15 || zValue > 15) {
                if ((System.currentTimeMillis() - time) > 10000){
                    Toast.makeText(MainActivity.this, "摇一摇", Toast.LENGTH_SHORT).show();
                    time = System.currentTimeMillis();
                    isToast = true;

                }else if(isToast && (System.currentTimeMillis() - time)  > 2000){

                    Snackbar.make(activityMainID, "五秒后再摇一摇", Snackbar.LENGTH_SHORT)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Perform anything for the action selected
                                }
                            })
                            .show();

                    isToast = false;
                }

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


}
