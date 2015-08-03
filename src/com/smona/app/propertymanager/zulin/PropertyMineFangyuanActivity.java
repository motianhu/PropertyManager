package com.smona.app.propertymanager.zulin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.common.PropertyFilterTypeActivity;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinContentItem;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinHomeContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinMessageProcessProxy;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinRequestInfo;

public class PropertyMineFangyuanActivity extends PropertyFilterTypeActivity {
    private static final String TAG = "PropertyMineFangyuanActivity";

    // content
    private PropertyFangwuzulinHomeContentItem mContent;

    // type
    private PropertyFangwuzulinTypeItem mTypes;
    private ArrayList<PropertyItemInfo> mYewuDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mHuxingDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mAreaDatas = new ArrayList<PropertyItemInfo>();
    
    // filter
    private String mFilterChooseType = "";
    private String mFilterAreaCode = "";
    private String mFilterHouseCode = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_fangwuzulin_mine);
        aquireData();
        initViews();
        requestLoadData();
    }

    private void aquireData() {
        mTypes = new PropertyFangwuzulinTypeItem();
        mTypes.loadDBData(this);

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

        mHuxingDatas.addAll(mTypes.hourse);
        mAreaDatas.addAll(mTypes.areas);
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        mProcess = new PropertyFangwuzulinMessageProcessProxy();

        mRequestInfo = new PropertyFangwuzulinRequestInfo();
        fetchListData();
    }

    private void fetchListData() {
        showCustomProgrssDialog();
        ((PropertyFangwuzulinRequestInfo) mRequestInfo).pageno = getCurrentPage() + "";
        ((PropertyFangwuzulinRequestInfo) mRequestInfo).pageSize = PAGE_SIZE + "";
        ((PropertyFangwuzulinMessageProcessProxy) mProcess)
                .requestFangwuzulinMine(this, mRequestInfo, this);

    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyFangwuzulinHomeContentItem>() {
        }.getType();
        mContent = JsonUtils.parseJson(content, type);

        LogUtil.d(TAG, "mContent.icobject: " + mContent.icobject.size());
        if ("4510".equals(mContent.iccode)
                && "00".endsWith(mContent.answercode)) {
            loadDBData();
            setDataPos(mContent.icobject.size());
        }
        finishDialogAndRefresh();
    }

    protected void failedRequest() {
        finishDialogAndRefresh();
    }

    private void loadDBData() {
        mAllDatas.addAll(mContent.icobject);
        mShowDatas.clear();
        mShowDatas.addAll(mAllDatas);
        
        requestRefreshUI();
    }

    protected void refreshUI() {
        notifyDataSetChanged();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_fangwuzulin_mine);
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
                LogUtil.d(TAG, "clickSelectType: type_name: "
                        + ((PropertyTypeItem) info).type_name + ", type_id: "
                        + ((PropertyTypeItem) info).type_id);
                View parent = mRoot.findViewById(R.id.ywtype);
                mFilterChooseType = ((PropertyTypeItem) info).type_id;
                filterType(((PropertyTypeItem) info).type_id);
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
                LogUtil.d(TAG, "clickSelectHuxing: type_name: "
                        + ((PropertyTypeItem) info).type_name + ", type_id: "
                        + ((PropertyTypeItem) info).type_id);
                View parent = mRoot.findViewById(R.id.housetype);
                mFilterHouseCode = ((PropertyTypeItem) info).type_id;
                filterType(((PropertyTypeItem) info).type_id);
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
                LogUtil.d(TAG, "clickSelectArea: type_name: "
                        + ((PropertyTypeItem) info).type_name + ", type_id: "
                        + ((PropertyTypeItem) info).type_id);
                View parent = mRoot.findViewById(R.id.area);
                mFilterAreaCode = ((PropertyTypeItem) info).type_id;
                filterType(((PropertyTypeItem) info).type_id);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }
    


    private void filterType(String filteId) {
        mShowDatas.clear();
        for (PropertyItemInfo info : mAllDatas) {
            LogUtil.d(TAG, "info: " + (PropertyFangwuzulinContentItem) info);
            if (isFitFilter((PropertyFangwuzulinContentItem) info)) {
                mShowDatas.add(info);
            }
        }
        requestRefreshUI();
    }

    private boolean isFitFilter(PropertyFangwuzulinContentItem info) {
        boolean result = true;

        if (!TextUtils.isEmpty(mFilterChooseType)) {
            result = mFilterChooseType
                    .equals(((PropertyFangwuzulinContentItem) info).choosetype);
        }

        if (!TextUtils.isEmpty(mFilterAreaCode)) {
            result = mFilterAreaCode
                    .equals(((PropertyFangwuzulinContentItem) info).areacode);
        }

        if (!TextUtils.isEmpty(mFilterHouseCode)) {
            result = mFilterHouseCode
                    .equals(((PropertyFangwuzulinContentItem) info).housecode);
        }

        return result;
    }

    @Override
    protected void loadMore() {
        fetchListData();
    }
    
    @Override
    public PropertyBaseDataAdapter createAdapter(
            ArrayList<PropertyItemInfo> data) {
        return new PropertyZulinDetailAdapter(this, data);
    }
}
