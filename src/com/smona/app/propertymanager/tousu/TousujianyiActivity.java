package com.smona.app.propertymanager.tousu;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

import android.os.Bundle;
import android.view.View;

public class TousujianyiActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tousujianyi);
        initViews();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_tousujianyi);
        initText(R.id.detail, R.string.tousudandetail);
        initText(R.id.select_type, R.string.xuanzetousuleixing);
        
        initText(R.id.action_now, R.string.liketousu);
        initView(R.id.back);
        initView(R.id.detail);
    }

    protected void initBody() {

    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity(MineTousuActivity.class);
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
