package com.runhuaoil.uicustomview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by RunHua on 2016/9/25.
 */

public class TitleLayout extends LinearLayout implements View.OnClickListener {


    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.titile,this);

        Button button_back = (Button)findViewById(R.id.button_back);
        Button button_edit = (Button)findViewById(R.id.button_edit);
        button_back.setOnClickListener(this);
        button_edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_back:
                ((Activity)getContext()).finish();
                break;
            case R.id.button_edit:
                Toast.makeText(getContext(),"Edit",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
