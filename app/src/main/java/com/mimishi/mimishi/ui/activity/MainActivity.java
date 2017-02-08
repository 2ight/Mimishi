package com.mimishi.mimishi.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.mimishi.mimishi.MainApplication;
import com.mimishi.mimishi.R;
import com.mimishi.mimishi.adapter.MainViewPagerAdapter;
import com.mimishi.mimishi.base.BaseActivity;
import com.mimishi.mimishi.common.CommonDialog;
import com.mimishi.mimishi.model.VerifyingUsers;
import com.mimishi.mimishi.rx.HttpMethods;
import com.mimishi.mimishi.ui.fragment.MainFragment;
import com.mimishi.mimishi.utils.LogUtils;
import com.mimishi.mimishi.utils.PrefUtils;
import com.mimishi.mimishi.verify.VerifyUsers;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;


public class MainActivity extends BaseActivity{

    private Toolbar mToolbar;
    private ViewPager mMainViewPager;
    private TabLayout mTabLayout;
    private long signTimeStamp = 0;
    private Context mContext;
    private EditText etSerialNum;
    private List<VerifyingUsers.UsersList> mUsersList = new ArrayList<>();

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
        MainApplication.addActivity(this);
        mContext = this;
        mMainViewPager = (ViewPager) findViewById(R.id.view_pager_main);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), this);
        viewPagerAdapter.addFragment(new MainFragment());

        mMainViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mMainViewPager);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                checkUsers();
                showSignDialog();
            }
        }, setDelayTime());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               getUsersList();
            }
        }, 2000);

    }

    private void getUsersList() {
        Subscriber subscriber = new Subscriber<VerifyingUsers>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(VerifyingUsers usersList) {
                mUsersList = usersList.list;
            }


        };
        HttpMethods.getInstance().getVerifyingUsers(subscriber);

    }

    public long setDelayTime(){
        if(PrefUtils.getIsShowedSignDialog()){
            return 1000;
        }
        return 60 * 1000 * 0;
    }

    private void checkUsers() {

        if(PrefUtils.getIsSigned()){
            if(PrefUtils.getSignTimeStamp() - signTimeStamp > 1000 * 60 * 30){
                //超过30分钟注册的，可能是第二用户
                //// TODO: 17-2-7 验证signtime 有效性
                showSignDialog();
                PrefUtils.setIsShowedSignDialog(true);
            }
        }else{
            showSignDialog();
            PrefUtils.setIsShowedSignDialog(true);
        }

    }

    public void showSignDialog(){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View inputView = inflater.inflate(R.layout.dialog_input, null);
        etSerialNum = (EditText) inputView.findViewById(R.id.dialog_pwd);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        builder.setTitle("提示")
                .setMessage("系统检测到用户尚未注册， 请输入激活码")
                .setPositiveButton("确定", positiveListener)
                .setNegativeButton("退出应用", exitListener)
                .setView(inputView)
                .show();

    }

    public void verifyUsers(){

        // TODO: 17-2-8 正在验证对话框
        CommonDialog.progressBarDialog(this);
        boolean isValid = VerifyUsers.verifyUsers(mContext, String.valueOf(etSerialNum.getText()), mUsersList);
        if(isValid){
            CommonDialog.verifySuccess();
        }else{
            CommonDialog.verifyFailed();
            showSignDialog();
        }
        // TODO: 17-2-8 验证完成对话框， 公布验证结果，成功则进行一系列配置，失败则重新调回对话框

    }

    private DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if(mUsersList.size() > 0){
                verifyUsers();
            }else{
                LogUtils.i("datalist is empty");
                getUsersList();
            }
        }
    };
    private DialogInterface.OnClickListener exitListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            MainApplication.finishAll();
        }
    };





}
