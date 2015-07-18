package com.smona.app.propertymanager.baoxiu;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
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

    private ListView mBaoxiudans;
    private PropertyBaoxiudansAdapter mBaoxiudansAdapter;
    ArrayList<PropertyItemInfo> mDatas = new ArrayList<PropertyItemInfo>();

    private PropertyCustomerContentItem customer;
    private PropertyWuyebaoxiudanHomeContentItem mBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyebaoxiu_baoxiudan);
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
        loadDBData();
    }

    private void requestData() {
        mProcess = new PropertyWuyebaoxiuMessageProcess();
        String content = mProcess.getWuyebaoxiudanContent(this);
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyWuyebaoxiudanHomeContentItem>() {
        }.getType();
        mBean = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "mBean: " + mBean);
    }

    private void loadDBData() {
        mDatas.addAll(mBean.icobjct);
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

        mBaoxiudans = (ListView) mRoot.findViewById(R.id.list_content);
        mBaoxiudansAdapter = new PropertyBaoxiudansAdapter(this, mDatas);
        mBaoxiudans.setAdapter(mBaoxiudansAdapter);
    }

    private void initYezhuxinxi() {
        initText(R.id.yezhuxinxi_xingming, customer.username);
        initText(R.id.yezhuxinxi_dianhua, "(" + customer.userphone + ")");
        initText(R.id.yezhuxinxi_dizhi, customer.useraddress);

        ImageView image = (ImageView) mRoot
                .findViewById(R.id.yezhuxinxi_touxiang);
        ImageLoaderManager.getInstance().loadImage(customer.pictureurl.get(0),
                image);
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
