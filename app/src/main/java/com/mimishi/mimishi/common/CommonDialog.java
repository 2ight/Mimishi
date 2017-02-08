package com.mimishi.mimishi.common;

import android.app.Dialog;
import android.content.Context;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.ui.activity.MainActivity;

/**
 * Created by chen on 17-2-8.
 */

public class CommonDialog {

    private static Dialog mProgressBarDialog;
    private static Context mContext;
    private static Dialog mVerifySuccessDialg;
    private static Dialog mVerifyFaildDialg;


    public static void progressBarDialog(Context context){
        mContext = context;

        mProgressBarDialog = new Dialog(context);
        mProgressBarDialog.setTitle("notitle");
        mProgressBarDialog.setContentView(R.layout.dialog_progress_bar);
        mProgressBarDialog.show();

//        mProgressBarDialog.dismiss();
    }

    public static void verifySuccess(Context context) {
        mContext = context;
//        mProgressBarDialog.dismiss();
    }

    public static void verifyFailed(final MainActivity context){
//        mContext = context;
//        mProgressBarDialog.dismiss();

    }

}
