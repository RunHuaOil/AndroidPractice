package com.runhuaoil.jsonparsetest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private static final int UPDATE_TEXT = 1;
    private HttpURLConnection connection;
    private InputStream inputStream;
    private StringBuilder stringBuilder = new StringBuilder();


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_TEXT:
                    String data = (String) msg.obj;
                    textView.setText(data);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);

    }

    public void SendRequestButton(View view){

        HttpUtil.sendHttpRequest("http://10.0.2.2:8080/AndroidData/get_data.json", new HttpCallBack() {
            @Override
            public void onFinish(String responseData) {
                parseJSONwithJSONObject(responseData);

                //parseJSONwithGSON(responseData);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });





//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    URL url = new URL("http://10.0.2.2:8080/AndroidData/get_data.json");
//
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(8000);
//                    connection.setDoInput(true);
//                    connection.setReadTimeout(8000);
//                    connection.connect();
//                    if (connection.getResponseCode() == 200){
//                        Log.d("Test","success connect");
//                        inputStream = connection.getInputStream();
//                        String jsonData = IOUtils.toString(inputStream,"UTF-8");
//
////                        parseJSONwithJSONObject(jsonData);
//
//                        parseJSONwithGSON(jsonData);
//                    }
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }finally {
//                    if (connection != null){
//                        connection.disconnect();
//                    }
//                }
//
//            }
//
//
//        }).start();
    }

    private void parseJSONwithGSON(String jsonData) {

        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new
                TypeToken<List<App>>() {}.getType());
        for (App app : appList) {
            stringBuilder.append("ID is " + app.getId() + "\nName is " + app.getName() + "\nVersion is " + app.getVersion() + "\n");

        }
        Message message = new Message();
        message.what = UPDATE_TEXT;
        message.obj = stringBuilder.toString();
        handler.sendMessage(message);
    }

    private void parseJSONwithJSONObject(String jsonData) {
        try {


            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                stringBuilder.append(id + "," + name + "," + version + "\n");
            }

            Message message = new Message();
            message.what = UPDATE_TEXT;
            message.obj = stringBuilder.toString();
            handler.sendMessage(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
