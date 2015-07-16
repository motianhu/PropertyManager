package com.smona.app.propertymanager.wupin;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PropertyWupinfabuActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyWupinfabuActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin_fabu);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_ershouwupin_wupinfabu);
        initText(R.id.detail, R.string.property_ershouwupin_mine);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.wupintype);
        initText(parent, R.id.select_type,
                R.string.property_ershouwupin_xuanzewupinfenlei);
        initView(R.id.wupintype);

        parent = mRoot.findViewById(R.id.pinpai);
        initText(parent, R.id.select_type,
                R.string.property_ershouwupin_pinpaifenlei);
        initView(R.id.pinpai);

        parent = mRoot.findViewById(R.id.xinjiu);
        initText(parent, R.id.select_type,
                R.string.property_ershouwupin_xuanzexinjiu);
        initView(R.id.xinjiu);

        EditText text = (EditText) mRoot.findViewById(R.id.problem_content);
        text.setHint(R.string.property_ershouwupin_wupinfabu_shuru_wupinmiaoshu);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_lianxiren);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_dianhu);

        parent = mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_pulish_time);

    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity(PropertyMineWupinActivity.class);
            break;

        case R.id.wupintype:
            clickChoidWupinType();
            break;
        case R.id.pinpai:
            clickChoicePinpai();
            break;
        case R.id.xinjiu:
            clickChoiceXinjiu();
            break;
        }
    }

    private void clickChoidWupinType() {
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
                View parent = mRoot.findViewById(R.id.wupintype);
                initText(parent, R.id.select_type_value,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void clickChoiceXinjiu() {
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
                View parent = mRoot.findViewById(R.id.xinjiu);
                initText(parent, R.id.select_type_value,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void clickChoicePinpai() {
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
                View parent = mRoot.findViewById(R.id.pinpai);
                initText(parent, R.id.select_type_value,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }
}
