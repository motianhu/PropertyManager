package com.smona.app.propertymanager.tousu;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

import android.os.Bundle;
import android.view.View;

public class TousudanDetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tousudan_detail);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.tousu_detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
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
