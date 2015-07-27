package com.smona.app.propertymanager.wupin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.bean.PropertyBeanErshouwupinpinpais;
import com.smona.app.propertymanager.data.bean.PropertyBeanErshouwupinwupins;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinHomeContentItem;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinMessageProcessProxy;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinPinpaiRequestInfo;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinRequestInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PropertyErshouwupinActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyErshouwupinActivity";

    // content
    private PropertyWupinDetailAdapter mAdapter;
    private ArrayList<PropertyItemInfo> mDatas = new ArrayList<PropertyItemInfo>();
    private PropertyErshouwupinHomeContentItem mContent;

    // type
    private PropertyErshouwupinTypeItem mTypes;
    private ArrayList<PropertyItemInfo> mPinpaiDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mWupinDatas = new ArrayList<PropertyItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin);
        initViews();
        requestLoadData();
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        initConent();
        initWupinTypes();
        showCustomProgrssDialog();
    }

    private void initConent() {
        mProcess = new PropertyErshouwupinMessageProcessProxy();

        mRequestInfo = new PropertyErshouwupinRequestInfo();
        ((PropertyErshouwupinRequestInfo) mRequestInfo).pageno = "1";
        ((PropertyErshouwupinRequestInfo) mRequestInfo).pageSize = "12";
        ((PropertyErshouwupinMessageProcessProxy) mProcess).requestErshouwupin(
                this, mRequestInfo, this);
    }

    private void initWupinTypes() {
        ((PropertyErshouwupinMessageProcessProxy) mProcess)
                .requestErshouwupinXinjiuType(this, this);
    }
    

    private void initPinpaiTypes(String classCode) {
        PropertyErshouwupinPinpaiRequestInfo request = new PropertyErshouwupinPinpaiRequestInfo();
        request.classcode = classCode;
        ((PropertyErshouwupinMessageProcessProxy) mProcess)
                .requestErshouwupinPinpaiType(this, request, this);
        showCustomProgrssDialog();
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "info.iccode: " + info.iccode + "; content: " + content);

        if ("4710".equals(info.iccode)) {
            type = new TypeToken<PropertyErshouwupinHomeContentItem>() {
            }.getType();
            mContent = JsonUtils.parseJson(content, type);
            loadListData();
        } else if ("4910".equals(info.iccode)) {
            type = new TypeToken<PropertyBeanErshouwupinpinpais>() {
            }.getType();
            PropertyBeanErshouwupinpinpais bean = JsonUtils.parseJson(content,
                    type);
            bean.saveDataToDB(this);
            loadTypeData();
        } else if ("4810".equals(info.iccode)) {
            LogUtil.d(TAG, "1content: " + content);
            type = new TypeToken<PropertyBeanErshouwupinwupins>() {
            }.getType();
            PropertyBeanErshouwupinwupins wupin = JsonUtils.parseJson(content,
                    type);
            wupin.saveDataToDB(this);
            loadTypeData();
        }
        
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    private void loadTypeData() {
        mTypes = new PropertyErshouwupinTypeItem();
        mTypes.loadDBData(this);
        mPinpaiDatas.clear();
        mWupinDatas.clear();

        mPinpaiDatas.addAll(mTypes.pinpais);
        mWupinDatas.addAll(mTypes.wupins);
    }

    private void loadListData() {
        mDatas.addAll(mContent.icobject);
        requestRefreshUI();
    }

    protected void refreshUI() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_home_ershouwupin);
        initText(R.id.detail, R.string.property_ershouwupin_wupinfabu);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = findViewById(R.id.wupintype);
        initTextHint(parent, R.id.select_type,
                R.string.property_ershouwupin_xuanzewupinfenlei);
        initText(parent, R.id.select_type_value,
                R.string.property_ershouwupin_detail_wupinfenlei);
        initView(R.id.wupintype);

        parent = findViewById(R.id.pinpai);
        initTextHint(parent, R.id.select_type,
                R.string.property_ershouwupin_pinpaifenlei);
        initText(parent, R.id.select_type_value,
                R.string.property_ershouwupin_detail_pinpai);
        initView(R.id.pinpai);

        ListView list = (ListView) findViewById(R.id.list_content);
        ArrayList<PropertyItemInfo> data = mDatas;
        mAdapter = new PropertyWupinDetailAdapter(this, data);
        list.setAdapter(mAdapter);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity(PropertyWupinfabuActivity.class);
            break;

        case R.id.wupintype:
            clickChoidWupinType();
            break;
        case R.id.pinpai:
            clickChoicePinpai();
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
                setTag(parent, R.id.select_type, info);
                initPinpaiTypes(((PropertyTypeItem) info).type_id);
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
                setTag(parent, R.id.select_type, info);
            }
        });
    }
}
