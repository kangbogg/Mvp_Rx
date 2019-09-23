package com.example.myapplication.api;

import com.example.myapplication.bean.FoodBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface FoodApi {

    @GET
    Observable<FoodBean> getData(@Url String url, @QueryMap Map<String, String> map);
}
