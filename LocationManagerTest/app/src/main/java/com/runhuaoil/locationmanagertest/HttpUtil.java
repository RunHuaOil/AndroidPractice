package com.runhuaoil.locationmanagertest;

import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by RunHua on 2016/10/19.
 */

public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                InputStream inputStream;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Accept-Language", "zh-CN");
                    connection.connect();

                    if (connection.getResponseCode() == 200){
                        inputStream = connection.getInputStream();
                        String jsonData = IOUtils.toString(inputStream,"UTF-8");
                        if (callBack != null){
                            callBack.onFinish(jsonData);
                        }
                    }else{
                        Log.d("Test","connect failed");
                    }

                }catch (Exception e){
                    callBack.onError(e);
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }

            }


        }).start();

    }
}
