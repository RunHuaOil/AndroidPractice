package com.runhuaoil.viewpagertest;


import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            //设置导航模式 显示 Tabs
            ActionBar.TabListener tabListener = new ActionBar.TabListener() {
                @Override
                public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

                }

                @Override
                public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

                }
            };


            for (int i = 0; i < adapter.getCount(); i++) {
                actionBar.addTab(actionBar.newTab()
                                .setIcon(R.mipmap.ic_launcher)
                                .setText(adapter.getPageTitle(i))
                                .setTabListener(tabListener));
            }


        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
