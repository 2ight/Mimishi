package com.mimishi.mimishi;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 17-2-6.
 */

public class MainApplication extends Application {

    public static Context mContext;
    public static List<Activity> activities = new ArrayList<>();


    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        super.onCreate();
        /*QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.d("MainApplication", "x5 core load success");
            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });*/
    }

    /*
     * 活动管理器
     */
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for(Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }



    public static Context getContext(){
        return mContext;
    }
}
