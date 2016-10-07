package com.runhuaoil.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String newId = "1";
    private Uri uriBook;
    private Uri uriBookId ;
    private ContentValues values;
    private Cursor cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         uriBook = Uri.parse("content://com.runhuaoil.androidsqlite.provider/Book");
         uriBookId = Uri.parse("content://com.runhuaoil.androidsqlite.provider/Book/" + newId);



        Button updateData = (Button) findViewById(R.id.update_data);
        Button addData = (Button) findViewById(R.id.add_data);
        Button queryData = (Button) findViewById(R.id.query_data);
        Button deleteData = (Button) findViewById(R.id.delete_data);

        updateData.setOnClickListener(this);
        addData.setOnClickListener(this);
        queryData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        values = new ContentValues();
        switch (v.getId()){
            case R.id.update_data:

                values.put("name","第一行代码");
                values.put("pages", 555);
                values.put("price", 70);
                values.put("author", "郭霖");

                getContentResolver().update(uriBookId, values, null, null);
                values.clear();
                break;
            case R.id.add_data:

                values.put("name","第一行代码");
                values.put("pages", 555);
                values.put("price", 60);
                values.put("author", "郭霖");

                Uri newUri = getContentResolver().insert(uriBook, values);
                newId = newUri.getPathSegments().get(1);
                values.clear();
                break;
            case R.id.query_data:
                cursor = getContentResolver().query(uriBook, null, null ,null,null);
                if (cursor != null){
                    while (cursor.moveToNext()){
                        Log.d("Test", "ID:" + cursor.getInt(cursor.getColumnIndex("id")));
                        Log.d("Test", "Name:" + cursor.getString(cursor.getColumnIndex("name")));
                        Log.d("Test", "Author:" + cursor.getString(cursor.getColumnIndex("author")));
                        Log.d("Test", "Price:" + cursor.getDouble(cursor.getColumnIndex("price")) );
                        Log.d("Test", "Pages:" + cursor.getInt(cursor.getColumnIndex("pages")) );
                    }
                    cursor.close();
                }

                break;
            case R.id.delete_data:
                getContentResolver().delete(uriBookId, null,null);
                break;
            default:
                break;
        }
    }
}
