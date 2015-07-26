package com.smona.app.propertymanager.baoxiu;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.baoxiu.process.PropertyWuyebaoxiuMessageProcessProxy;
import com.smona.app.propertymanager.baoxiu.process.PropertyWuyebaoxiuRequestInfo;
import com.smona.app.propertymanager.data.model.PropertyCustomerContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyWuyebaoxiudanHomeContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class PropertyBaoxiudanActivity extends PropertyBaseActivity {

    private static final String TAG = "PropertyBaoxiudanActivity";

    private PropertyBaoxiudansAdapter mAdapter;
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
        ((PropertyWuyebaoxiuRequestInfo) mRequestInfo).pageno = "1";
        ((PropertyWuyebaoxiuRequestInfo) mRequestInfo).pageSize = "12";

        ((PropertyWuyebaoxiuMessageProcessProxy) mProcess)
                .requestWuyebaoxiudan(this, mRequestInfo, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyWuyebaoxiudanHomeContentItem>() {
        }.getType();
        mBean = JsonUtils.parseJson(content, type);

        LogUtil.d(TAG, "content: " + mBean);
        loadDBData();
    }

    protected void failedRequest() {

    }

    private void loadDBData() {
        mDatas.addAll(mBean.icobjct);
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
        initText(R.id.title, R.string.property_wuyebaoxiu_baoxiudan);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        initYezhuxinxi();

        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        mAdapter = new PropertyBaoxiudansAdapter(this, mDatas);
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
}
