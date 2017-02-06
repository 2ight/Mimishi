package com.mimishi.mimishi.api;

import com.mimishi.mimishi.model.ResourcesMain;

import java.util.Observable;

import retrofit2.http.GET;

/**
 * Created by chen on 17-2-6.
 */
public interface ApiService {

    @GET("resources/resource_main_list")
    rx.Observable<ResourcesMain> getMainData();

}
