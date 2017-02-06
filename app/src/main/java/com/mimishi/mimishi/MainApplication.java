package com.mimishi.mimishi;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by chen on 17-2-6.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.d("MainApplication", "x5 core load success");
            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });
    }
}
