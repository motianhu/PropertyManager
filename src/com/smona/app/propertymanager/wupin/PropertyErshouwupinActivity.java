package com.smona.app.propertymanager.wupin;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.PropertyItemInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PropertyErshouwupinActivity extends PropertyBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_home_ershouwupin);
        initText(R.id.detail, R.string.property_ershouwupin_wupinfabu);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = findViewById(R.id.wupintype);
        initText(parent, R.id.select_type,
                R.string.property_ershouwupin_xuanzewupinfenlei);
        initView(R.id.wupintype);

        parent = findViewById(R.id.pinpai);
        initText(parent, R.id.select_type,
                R.string.property_ershouwupin_pinpaifenlei);
        initView(R.id.pinpai);

        ListView list = (ListView) findViewById(R.id.list_content);
        ArrayList<PropertyItemInfo> data = new ArrayList<PropertyItemInfo>();
        for (int i = 0; i < 10; i++) {
            PropertyItemInfo info = new PropertyItemInfo();
            data.add(info);
        }
        PropertyWupinDetailAdapter adapter = new PropertyWupinDetailAdapter(
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
            gotoSubActivity(PropertyWupinfabuActivity.class);
            break;
        }
    }
}
