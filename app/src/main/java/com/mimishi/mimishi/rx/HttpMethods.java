package com.mimishi.mimishi.rx;

import com.mimishi.mimishi.api.ApiService;
import com.mimishi.mimishi.model.ResourcesMain;
import com.mimishi.mimishi.model.SignedUsers;
import com.mimishi.mimishi.model.VerifyingUsers;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chen on 17-2-6.
 */

public class HttpMethods  {
    public static final String BASE_URL = "https://raw.githubusercontent.com/mimishi705911/Mimishi/master/data/json/";

    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;
    private ApiService mApiService;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mApiService = retrofit.create(ApiService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void getMainData(Subscriber<ResourcesMain> subscriber){
        mApiService.getMainData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    public void getVerifyingUsers(Subscriber<VerifyingUsers> subscriber) {
        mApiService.getVerifyingUsers()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getSignedUsers(Subscriber<SignedUsers> subscriber) {
        mApiService.getSignedUsers()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
