package com.mimishi.mimishi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.base.BaseActivity;

public class MainActivity extends BaseActivity{

    private Toolbar mToolbar;
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



    }
}
