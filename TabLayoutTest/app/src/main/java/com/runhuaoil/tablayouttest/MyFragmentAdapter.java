package com.runhuaoil.tablayouttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by RunHua on 2016/10/22.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {



    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        MyFragMent fragMent = new MyFragMent();
        Bundle bundle = new Bundle();
        bundle.putInt(MyFragMent.ARG_OBJECT, position + 1);
        fragMent.setArguments(bundle);
        return fragMent;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:

                return "Tab 1";
            case 1:

                return "Tab 2";
            case 2:

                return "Tab 3";
            default:
                return "Tab";

        }
    }
}
