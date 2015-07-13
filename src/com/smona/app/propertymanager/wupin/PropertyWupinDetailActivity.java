package com.smona.app.propertymanager.wupin;

import android.os.Bundle;
import android.view.View;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

public class PropertyWupinDetailActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin_detail);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_ershouwupin_wupin_detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.wupinfenlei);
        initText(parent, R.id.name, R.string.property_ershouwupin_detail_wupinfenlei);

        parent = mRoot.findViewById(R.id.pinpai);
        initText(parent, R.id.name, R.string.property_ershouwupin_detail_pinpai);

        parent = mRoot.findViewById(R.id.wupinmingchen);
        initText(parent, R.id.name, R.string.property_ershouwupin_wupinmingchen);
        
        parent = mRoot.findViewById(R.id.wupinmiaoshu);
        initText(parent, R.id.name, R.string.property_ershouwupin_wupinfabu_wupinmiaoshu);
        
        parent = mRoot.findViewById(R.id.xinjiu);
        initText(parent, R.id.name, R.string.property_ershouwupin_xuanzeinjiu);
        
        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.name, R.string.property_ershouwupin_wupinfabu_lianxiren);
        
        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.name, R.string.property_ershouwupin_wupinfabu_dianhu);
        
        parent= mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.name, R.string.property_ershouwupin_item_pulish_time);
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
