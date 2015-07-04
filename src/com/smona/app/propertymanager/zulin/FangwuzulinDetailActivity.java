package com.smona.app.propertymanager.zulin;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

import android.os.Bundle;
import android.view.View;

public class FangwuzulinDetailActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fangwuzulin);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.chuzu);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = findViewById(R.id.ywtype);
        initText(parent, R.id.select_type_value, R.string.xuanzeyewuleixing);
        initText(parent, R.id.select_type, R.string.yewuleixing);
        
        parent = findViewById(R.id.area);
        initText(parent, R.id.select_type_value, R.string.area_leixing);
        initText(parent, R.id.select_type, R.string.area_xuanze);
        
        parent = findViewById(R.id.housetype);
        initText(parent, R.id.select_type_value, R.string.house_leixing);
        initText(parent, R.id.select_type, R.string.house_xuanze);
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
