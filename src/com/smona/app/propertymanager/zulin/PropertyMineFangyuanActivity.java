package com.smona.app.propertymanager.zulin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinHomeContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinMessageProcessProxy;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinRequestInfo;

public class PropertyMineFangyuanActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyMineFangyuanActivity";

    // content
    private PropertyZulinDetailAdapter mAdapter;
    private PropertyFangwuzulinHomeContentItem mContent;
    private ArrayList<PropertyItemInfo> mDatas = new ArrayList<PropertyItemInfo>();

    // type
    private PropertyFangwuzulinTypeItem mTypes;
    private ArrayList<PropertyItemInfo> mYewuDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mHuxingDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mAreaDatas = new ArrayList<PropertyItemInfo>();

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

        mYewuDatas.addAll(mTypes.yewus);
        mHuxingDatas.addAll(mTypes.hourse);
        mAreaDatas.addAll(mTypes.areas);
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        mProcess = new PropertyFangwuzulinMessageProcessProxy();

        mRequestInfo = new PropertyFangwuzulinRequestInfo();
        ((PropertyFangwuzulinRequestInfo) mRequestInfo).pageno = "1";
        ((PropertyFangwuzulinRequestInfo) mRequestInfo).pageSize = "12";

        ((PropertyFangwuzulinMessageProcessProxy) mProcess)
                .requestFangwuzulinMine(this, mRequestInfo, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyFangwuzulinHomeContentItem>() {
        }.getType();
        mContent = JsonUtils.parseJson(content, type);

        LogUtil.d(TAG, "mContent.icobject: " + mContent.icobject.size());
        loadDBData();
    }

    protected void failedRequest() {

    }

    private void loadDBData() {
        mDatas.addAll(mContent.icobject);

        requestRefreshUI();
    }

    protected void refreshUI() {
        mAdapter.notifyDataSetChanged();
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

        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        ArrayList<PropertyItemInfo> data = mDatas;
        mAdapter = new PropertyZulinDetailAdapter(this, data);
        list.setAdapter(mAdapter);
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
        final ArrayList<PropertyItemInfo> datas = mYewuDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.ywtype);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void clickSelectHuxing() {
        final ArrayList<PropertyItemInfo> datas = mHuxingDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.housetype);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }

    private void clickSelectArea() {
        final ArrayList<PropertyItemInfo> datas = mAreaDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.area);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }
}
