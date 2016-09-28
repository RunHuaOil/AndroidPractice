package com.runhuaoil.locationchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Msg> msgList = new ArrayList<Msg>();
    private ListView listView;
    private MsgAdapter msgAdapter;

    private EditText editText;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsg();

        msgAdapter = new MsgAdapter(this,R.layout.list_item,msgList);

        listView = (ListView) findViewById(R.id.list_view_id);
        editText = (EditText) findViewById(R.id.edit_text);
        buttonSend = (Button) findViewById(R.id.button_send);
        listView.setAdapter(msgAdapter);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (!"".equals(text)){

                    Msg msg = new Msg(text,Msg.MSG_SEND);
                    msgList.add(msg);
                    msgAdapter.notifyDataSetChanged();

                    listView.setSelection(msgList.size());

                    editText.setText("");
                }
            }
        });



    }

    private void initMsg() {

        Msg msg1 = new Msg("Hello",Msg.MSG_RECEIVE);
        msgList.add(msg1);

        Msg msg2 = new Msg("Hi",Msg.MSG_SEND);
        msgList.add(msg2);

        Msg msg3 = new Msg("你好",Msg.MSG_RECEIVE);
        msgList.add(msg3);

    }
}
