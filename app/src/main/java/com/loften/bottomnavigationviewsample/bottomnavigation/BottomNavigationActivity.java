package com.loften.bottomnavigationviewsample.bottomnavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.loften.bottomnavigationviewsample.R;
import com.loften.bottomnavigationviewsample.view.DivBottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private OneFragment mOneFragment;
    private TwoFragment mTwoFragment;
    private ThreeFragment mThreeFragment;
    private FourFragment mFourFragment;
    private DivBottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        initView();
        initData();
    }

    private void initView(){
        mBottomNavigationView = (DivBottomNavigationView) findViewById(R.id.navigation_view);
        mBottomNavigationView.SetNormalBottomNavigation();
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.video:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.mine:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.about:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initData(){
        mFragments = new ArrayList<>();
        mOneFragment = new OneFragment();
        mTwoFragment = new TwoFragment();
        mThreeFragment = new ThreeFragment();
        mFourFragment = new FourFragment();
        mFragments.add(mOneFragment);
        mFragments.add(mTwoFragment);
        mFragments.add(mThreeFragment);
        mFragments.add(mFourFragment);
        mAdapter = new ViewPagerAdapter(this, mFragments, getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(0);

        //测试底部导航栏消息通知数目
        mBottomNavigationView.setBadgeValue(1, 1);
        mBottomNavigationView.setBadgeValue(2, 10);
        mBottomNavigationView.setBadgeValue(2, -1);
    }
}
