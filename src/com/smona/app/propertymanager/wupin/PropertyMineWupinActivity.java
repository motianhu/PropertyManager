package com.smona.app.propertymanager.wupin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.baoxiu.PropertyWuyebaoxiuMessageProcess;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinHomeContentItem;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PropertyMineWupinActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyMineWupinActivity";

    // content
    private ArrayList<PropertyItemInfo> mDatas = new ArrayList<PropertyItemInfo>();
    private PropertyErshouwupinHomeContentItem mContent;

    // type
    private PropertyErshouwupinTypeItem mTypes;
    private ArrayList<PropertyItemInfo> mPinpaiDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mWupinDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mXinjiuDatas = new ArrayList<PropertyItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin_mine);
        initViews();
        requestLoadData();
    }

    protected void loadData() {
        requestData();
        loadDBData();
    }

    private void requestData() {
        mProcess = new PropertyWuyebaoxiuMessageProcess();
        String content = mProcess.getErshouwupinContent(this);
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyErshouwupinHomeContentItem>() {
        }.getType();
        mContent = JsonUtils.parseJson(content, type);
    }

    private void loadDBData() {
        mTypes = new PropertyErshouwupinTypeItem();
        mTypes.loadDBData(this);
        mPinpaiDatas.addAll(mTypes.pinpais);
        mWupinDatas.addAll(mTypes.wupins);
        mXinjiuDatas.addAll(mTypes.xinjius);

        mDatas.addAll(mContent.icobject);

        requestRefreshUI();
    }

    protected void refreshUI() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_ershouwupin_mine);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.wupintype);
        initTextHint(parent, R.id.select_type,
                R.string.property_ershouwupin_xuanzewupinfenlei);
        initText(parent, R.id.select_type_value,
                R.string.property_ershouwupin_detail_wupinfenlei);
        initView(R.id.wupintype);

        parent = mRoot.findViewById(R.id.pinpai);
        initTextHint(parent, R.id.select_type,
                R.string.property_ershouwupin_pinpaifenlei);
        initText(parent, R.id.select_type_value,
                R.string.property_ershouwupin_detail_pinpai);
        initView(R.id.pinpai);

        parent = mRoot.findViewById(R.id.xinjiu);
        initTextHint(parent, R.id.select_type,
                R.string.property_ershouwupin_xuanzexinjiu);
        initText(parent, R.id.select_type_value,
                R.string.property_ershouwupin_item_xinjiu);
        initView(R.id.xinjiu);

        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        ArrayList<PropertyItemInfo> data = mDatas;
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
        final ArrayList<PropertyItemInfo> datas = mWupinDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.wupintype);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void clickChoiceXinjiu() {
        final ArrayList<PropertyItemInfo> datas = mXinjiuDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.xinjiu);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void clickChoicePinpai() {
        final ArrayList<PropertyItemInfo> datas = mPinpaiDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.pinpai);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }
}
