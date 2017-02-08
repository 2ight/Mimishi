package com.mimishi.mimishi.api;

import com.mimishi.mimishi.model.ResourcesMain;
import com.mimishi.mimishi.verify.VerifyUsers;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chen on 17-2-6.
 */
public interface ApiService {

    @GET("resources/resource_main_list")
    Observable<ResourcesMain> getMainData();

    @GET("users/user_verifying")
    Observable<VerifyUsers> getVerifyingUsers();
}
