package com.runhuaoil.androidsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySQLiteHelper = new MySQLiteHelper(this,"BookStore.db", null, 2);
        Button create_button = (Button) findViewById(R.id.button_create);

        Button insert_button = (Button) findViewById(R.id.button_insert);
        Button update_button = (Button) findViewById(R.id.button_update);
        Button delete_button = (Button) findViewById(R.id.button_delete);
        Button query_button = (Button) findViewById(R.id.button_query);
        Button transaction_button = (Button) findViewById(R.id.button_transaction_test);


        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySQLiteHelper.getWritableDatabase();
            }
        });

        transaction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
                sqLiteDatabase.beginTransaction();
                try{
                    sqLiteDatabase.delete("Book",null,null);
//                    if (true){
//                        throw new NullPointerException();//手动抛出一个异常,则删除Book的操作会撤销，也不会添加数据
//                    }
                    ContentValues values = new ContentValues();
                    values.put("author", "王爽");
                    values.put("price", 60);
                    values.put("pages", 555);
                    values.put("name", "汇编语言");
                    sqLiteDatabase.insert("Book", null , values);
                    values.clear();


                    sqLiteDatabase.setTransactionSuccessful();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    sqLiteDatabase.endTransaction();
                }
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
                sqLiteDatabase.delete("Book", "id = ?", new String[]{"1"});
                Toast.makeText(MainActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
            }
        });

        query_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
                Cursor cursor = sqLiteDatabase.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Toast.makeText(MainActivity.this, name+","+author + ",页数:" + pages + ",RMB:" + price, Toast.LENGTH_SHORT).show();
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        insert_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("author", "郭霖");
                values.put("price", 60);
                values.put("pages", 555);
                values.put("name", "第一行代码");
                sqLiteDatabase.insert("Book", null , values);
                values.clear();
                Toast.makeText(MainActivity.this, "Insert Success", Toast.LENGTH_SHORT).show();
            }
        });

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = mySQLiteHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("pages", "600");
                sqLiteDatabase.update("Book", values, "name = ?", new String[]{"第一行代码"});
                values.clear();
                Toast.makeText(MainActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
