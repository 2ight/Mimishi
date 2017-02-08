package com.mimishi.mimishi.common;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.mimishi.mimishi.R;
import com.mimishi.mimishi.utils.PrefUtils;

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

    public static void verifySuccess() {
        mProgressBarDialog.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder
                .setTitle("验证结果")
                .setMessage("验证成功！")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PrefUtils.setIsSigned(true);
                        PrefUtils.setSignTimeStamp(System.currentTimeMillis());
                    }
                })
                .show();
    }

    public static void verifyFailed(){
        mProgressBarDialog.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("验证结果")
                .setMessage("验证成功！")
                .setCancelable(false)
                .setPositiveButton("重新验证", null)
                .show();

    }

}
