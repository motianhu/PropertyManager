package com.smona.app.propertymanager.baoxiu;

import java.util.ArrayList;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PropertyBaoxiudanActivity extends BaseActivity {

    private ListView mBaoxiudans;
    private PropertyBaoxiudansAdapter mBaoxiudansAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyebaoxiu_baoxiudan);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_wuyebaoxiu_baoxiudan);
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

        mBaoxiudans = (ListView) mRoot.findViewById(R.id.list_content);
        ArrayList<ItemInfo> datas = new ArrayList<ItemInfo>();
        for (int i = 0; i < 10; i++) {
            ItemInfo item = new ItemInfo();
            datas.add(item);
        }
        mBaoxiudansAdapter = new PropertyBaoxiudansAdapter(this, datas);
        mBaoxiudans.setAdapter(mBaoxiudansAdapter);
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
