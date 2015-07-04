package com.smona.app.propertymanager.wupin;

import java.util.ArrayList;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ErshouwupinActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ershouwupin);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.wupin);
        initText(R.id.detail, R.string.ershouwupin_wupinfabu);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = findViewById(R.id.wupintype);
        initText(parent, R.id.select_type_value,
                R.string.ershouwupin_wupinfenlei);
        initText(parent, R.id.select_type,
                R.string.ershouwupin_xuanzewupinfenlei);
        initView(R.id.wupintype);

        parent = findViewById(R.id.pinpai);
        initText(parent, R.id.select_type_value, R.string.ershouwupin_pinpai);
        initText(parent, R.id.select_type, R.string.ershouwupin_pinpaifenlei);
        initView(R.id.pinpai);

        ListView list = (ListView) findViewById(R.id.list_content);
        ArrayList<ItemInfo> data = new ArrayList<ItemInfo>();
        for (int i = 0; i < 10; i++) {
            ItemInfo info = new ItemInfo();
            data.add(info);
        }
        WupinDetailAdapter adapter = new WupinDetailAdapter(this, data);
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
            gotoSubActivity(WupinfabuActivity.class);
            break;
        }
    }
}
