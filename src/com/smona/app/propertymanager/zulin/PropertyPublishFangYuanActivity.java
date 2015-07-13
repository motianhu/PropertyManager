package com.smona.app.propertymanager.zulin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;

public class PropertyPublishFangYuanActivity extends PropertyBaseActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_fangwuzulin_fabufangyuan);
        initViews();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_fangwuzulin_publish_fangyuan);
        initText(R.id.detail, R.string.property_fangwuzulin_mine);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.ywtype);
        initText(parent, R.id.select_type,
                R.string.property_fangwuzulin_xuanzeyewuleixing);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.select_type,
                R.string.property_fangwuzulin_arealeixing);

        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.select_type,
                R.string.property_fangwuzulin_xuanzehuxing);

        EditText text = (EditText) mRoot.findViewById(R.id.problem_content);
        text.setHint(R.string.property_fangwuzulin_fangyuanfabu_shurupeitaomiaoshu);

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
        case R.id.detail:
            gotoSubActivity(PropertyMineFangyuanActivity.class);
            break;
        }
    }

}
