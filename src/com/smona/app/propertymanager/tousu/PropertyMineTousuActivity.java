package com.smona.app.propertymanager.tousu;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyCustomerContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTousujianyidanHomeContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiMessageProcessProxy;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiRequestInfo;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

public class PropertyMineTousuActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyMineTousuActivity";

    private PropertyTousudanAdapter mAdapter;
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
        ((PropertyTousujianyiRequestInfo) mRequestInfo).pageno = "1";
        ((PropertyTousujianyiRequestInfo) mRequestInfo).pageSize = "12";
        ((PropertyTousujianyiMessageProcessProxy) mProcess)
                .requestTousujianyidan(this, mRequestInfo, this);
        showCustomProgrssDialog();
    }

    protected void saveData(String content) {
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyTousujianyidanHomeContentItem>() {
        }.getType();
        mBean = JsonUtils.parseJson(content, type);

        loadDBData();
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    private void loadDBData() {
        mDatas.addAll(mBean.icobjct);
        requestRefreshUI();
    }

    protected void refreshUI() {
        mAdapter.notifyDataSetChanged();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_tousujianyi);
        initView(R.id.back);
    }

    protected void initBody() {
        initYezhuxinxi();

        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        mAdapter = new PropertyTousudanAdapter(this, mDatas);
        list.setAdapter(mAdapter);
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

}
