package com.mimishi.mimishi.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.adapter.MainViewPagerAdapter;
import com.mimishi.mimishi.base.BaseActivity;
import com.mimishi.mimishi.ui.fragment.MainFragment;

public class MainActivity extends BaseActivity{

    private Toolbar mToolbar;
    private ViewPager mMainViewPager;
    private TabLayout mTabLayout;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Toolbar getToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        return mToolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainViewPager = (ViewPager) findViewById(R.id.view_pager_main);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), this);
        viewPagerAdapter.addFragment(new MainFragment());

        mMainViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mMainViewPager);



    }
}
