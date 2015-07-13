package com.smona.app.propertymanager.zulin;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.PropertyItemInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PropertyFangwuzulinActivity extends PropertyBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_fangwuzulin);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_home_fangwuzulin);
        initText(R.id.detail, R.string.property_fangwuzulin_publish_fangyuan);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.ywtype);
        initText(parent, R.id.select_type,
                R.string.property_fangwuzulin_xuanzeyewuleixing);
        initView(R.id.ywtype);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.select_type,
                R.string.property_fangwuzulin_arealeixing);
        initView(R.id.area);

        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.select_type,
                R.string.property_fangwuzulin_xuanzehuxing);
        initView(R.id.housetype);

        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        ArrayList<PropertyItemInfo> data = new ArrayList<PropertyItemInfo>();
        for (int i = 0; i < 10; i++) {
            PropertyItemInfo info = new PropertyItemInfo();
            data.add(info);
        }
        PropertyZulinDetailAdapter adapter = new PropertyZulinDetailAdapter(
                this, data);
        list.setAdapter(adapter);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity(PropertyPublishFangYuanActivity.class);
            break;
        case R.id.ywtype:
            break;
        case R.id.area:
            break;
        case R.id.housetype:
            break;
        }
    }
}
