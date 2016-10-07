package com.runhuaoil.contentresolvertest;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;
    private List<String> contactsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadContact();//载入系统通讯录资料入List
        ListView listView = (ListView) findViewById(R.id.list_view);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
        listView.setAdapter(arrayAdapter);

    }

    private void loadContact() {
        Cursor cursor = null;
        try{
            //Uri为系统已经定义好的常量 ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,null, null);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                //获取本次游标数据的 名字
                String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //获取本次游标数据的 电话号码
                contactsList.add(name  + "\n" + phoneNumber);//add进arrayList
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (cursor != null){
                cursor.close();
            }
        }
    }
}
