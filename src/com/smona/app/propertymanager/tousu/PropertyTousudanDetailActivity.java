package com.smona.app.propertymanager.tousu;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;

import android.os.Bundle;
import android.view.View;

public class PropertyTousudanDetailActivity extends PropertyBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_tousujianyi_tousudan_detail);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_tousujianyi_tousu_detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View view = findViewById(R.id.tousu_time);
        initText(view, R.id.name,
                R.string.property_tousujianyi_tousudan_item_time);
        view = findViewById(R.id.tousu_type);
        initText(view, R.id.name,
                R.string.property_tousujianyi_tousudan_item_type);
        view = findViewById(R.id.tousu_problem);
        initText(view, R.id.name,
                R.string.property_tousujianyi_tousuwentimiaoshu);

        view = findViewById(R.id.tousu_wuyefankui);
        initText(view, R.id.name,
                R.string.property_tousujianyi_tousudan_item_wuyefankui);
        view = findViewById(R.id.tousu_fankuishijian);
        initText(view, R.id.name,
                R.string.property_tousujianyi_tousudan_item_fankuishijian);
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
