package com.example.myapplication.presenter;

import android.util.Log;

import com.example.myapplication.bean.FoodBean;
import com.example.myapplication.contract.FoodContract;
import com.example.myapplication.model.FoodModel;
import com.example.myapplication.view.FoodView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FoodPresenter implements FoodContract.Presenter {

    private FoodView view;
    private FoodModel model;
    private boolean isRefresh;
    private static final String TAG = "####";

    public FoodPresenter(FoodView view) {
        this.view = view;
        model = FoodModel.getInstance();
    }

    private Observer<FoodBean> observer = new Observer<FoodBean>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(FoodBean foodBean) {
            for (FoodBean.DataBean datum : foodBean.getData()) {
                Log.d(TAG, "onNext: datum"+datum.toString());
            }
            view.onSucess(isRefresh,foodBean);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };


    @Override
    public void getFoodData(boolean isRefresh, String baseUrl, String url, Map<String, String> map) {
        this.isRefresh = isRefresh;
        model.getFoodData(baseUrl, url, map, observer);
        Log.d(TAG, "FoodPresenter   getFoodData: ");
    }
}
