package com.smona.app.propertymanager.notify;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.PropertyItemInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PropertyWuyetongzhiActivity extends PropertyBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyetongzhi);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_wuyetongzhi);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        String name = "张三";
        String tel = "(13582426255)";
        String address = "深圳市南山区南山村花好月圆小区五栋502";
        initText(R.id.yezhuxinxi_xingming, name);
        initText(R.id.yezhuxinxi_dianhua, tel);
        initText(R.id.yezhuxinxi_dizhi, address);

        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        ArrayList<PropertyItemInfo> data = new ArrayList<PropertyItemInfo>();
        for (int i = 0; i < 10; i++) {
            PropertyItemInfo info = new PropertyItemInfo();
            data.add(info);
        }
        PropertyNotifyMessageAdapter adapter = new PropertyNotifyMessageAdapter(
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
        }
    }
}
