package com.mimishi.mimishi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chen on 16-12-1.
 */

public class TimeStampUtils {

    //showapi参数：timestamp
    public static String getCurrentTimeStampOfShowApi(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        LogUtils.i(dateFormat.format(new Date(System.currentTimeMillis())) + "");
        return dateFormat.format(new Date(System.currentTimeMillis())) + "";
    }

}
