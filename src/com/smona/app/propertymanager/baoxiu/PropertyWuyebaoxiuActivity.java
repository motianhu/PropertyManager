package com.smona.app.propertymanager.baoxiu;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.bean.PropertyWuyebaoxiuBean;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;

public class PropertyWuyebaoxiuActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyWuyebaoxiuActivity";
    private PropertyWuyebaoxiuMessageProcess mProcess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyebaoxiu);
        initViews();
        testData();
    }

    private void testData() {
        mProcess = new PropertyWuyebaoxiuMessageProcess();
        String content = mProcess.getWuyebaoxiuContent(this);
        LogUtil.d(TAG, "content: " + content);
        PropertyWuyebaoxiuBean bean = new PropertyWuyebaoxiuBean();
        Type type = new TypeToken<PropertyWuyebaoxiuBean>() {
        }.getType();
        bean = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "bean: " + bean);
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

        initView(R.id.select_type_container);
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
