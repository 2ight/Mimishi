package com.mimishi.mimishi.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.mimishi.mimishi.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 16-11-29.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> fragments = new ArrayList<>();
    private String tabTitles[] = new String[]{"每日精选", "笑话", "图片", "动图","视频"};

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    public MainViewPagerAdapter(FragmentManager fm, MainActivity context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }



}
