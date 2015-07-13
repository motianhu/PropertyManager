package com.smona.app.propertymanager.zulin;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;

import android.os.Bundle;
import android.view.View;

public class PropertyFangwuzulinDetailActivity extends PropertyBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_fangwuzulin_detail);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_fangwuzulin_fangyuanxinxi);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.ywtype);
        initText(parent, R.id.name,
                R.string.property_fangwuzulin_item_yewuleixing);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_area);

        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_huxing);

        parent = mRoot.findViewById(R.id.peitaomiaoshu);
        initText(parent, R.id.name,
                R.string.property_fangwuzulin_fangyuanfabu_peitaomiaoshu);

        parent = mRoot.findViewById(R.id.position);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_position);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_lianxiren);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_dianhu);

        parent = mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_pulish_time);
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
