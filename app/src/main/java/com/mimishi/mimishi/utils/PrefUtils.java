package com.mimishi.mimishi.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mimishi.mimishi.MainApplication;

/**
 * Created by chen on 17-2-7.
 */

public class PrefUtils  {

    private static final String IS_SIGNED = "is_signed";
    private static final String SIGN_TIME_STAMP = "sign_time_stamp";
    private static final String IS_SHOWED_SIGN_DIALOG = "is_showed_sign_dialog";
    private static final String SIGN_SERIAL_NUM = "serial_num";

    private static SharedPreferences getDefaultSP(){
        return PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext());
    }

    public static void setIsSigned(boolean b){
        getDefaultSP().edit().putBoolean(IS_SIGNED, b).apply();
    }
    public static boolean getIsSigned(){
        return getDefaultSP().getBoolean(IS_SIGNED, false);
    }

    public static void setSignTimeStamp(long l){
        getDefaultSP().edit().putLong(SIGN_TIME_STAMP, l).apply();
    }
    public static long getSignTimeStamp(){
        return getDefaultSP().getLong(SIGN_TIME_STAMP, 0);
    }


    public static void setIsShowedSignDialog(boolean b) {
        getDefaultSP().edit().putBoolean(IS_SHOWED_SIGN_DIALOG, b).apply();
    }
    public static boolean getIsShowedSignDialog(){
        return getDefaultSP().getBoolean(IS_SHOWED_SIGN_DIALOG, false);
    }

    public static void setSerialNum(String s) {
        getDefaultSP().edit().putString(SIGN_SERIAL_NUM, s).apply();
    }
    public static String getSerialNum(){
        return getDefaultSP().getString(SIGN_SERIAL_NUM, "");
    }

}
