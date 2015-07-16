package com.smona.app.propertymanager.zulin;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PropertyFangwuzulinActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyFangwuzulinActivity";

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
            clickSelectType();
            break;
        case R.id.area:
            clickSelectArea();
            break;
        case R.id.housetype:
            clickSelectHuxing();
            break;
        }
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
                View parent = mRoot.findViewById(R.id.ywtype);
                initText(parent, R.id.select_type_value,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void clickSelectHuxing() {
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
                View parent = mRoot.findViewById(R.id.housetype);
                initText(parent, R.id.select_type_value,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void clickSelectArea() {
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
                View parent = mRoot.findViewById(R.id.area);
                initText(parent, R.id.select_type_value,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }
}
