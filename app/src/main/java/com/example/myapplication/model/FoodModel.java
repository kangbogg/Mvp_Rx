package com.example.myapplication.model;

import android.util.Log;

import com.example.myapplication.api.FoodApi;
import com.example.myapplication.bean.FoodBean;
import com.example.myapplication.contract.FoodContract;
import com.example.myapplication.util.RxUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FoodModel implements FoodContract.Model {

    private static volatile FoodModel singleton;
    private static final String TAG = "####";

    private FoodModel() {
    }

    public static FoodModel getInstance() {
        if (singleton == null) {
            synchronized (FoodModel.class) {
                if (singleton == null) {
                    singleton = new FoodModel();
                }
            }
        }
        return singleton;
    }

    @Override
    public void getFoodData(String baseUrl, String url, Map<String, String> map, Observer<FoodBean> observer) {
        RxUtil.getInstance()
                .getApi(baseUrl, FoodApi.class)
                .getData(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.d(TAG, "FoodModel       getFoodData: ");
    }
}