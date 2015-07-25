package com.smona.app.propertymanager.zulin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.bean.PropertyBeanFangwuzulinType;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinHomeContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PropertyFangwuzulinActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyFangwuzulinActivity";

    // content
    private ArrayList<PropertyItemInfo> mDatas = new ArrayList<PropertyItemInfo>();
    private PropertyFangwuzulinHomeContentItem mContent;

    // type
    private PropertyFangwuzulinTypeItem mTypes;
    private ArrayList<PropertyItemInfo> mYewuDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mHuxingDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mAreaDatas = new ArrayList<PropertyItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_fangwuzulin);
        initViews();
        requestLoadData();
    }

    protected void loadData() {
        requestData();
        loadTypeData();
    }

    private void requestData() {
        mProcess = new PropertyMessageProcessProxy();
        mProcess.requestFangwuzulin(this, this);
        mProcess.requestFangwuzulinType(this, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        if ("4210".equals(info.iccode)) {
            type = new TypeToken<PropertyFangwuzulinHomeContentItem>() {
            }.getType();
            mContent = JsonUtils.parseJson(content, type);
            loadListData();
        } else if ("4310".equals(info.iccode)) {
            LogUtil.d(TAG, "1content: " + content);
            type = new TypeToken<PropertyBeanFangwuzulinType>() {
            }.getType();
            PropertyBeanFangwuzulinType bean = JsonUtils.parseJson(content,
                    type);
            bean.saveDataToDB(this);
            loadTypeData();
        }

    }

    protected void failedRequest() {

    }
    
    private void loadTypeData() {
        mTypes = new PropertyFangwuzulinTypeItem();
        mTypes.loadDBData(this);
        LogUtil.d(TAG, "loadDBData mContent: " + mTypes);
        
        mYewuDatas.addAll(mTypes.yewus);
        mHuxingDatas.addAll(mTypes.hourse);
        mAreaDatas.addAll(mTypes.areas);
    }

    private void loadListData() {
        //has problem
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
        initText(R.id.title, R.string.property_home_fangwuzulin);
        initText(R.id.detail, R.string.property_fangwuzulin_publish_fangyuan);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.ywtype);
        initTextHint(parent, R.id.select_type,
                R.string.property_fangwuzulin_xuanzeyewuleixing);
        initText(parent, R.id.select_type_value,
                R.string.property_fangwuzulin_item_yewuleixing);
        initView(R.id.ywtype);

        parent = mRoot.findViewById(R.id.area);
        initTextHint(parent, R.id.select_type,
                R.string.property_fangwuzulin_arealeixing);
        initText(parent, R.id.select_type_value,
                R.string.property_fangwuzulin_item_area);
        initView(R.id.area);

        parent = mRoot.findViewById(R.id.housetype);
        initTextHint(parent, R.id.select_type,
                R.string.property_fangwuzulin_xuanzehuxing);
        initText(parent, R.id.select_type_value,
                R.string.property_fangwuzulin_item_huxing);
        initView(R.id.housetype);

        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        PropertyZulinDetailAdapter adapter = new PropertyZulinDetailAdapter(
                this, mDatas);
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
        showSingleChoiceType(mYewuDatas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = mYewuDatas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.ywtype);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void clickSelectHuxing() {
        showSingleChoiceType(mHuxingDatas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = mHuxingDatas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.housetype);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void clickSelectArea() {
        showSingleChoiceType(mAreaDatas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = mAreaDatas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.area);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }
}
