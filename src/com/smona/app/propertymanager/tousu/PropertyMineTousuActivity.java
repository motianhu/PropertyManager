package com.smona.app.propertymanager.tousu;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;

public class PropertyMineTousuActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_tousujianyi_mine);
        initViews();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_tousujianyi);
        initView(R.id.back);
    }

    protected void initBody() {
        String name = "张三";
        String tel = "(13582426255)";
        String address = "深圳市南山区南山村花好月圆小区五栋502";
        initText(R.id.yezhuxinxi_xingming, name);
        initText(R.id.yezhuxinxi_dianhua, tel);
        initText(R.id.yezhuxinxi_dizhi, address);
        
        
        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        ArrayList<ItemInfo> data = new ArrayList<ItemInfo>();
        for (int i = 0; i < 10; i++) {
            ItemInfo info = new ItemInfo();
            data.add(info);
        }
        PropertyTousudanAdapter adapter = new PropertyTousudanAdapter(this,
                data);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
