package com.runhuaoil.tablayouttest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by RunHua on 2016/10/22.
 */

public class MyFragMent extends Fragment {

    private static int FRAGMENT_TEXT = 0;
    private TextView textView;
    public static final String ARG_OBJECT = "object";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        textView = (TextView) view.findViewById(R.id.textView);

        Bundle bundle = getArguments();
        int data = bundle.getInt(ARG_OBJECT);

        textView.setText("第" + data  + "个");
        return view;
    }


}
