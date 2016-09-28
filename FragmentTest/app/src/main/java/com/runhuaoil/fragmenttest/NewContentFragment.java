package com.runhuaoil.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.runhuaoil.fragmenttest.R;

import org.w3c.dom.Text;

/**
 * Created by RunHua on 2016/9/27.
 */

public class NewContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.new_content_fragment_layout,container,false);

        return view;
    }

    public void refresh(String title, String content){

        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);

        TextView titleText = (TextView) view.findViewById(R.id.news_title);
        TextView contentText = (TextView) view.findViewById(R.id.news_content);

        titleText.setText(title);
        contentText.setText(content);

    }

}
