package com.runhuaoil.tablayouttest;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_id) ;

        viewPager = (ViewPager) findViewById(R.id.pager);

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        //tabLayout.setupWithViewPager(viewPager);

    }
}
