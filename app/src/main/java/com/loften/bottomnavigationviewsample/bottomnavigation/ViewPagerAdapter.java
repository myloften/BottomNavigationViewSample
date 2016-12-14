package com.loften.bottomnavigationviewsample.bottomnavigation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lcw on 2016/10/30.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    Context ctx;
    private List<Fragment> mFragments;
    private String[] mTitles;
    private FragmentManager mFragmentManager;

    public ViewPagerAdapter(Context ctx, List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        this.ctx = ctx;
        this.mFragments = fragments;
        this.mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void setTitles(String[] titles){
        this.mTitles = mTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(mTitles != null){
            return mTitles[position];
        }
        return super.getPageTitle(position);
    }
}
