package com.smona.app.propertymanager.baoxiu;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.PropertyMessageProcess;
import com.smona.app.propertymanager.R;

import android.os.Bundle;
import android.view.View;

public class PropertyWuyebaoxiuActivity extends PropertyBaseActivity {
    private PropertyMessageProcess mProcess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyebaoxiu);
        initViews();
        testData();
    }

    private void testData() {
        mProcess = new PropertyMessageProcess();
        mProcess.requestWuyebaoxiu();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_wuyebaoxiu);
        initText(R.id.detail, R.string.property_wuyebaoxiu_baoxiudandetail);
        initView(R.id.back);
        initView(R.id.detail);
    }

    protected void initBody() {
        String name = "张三";
        String tel = "(13582426255)";
        String address = "深圳市南山区南山村花好月圆小区五栋502";
        initText(R.id.yezhuxinxi_xingming, name);
        initText(R.id.yezhuxinxi_dianhua, tel);
        initText(R.id.yezhuxinxi_dizhi, address);

        initText(R.id.select_type, R.string.property_common_xuanzebaoxiuleixing);

        initText(R.id.action_now,
                R.string.property_wuyebaoxiu_now_action_baoxiu);
    }

    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity(PropertyBaoxiudanActivity.class);
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
