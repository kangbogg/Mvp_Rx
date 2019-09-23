package com.example.myapplication.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.bean.FoodBean;

import java.util.List;

public class FoodAdapter extends BaseQuickAdapter<FoodBean.DataBean, BaseViewHolder> {

    public FoodAdapter(int layoutResId, @Nullable List<FoodBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FoodBean.DataBean item) {
        helper.setText(R.id.tv_show_title, item.getTitle());
    }
}
