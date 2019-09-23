package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.FoodAdapter;
import com.example.myapplication.bean.FoodBean;
import com.example.myapplication.presenter.FoodPresenter;
import com.example.myapplication.view.FoodView;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements FoodView {

    private RecyclerView rl;
    private PullToRefreshLayout prl;
    private FoodPresenter foodPresenter;
    private List<FoodBean.DataBean> list;
    private FoodAdapter foodAdapter;
    private Map<String,String> paramsMap;
    private String baseUrl = "http://www.qubaobei.com/ios/cf/";
    private String url = "dish_list.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        if (foodPresenter == null) {
            foodPresenter = new FoodPresenter(this);
        }
//        http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1
        paramsMap = new HashMap<>();
        paramsMap.put("stage_id","1");
        paramsMap.put("limit","20");
        paramsMap.put("page","2");
        foodPresenter.getFoodData(false,baseUrl,url,paramsMap);
    }


    private void initView() {
        rl = findViewById(R.id.rl);
        prl = findViewById(R.id.prl);
        list = new ArrayList<>();
        foodAdapter = new FoodAdapter(R.layout.food_item_layout, list);
        rl.setAdapter(foodAdapter);
        rl.setLayoutManager(new LinearLayoutManager(this));
        prl.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                paramsMap.put("page","1");
                foodPresenter.getFoodData(true,baseUrl,url,paramsMap);
            }

            @Override
            public void loadMore() {
                String page = paramsMap.get("page");
                int parseInt = Integer.parseInt(page);
                paramsMap.put("page", (parseInt+1)+"");
                foodPresenter.getFoodData(false,baseUrl,url,paramsMap);
            }
        });
    }

    @Override
    public void onSucess(boolean isRefresh, FoodBean foodBean) {
        if (isRefresh) {
            list.clear();
        }
        list.addAll(foodBean.getData());
        foodAdapter.notifyDataSetChanged();
        prl.finishRefresh();
        prl.finishLoadMore();
    }

}
