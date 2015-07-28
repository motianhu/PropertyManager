package com.smona.app.propertymanager.wupin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.bean.PropertyBeanErshouwupinpinpais;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinHomeContentItem;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.source.listview.XListView;
import com.smona.app.propertymanager.source.listview.XListView.IXListViewListener;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinMessageProcessProxy;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinPinpaiRequestInfo;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinRequestInfo;

import android.os.Bundle;
import android.view.View;

public class PropertyMineWupinActivity extends PropertyBaseActivity implements
        IXListViewListener {
    private static final String TAG = "PropertyMineWupinActivity";

    // content
    private XListView mList;
    private PropertyWupinDetailAdapter mAdapter;
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
        loadTypeData();
        showCustomProgrssDialog();
    }

    private void requestData() {
        mProcess = new PropertyErshouwupinMessageProcessProxy();
        mRequestInfo = new PropertyErshouwupinRequestInfo();
        ((PropertyErshouwupinRequestInfo) mRequestInfo).pageno = "1";
        ((PropertyErshouwupinRequestInfo) mRequestInfo).pageSize = "12";
        ((PropertyErshouwupinMessageProcessProxy) mProcess)
                .requestErshouwupinMine(this, mRequestInfo, this);
    }

    protected void saveData(String content) {
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyErshouwupinHomeContentItem>() {
        }.getType();

        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "info.iccode: " + info.iccode + "; content: " + content);

        if ("4710".equals(info.iccode)) {
            mContent = JsonUtils.parseJson(content, type);
            loadDBData();
        } else if ("4910".equals(info.iccode)) {
            PropertyBeanErshouwupinpinpais bean = JsonUtils.parseJson(content,
                    type);
            bean.saveDataToDB(this);
            loadPinpaiTypeData();
        }
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    private void loadTypeData() {
        mTypes = new PropertyErshouwupinTypeItem();
        mTypes.loadDBData(this);
        mWupinDatas.addAll(mTypes.wupins);
        mXinjiuDatas.addAll(mTypes.xinjius);
    }

    private void loadPinpaiTypeData() {
        mPinpaiDatas.clear();
        mTypes.loadPinpais(this);
        mPinpaiDatas.addAll(mTypes.pinpais);
    }

    private void loadDBData() {
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

        mList = (XListView) mRoot.findViewById(R.id.list_content);
        ArrayList<PropertyItemInfo> data = mDatas;
        mAdapter = new PropertyWupinDetailAdapter(this, data);
        mList.setAdapter(mAdapter);
        mList.setPullRefreshEnable(true);
        mList.setPullLoadEnable(true);
        mList.setXListViewListener(this);

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

    private void initPinpaiTypes(String classCode) {
        PropertyErshouwupinPinpaiRequestInfo request = new PropertyErshouwupinPinpaiRequestInfo();
        request.classcode = classCode;
        ((PropertyErshouwupinMessageProcessProxy) mProcess)
                .requestErshouwupinPinpaiType(this, request, this);
        showCustomProgrssDialog();
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
                initPinpaiTypes(((PropertyTypeItem) info).type_id);
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

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
    
    private void stopRefresh() {
        mList.stopLoadMore();
        mList.stopRefresh();
    }
}
