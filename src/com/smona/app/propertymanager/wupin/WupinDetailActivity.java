package com.smona.app.propertymanager.wupin;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

public class WupinDetailActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fangwuzulin_detail);
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
        View parent = mRoot.findViewById(R.id.ywtype);
        initText(parent, R.id.name, R.string.yewuleixing);
        initText(parent, R.id.value, R.string.yewuleixing);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.name, R.string.area_leixing);
        initText(parent, R.id.value, R.string.area_xuanze);

        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.name, R.string.house_leixing);
        initText(parent, R.id.value, R.string.house_xuanze);
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
