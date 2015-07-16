package com.smona.app.propertymanager.tousu;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PropertyTousujianyiActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyTousujianyiActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_tousujianyi);
        initViews();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_tousujianyi);
        initText(R.id.detail, R.string.property_tousudan_detail);
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

        initText(R.id.select_type,
                R.string.property_tousujianyi_xuanzetousuleixing);
        initText(R.id.action_now, R.string.property_tousujianyi_action_tousu);

        EditText text = (EditText) mRoot.findViewById(R.id.problem_content);
        text.setHint(R.string.property_tousujianyi_tousuwentimiaoshu);
        
        initView(R.id.select_type_container);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity(PropertyMineTousuActivity.class);
            break;
        case R.id.select_type_container:
            clickSelectType();
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
    private void clickSelectType() {
        final ArrayList<PropertyItemInfo> datas = new ArrayList<PropertyItemInfo>();
        for (int i = 0; i < 100; i++) {
            PropertyTypeItem item = new PropertyTypeItem();
            item.type_id = i + "";
            item.type_name = "item " + i;
            datas.add(item);
        }

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                initText(R.id.select_type_value,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }
}
