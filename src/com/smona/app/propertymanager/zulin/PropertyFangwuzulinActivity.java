package com.smona.app.propertymanager.zulin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.common.PropertyFilterTypeActivity;
import com.smona.app.propertymanager.data.bean.PropertyBeanFangwuzulinType;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinContentItem;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinHomeContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinMessageProcessProxy;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinRequestInfo;

import android.os.Bundle;
import android.view.View;

public class PropertyFangwuzulinActivity extends PropertyFilterTypeActivity {
    private static final String TAG = "PropertyFangwuzulinActivity";

    // content
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
        loadLocalData();
        requestLoadData();
    }

    private void loadLocalData() {
        String[] ids = getResources().getStringArray(
                R.array.fangwuzulin_ywtype_id);
        String[] ywnames = getResources().getStringArray(
                R.array.fangwuzulin_ywtype_name);

        for (int i = 0; i < ids.length; i++) {
            PropertyTypeItem item = new PropertyTypeItem();
            item.type_id = ids[i];
            item.type_name = ywnames[i];
            mYewuDatas.add(item);
        }
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        mProcess = new PropertyFangwuzulinMessageProcessProxy();

        mRequestInfo = new PropertyFangwuzulinRequestInfo();
        requestListData();

        ((PropertyFangwuzulinMessageProcessProxy) mProcess)
                .requestFangwuzulinType(this, this);

        showCustomProgrssDialog();
    }

    private void requestListData() {
        ((PropertyFangwuzulinRequestInfo) mRequestInfo).pageno = getCurrentPage()
                + "";
        ((PropertyFangwuzulinRequestInfo) mRequestInfo).pageSize = PAGE_SIZE
                + "";
        ((PropertyFangwuzulinMessageProcessProxy) mProcess).requestFangwuzulin(
                this, mRequestInfo, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        if ("4210".equals(info.iccode)) {
            if ("00".equals(info.answercode)) {
                type = new TypeToken<PropertyFangwuzulinHomeContentItem>() {
                }.getType();
                mContent = JsonUtils.parseJson(content, type);
                loadListData();
                setDataPos(Integer.valueOf(mContent.pagesize));
            }
            finishDialogAndRefresh();
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
        finishDialogAndRefresh();
    }

    private void loadTypeData() {
        mTypes = new PropertyFangwuzulinTypeItem();
        mTypes.loadDBData(this);
        LogUtil.d(TAG, "loadDBData mContent: " + mTypes);

        mHuxingDatas.addAll(mTypes.hourse);
        mAreaDatas.addAll(mTypes.areas);
    }

    private void loadListData() {
        // has problem
        mAllDatas.addAll(mContent.icobject);
        requestRefreshUI();
    }

    protected void refreshUI() {
        notifyDataSetChanged();
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

        mShowDatas.addAll(mAllDatas);
        setFetchListener(mShowDatas);
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
                filterYewuType(((PropertyTypeItem) info).type_id);
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
                filterHuxingType(((PropertyTypeItem) info).type_id);
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
                filterAreaType(((PropertyTypeItem) info).type_name);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void filterYewuType(String filterName) {
        mShowDatas.clear();
        for (PropertyItemInfo info : mAllDatas) {
            if (filterName
                    .equals(((PropertyFangwuzulinContentItem) info).choosetype)) {
                mShowDatas.add(info);
            }
        }
        requestRefreshUI();
    }

    private void filterHuxingType(String filterName) {
        mShowDatas.clear();
        for (PropertyItemInfo info : mAllDatas) {
            if (filterName
                    .equals(((PropertyFangwuzulinContentItem) info).housetype)) {
                mShowDatas.add(info);
            }
        }
        requestRefreshUI();
    }

    private void filterAreaType(String filterName) {
        mShowDatas.clear();
        for (PropertyItemInfo info : mAllDatas) {
            if (filterName
                    .equals(((PropertyFangwuzulinContentItem) info).housearea)) {
                mShowDatas.add(info);
            }
        }
        requestRefreshUI();
    }

    @Override
    public void loadMore() {
        requestListData();
        showCustomProgrssDialog();
    }
    
    @Override
    public PropertyBaseDataAdapter createAdapter(
            ArrayList<PropertyItemInfo> data) {
        return new PropertyZulinDetailAdapter(this, data);
    }
}
