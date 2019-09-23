package com.example.myapplication.contract;

import com.example.myapplication.bean.FoodBean;

import java.util.Map;

import io.reactivex.Observer;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface FoodContract {
    interface Model {
        void getFoodData(String baseUrl, @Url String url, @QueryMap Map<String, String> map, Observer<FoodBean> observer);
    }

    interface View {
        void onSucess(boolean isRefresh,FoodBean foodBean);
    }

    interface Presenter {
        void getFoodData(boolean isRefresh, String baseUrl, @Url String url, @QueryMap Map<String, String> map);
    }
}
