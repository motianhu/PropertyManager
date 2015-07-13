package com.smona.app.propertymanager.wupin;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PropertyWupinfabuActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin_fabu);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_ershouwupin_wupinfabu);
        initText(R.id.detail, R.string.property_ershouwupin_mine);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.wupintype);
        initText(parent, R.id.select_type, R.string.property_ershouwupin_xuanzewupinfenlei);

        parent = mRoot.findViewById(R.id.pinpai);
        initText(parent, R.id.select_type, R.string.property_ershouwupin_pinpaifenlei);

        parent = mRoot.findViewById(R.id.xinjiu);
        initText(parent, R.id.select_type, R.string.property_ershouwupin_xuanzeinjiu);
        
        EditText text = (EditText)mRoot.findViewById(R.id.problem_content);
        text.setText(R.string.property_ershouwupin_wupinfabu_shuru_wupinmiaoshu);
        
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
        case R.id.detail:
            gotoSubActivity(PropertyMineWupinActivity.class);
            break;
        }
    }
}
