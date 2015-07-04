package com.smona.app.propertymanager.zulin;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

public class PublishFangYuanActivity extends BaseActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fangwuzulin_fabufangyuan);
        initViews();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.publish_fangyuan);
        initText(R.id.detail, R.string.mine_fangyuan);
        initView(R.id.detail);
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
