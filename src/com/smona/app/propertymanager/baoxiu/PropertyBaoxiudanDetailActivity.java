package com.smona.app.propertymanager.baoxiu;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

import android.os.Bundle;
import android.view.View;

public class PropertyBaoxiudanDetailActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyebaoxiu_baoxiudandetail);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_wuyebaoxiu_baoxiudandetail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View view = findViewById(R.id.baoxiu_time);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_time);
        view = findViewById(R.id.baoxiu_type);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_type);
        view = findViewById(R.id.baoxiu_problem);
        initText(view, R.id.name,
                R.string.property_wuyebaoxiu_baoxiuwentimiaoshu);

        view = findViewById(R.id.baoxiu_paidan_time);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_paidan_time);
        view = findViewById(R.id.baoxiu_paidan_weixiuren);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_weixiuren);

        view = findViewById(R.id.baoxiu_wancheng_time);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_wancheng_time);
        view = findViewById(R.id.baoxiu_wancheng_result);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_wancheng_result);
        view = findViewById(R.id.baoxiu_wancheng_fee);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_wancheng_fee);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        }
    }
}
