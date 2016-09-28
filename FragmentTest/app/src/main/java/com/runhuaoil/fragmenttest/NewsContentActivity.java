package com.runhuaoil.fragmenttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by RunHua on 2016/9/27.
 */

public class NewsContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content_activity_main);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        NewContentFragment newContentFragment = (NewContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);

        newContentFragment.refresh(title, content);

    }

    public static void actionStart(Context context,String title, String content){

        Intent intent = new Intent(context,NewsContentActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }
}
