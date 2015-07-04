package com.smona.app.propertymanager.wupin;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

public class WupinDetailActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ershouwupin_detail);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.ershouwupin_detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.wupinfenlei);
        initText(parent, R.id.name, R.string.ershouwupin_wupinfenlei);
        initText(parent, R.id.value, R.string.ershouwupin_wupinfenlei);

        parent = mRoot.findViewById(R.id.pinpai);
        initText(parent, R.id.name, R.string.ershouwupin_pinpai);
        initText(parent, R.id.value, R.string.ershouwupin_pinpai);

        parent = mRoot.findViewById(R.id.wupinmingchen);
        initText(parent, R.id.name, R.string.ershouwupin_wupinmingchen);
        initText(parent, R.id.value, R.string.ershouwupin_wupinmingchen);
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
