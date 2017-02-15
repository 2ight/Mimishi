package com.mimishi.mimishi.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.mimishi.mimishi.model.SignedUsers;
import com.mimishi.mimishi.model.VerifyingUsers;
import com.mimishi.mimishi.rx.HttpMethods;
import com.mimishi.mimishi.ui.fragment.MainFragment;
import com.mimishi.mimishi.update.SearchUpdate;
import com.mimishi.mimishi.utils.LogUtils;
import com.mimishi.mimishi.utils.PrefUtils;
import com.mimishi.mimishi.utils.ToastUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;


public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private ViewPager mMainViewPager;
    private TabLayout mTabLayout;
    private long signTimeStamp = System.currentTimeMillis();
    private Context mContext;
    private EditText etSerialNum;
    private List<VerifyingUsers.UsersList> mUsersList = new ArrayList<>();
    private List<SignedUsers.ItemList> mSignUsersList = new ArrayList<>();
//    private AlertDialog mProgressDialog;
    private Handler mHandler;
    private ProgressDialog mProgressDialog;
    public static String TYPE_FRAGMENT_VIDEO = "type_fragment_video";

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

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        mContext = this;
        mMainViewPager = (ViewPager) findViewById(R.id.view_pager_main);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), this);
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE_FRAGMENT_VIDEO, 1);
//        viewPagerAdapter.addFragment(MainFragment.initFragmentType(1));
        viewPagerAdapter.addFragment(new MainFragment(bundle));
        bundle.putInt(TYPE_FRAGMENT_VIDEO, 2);
        viewPagerAdapter.addFragment(new MainFragment(bundle));
        bundle.putInt(TYPE_FRAGMENT_VIDEO, 1);
        viewPagerAdapter.addFragment(new MainFragment(bundle));
        bundle.putInt(TYPE_FRAGMENT_VIDEO, 2);
        viewPagerAdapter.addFragment(new MainFragment(bundle));
        bundle.putInt(TYPE_FRAGMENT_VIDEO, 1);
        viewPagerAdapter.addFragment(new MainFragment(bundle));

        mMainViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mMainViewPager);

        LogUtils.i(String.valueOf(System.currentTimeMillis()));

        if (!PrefUtils.getIsSigned()) {
            if(PrefUtils.getIsShowedSignDialog()){
                showSignDialog();
            }else{
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showSignDialog();
                    }
                }, 1000 * 60 * 0);
            }
        }
        ToastUtils.showMessage(this, "new version");

        checkUpdate();
    }

    private void checkUpdate() {
        int versionCode = 3;
        PackageManager pm = this.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(this.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        SearchUpdate searchUpdate = new SearchUpdate(this);
        searchUpdate.checkUpdate(versionCode, 2000);

    }

    private void getUsersList() {
        Subscriber subscriber = new Subscriber<VerifyingUsers>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                showFailedDialog();
            }

            @Override
            public void onNext(VerifyingUsers usersList) {
                mUsersList = usersList.list;
                mHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 1:
                                mProgressDialog.dismiss();
                                showSuccessDialog();
                                break;
                            case 0:
                                mProgressDialog.dismiss();
                                showFailedDialog();
                                break;
                        }

                    }
                };

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        LogUtils.i(String.valueOf(etSerialNum.getText()));
                        boolean isValid = false;
                        for (int i = 0; i < mUsersList.size(); i++) {
                            LogUtils.i(String.valueOf(mUsersList.get(i).serial_num));
                            if (mUsersList.get(i).serial_num.equals(String.valueOf(etSerialNum.getText()))) {
                                LogUtils.i("检测到该激活码");
                                LogUtils.i(String.valueOf(System.currentTimeMillis()), String.valueOf(mUsersList.get(i).sign_time));
                                if ((System.currentTimeMillis() - mUsersList.get(i).sign_time) < 1000 * 60 * 20) {
                                    isValid = true;
                                    break;
                                }
                            }
                        }
                        if (isValid) {
                            msg.what = 1;

                        } else {
                            msg.what = 0;

                        }
                        mHandler.sendMessage(msg);
                    }
                }).start();


            }


        };
        HttpMethods.getInstance().getVerifyingUsers(subscriber);
    }

    private void showProgressDialog() {

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在验证，请稍后...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

    }

    public void showSignDialog() {
        PrefUtils.setIsShowedSignDialog(true);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View inputView = inflater.inflate(R.layout.dialog_input, null);
        etSerialNum = (EditText) inputView.findViewById(R.id.dialog_pwd);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        builder.setTitle("提示")
                .setMessage("系统检测到用户尚未注册， 请输入激活码.\n如需获取激活码请联系QQ179727984管理员3元永久")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showProgressDialog();
                        getUsersList();
                    }
                })
                .setNegativeButton("退出应用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainApplication.finishAll();
                    }
                })
                .setView(inputView)
                .show();
        builder.create();

    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("验证结果").setMessage("验证成功！")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PrefUtils.setIsSigned(true);
                        PrefUtils.setSignTimeStamp(System.currentTimeMillis());
                        PrefUtils.setSerialNum(etSerialNum.getText().toString());
                    }
                }).show();
    }

    private void showFailedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("验证结果").setMessage("验证失败！")
                .setCancelable(false)
                .setPositiveButton("重新验证", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showSignDialog();
                    }
                })
                .show();
    }


}
