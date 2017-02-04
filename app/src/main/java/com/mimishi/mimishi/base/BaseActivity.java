package com.mimishi.mimishi.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.mimishi.mimishi.utils.ToastUtils;

/**
 * Created by chen on 17-2-4.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    protected abstract int getLayout();
    protected abstract Toolbar getToolbar();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        super.setContentView(getLayout());
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        mToolbar = getToolbar();
        initToolbar();
    }

    private void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void toastMessage(String message) {
        ToastUtils.showMessage(this,message);
    }



}
