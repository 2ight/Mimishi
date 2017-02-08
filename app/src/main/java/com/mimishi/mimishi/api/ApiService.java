package com.mimishi.mimishi.api;

import com.mimishi.mimishi.model.ResourcesMain;
import com.mimishi.mimishi.model.SignedUsers;
import com.mimishi.mimishi.model.VerifyingUsers;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chen on 17-2-6.
 */
public interface ApiService {

    @GET("resources/resource_main_list")
    Observable<ResourcesMain> getMainData();

    @GET("users/user_verifying")
    Observable<VerifyingUsers> getVerifyingUsers();

    @GET("users/user_signed")
    Observable<SignedUsers> getSignedUsers();
}
