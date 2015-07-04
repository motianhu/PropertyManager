package com.smona.app.propertymanager.wupin;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

import android.os.Bundle;
import android.view.View;

public class WupinfabuActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ershouwupin_fabu);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.ershouwupin_wupinfabu);
        initText(R.id.detail, R.string.ershouwupin_mine);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.wupintype);
        initText(parent, R.id.select_type, R.string.yewuleixing);
        initText(parent, R.id.select_type_value, R.string.yewuleixing);

        parent = mRoot.findViewById(R.id.pinpai);
        initText(parent, R.id.select_type, R.string.area_leixing);
        initText(parent, R.id.select_type_value, R.string.area_xuanze);

        parent = mRoot.findViewById(R.id.xinjiu);
        initText(parent, R.id.select_type, R.string.house_leixing);
        initText(parent, R.id.select_type_value, R.string.house_xuanze);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity(MineWupinActivity.class);
            break;
        }
    }
}
