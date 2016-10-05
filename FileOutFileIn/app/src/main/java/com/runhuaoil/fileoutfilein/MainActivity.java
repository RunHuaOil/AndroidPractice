package com.runhuaoil.fileoutfilein;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);

        String textE = load();
        if (! TextUtils.isEmpty(textE)){
            editText.setText(textE);
            editText.setSelection(textE.length());
            Toast.makeText(this, "数据已经加载完毕", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String text = editText.getText().toString();
        if ( ! TextUtils.isEmpty(text) ){
            save(text);
        }
    }

    public String load(){
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        String line = "";
        try {
            inputStream = openFileInput("EditTextData");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while( (line = reader.readLine() ) != null){
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }


    public void save(String text){

        FileOutputStream out = null;
        BufferedWriter writer = null;

        try {
            out = openFileOutput("EditTextData", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null){
                    writer.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
