package com.smona.app.propertymanager.tousu;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.common.PropertyFetchListActivity;
import com.smona.app.propertymanager.data.model.PropertyCustomerContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTousujianyidanHomeContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiMessageProcessProxy;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiRequestInfo;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

public class PropertyMineTousuActivity extends PropertyFetchListActivity {
    private static final String TAG = "PropertyMineTousuActivity";

    private ArrayList<PropertyItemInfo> mDatas = new ArrayList<PropertyItemInfo>();

    private PropertyCustomerContentItem customer;
    private PropertyTousujianyidanHomeContentItem mBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_tousujianyi_mine);
        acquireData();
        initViews();
        LogUtil.d(TAG, "customer: " + customer);
        requestLoadData();
    }

    private void acquireData() {
        customer = getIntent().getParcelableExtra("customer");
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        mProcess = new PropertyTousujianyiMessageProcessProxy();
        mRequestInfo = new PropertyTousujianyiRequestInfo();
        fetchListData();
    }

    private void fetchListData() {
        ((PropertyTousujianyiRequestInfo) mRequestInfo).pageno = getCurrentPage()
                + "";
        ((PropertyTousujianyiRequestInfo) mRequestInfo).pageSize = PAGE_SIZE
                + "";
        ((PropertyTousujianyiMessageProcessProxy) mProcess)
                .requestTousujianyidan(this, mRequestInfo, this);
        showCustomProgrssDialog();
    }

    protected void saveData(String content) {
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyTousujianyidanHomeContentItem>() {
        }.getType();
        mBean = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "content: " + mBean);
        if ("3910".equals(mBean) && "00".equals(mBean.answercode)) {
            loadDBData();
            setDataPos(Integer.valueOf(mBean.pageno));
        }
        finishDialogAndRefresh();
    }

    protected void failedRequest() {
        finishDialogAndRefresh();
    }

    private void loadDBData() {
        mDatas.addAll(mBean.icobjct);
        requestRefreshUI();
    }

    protected void refreshUI() {
        notifyDataSetChanged();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_tousujianyi);
        initView(R.id.back);
    }

    protected void initBody() {
        initYezhuxinxi();

        setFetchListener(mDatas);
    }

    private void initYezhuxinxi() {
        initText(R.id.yezhuxinxi_xingming, customer.username);
        initText(R.id.yezhuxinxi_dianhua, "(" + customer.userphone + ")");
        initText(R.id.yezhuxinxi_dizhi, customer.useraddress);

        if (customer.pictureurl != null && customer.pictureurl.size() > 0) {
            ImageView image = (ImageView) mRoot
                    .findViewById(R.id.yezhuxinxi_touxiang);
            ImageLoaderManager.getInstance().loadImage(
                    customer.pictureurl.get(0), image);
        }
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void loadMore() {
        fetchListData();
    }
    
    @Override
    public PropertyBaseDataAdapter createAdapter(
            ArrayList<PropertyItemInfo> data) {
        return new PropertyTousudanAdapter(this, data);
    }

}
