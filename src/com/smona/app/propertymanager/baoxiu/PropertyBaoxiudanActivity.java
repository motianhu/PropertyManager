package com.smona.app.propertymanager.baoxiu;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.baoxiu.process.PropertyWuyebaoxiuMessageProcessProxy;
import com.smona.app.propertymanager.baoxiu.process.PropertyWuyebaoxiuRequestInfo;
import com.smona.app.propertymanager.common.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.common.PropertyFetchListActivity;
import com.smona.app.propertymanager.data.model.PropertyCustomerContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyWuyebaoxiudanHomeContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PropertyBaoxiudanActivity extends PropertyFetchListActivity {

    private static final String TAG = "PropertyBaoxiudanActivity";

    private ArrayList<PropertyItemInfo> mDatas = new ArrayList<PropertyItemInfo>();

    private PropertyCustomerContentItem customer;
    private PropertyWuyebaoxiudanHomeContentItem mBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyebaoxiu_baoxiudan);
        acquireData();
        initViews();
        requestLoadData();
    }

    private void acquireData() {
        customer = getIntent().getParcelableExtra("customer");
        LogUtil.d(TAG, "customer: " + customer);
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        mProcess = new PropertyWuyebaoxiuMessageProcessProxy();
        mRequestInfo = new PropertyWuyebaoxiuRequestInfo();
        fetchListData();
    }

    private void fetchListData() {
        showCustomProgrssDialog();
        ((PropertyWuyebaoxiuRequestInfo) mRequestInfo).pageno = getCurrentPage()
                + "";
        ((PropertyWuyebaoxiuRequestInfo) mRequestInfo).pageSize = PAGE_SIZE
                + "";
        ((PropertyWuyebaoxiuMessageProcessProxy) mProcess)
                .requestWuyebaoxiudan(this, mRequestInfo, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyWuyebaoxiudanHomeContentItem>() {
        }.getType();
        mBean = JsonUtils.parseJson(content, type);

        LogUtil.d(TAG, "content: " + mBean);
        if ("3410".equals(mBean.iccode) && "00".equals(mBean.answercode)) {
            LogUtil.d(TAG, "content: " + mBean.icobjct.size());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_wuyebaoxiu_baoxiudan);
        initView(R.id.back);
    }

    @Override
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
    protected void loadMore() {
        fetchListData();
    }

    @Override
    public PropertyBaseDataAdapter createAdapter(
            ArrayList<PropertyItemInfo> data) {
        return new PropertyBaoxiudansAdapter(this, data);
    }
}
