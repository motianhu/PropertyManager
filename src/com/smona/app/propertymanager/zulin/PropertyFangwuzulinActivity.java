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
import com.smona.app.propertymanager.source.listview.XListView;
import com.smona.app.propertymanager.source.listview.XListView.IXListViewListener;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinMessageProcessProxy;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinRequestInfo;

import android.os.Bundle;
import android.view.View;

public class PropertyFangwuzulinActivity extends PropertyBaseActivity implements
        IXListViewListener {
    private static final String TAG = "PropertyFangwuzulinActivity";

    private int mCurrPage = 1;
    private static final int PAGE_SIZE = 10;
    private boolean mIsDataOver = false;

    // content
    private XListView mList;
    private PropertyZulinDetailAdapter mAdapter;
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
        ((PropertyFangwuzulinRequestInfo) mRequestInfo).pageno = mCurrPage + "";
        ((PropertyFangwuzulinRequestInfo) mRequestInfo).pageSize = PAGE_SIZE
                + "";

        ((PropertyFangwuzulinMessageProcessProxy) mProcess).requestFangwuzulin(
                this, mRequestInfo, this);
        ((PropertyFangwuzulinMessageProcessProxy) mProcess)
                .requestFangwuzulinType(this, this);

         showCustomProgrssDialog();
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
            mCurrPage += 1;
            mIsDataOver = Integer.valueOf(mContent.pagesize) < PAGE_SIZE;
        } else if ("4310".equals(info.iccode)) {
            LogUtil.d(TAG, "1content: " + content);
            type = new TypeToken<PropertyBeanFangwuzulinType>() {
            }.getType();
            PropertyBeanFangwuzulinType bean = JsonUtils.parseJson(content,
                    type);
            bean.saveDataToDB(this);
            loadTypeData();
        }
        stopRefresh();
    }

    protected void failedRequest() {
        stopRefresh();
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
        mDatas.addAll(mContent.icobject);
        requestRefreshUI();
        hideCustomProgressDialog();
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

        mList = (XListView) mRoot.findViewById(R.id.list_content);
        mAdapter = new PropertyZulinDetailAdapter(this, mDatas);
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

    @Override
    public void onRefresh() {
        stopRefresh();
    }

    @Override
    public void onLoadMore() {
        if (mIsDataOver) {
            stopRefresh();
            showMessage("数据到达终点");
            return;
        }
        ((PropertyFangwuzulinRequestInfo) mRequestInfo).pageno = mCurrPage + "";
        ((PropertyFangwuzulinRequestInfo) mRequestInfo).pageSize = PAGE_SIZE
                + "";
        ((PropertyFangwuzulinMessageProcessProxy) mProcess).requestFangwuzulin(
                this, mRequestInfo, this);
        showCustomProgrssDialog();
    }

    private void stopRefresh() {
        mList.stopLoadMore();
        mList.stopRefresh();
    }
}
