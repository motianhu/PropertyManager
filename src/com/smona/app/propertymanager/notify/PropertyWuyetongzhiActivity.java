package com.smona.app.propertymanager.notify;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.common.PropertyFetchListActivity;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyWuyetongzhiHomeContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.notify.process.PropertyWuyetongzhiMessageProcessProxy;
import com.smona.app.propertymanager.notify.process.PropertyWuyetongzhiRequestInfo;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PropertyWuyetongzhiActivity extends PropertyFetchListActivity {
    private static final String TAG = "PropertyWuyetongzhiActivity";

    private ArrayList<PropertyItemInfo> mDatas = new ArrayList<PropertyItemInfo>();

    private PropertyWuyetongzhiHomeContentItem mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyetongzhi);
        initViews();
        requestLoadData();
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        mProcess = new PropertyWuyetongzhiMessageProcessProxy();

        mRequestInfo = new PropertyWuyetongzhiRequestInfo();
        fetchListData();
    }

    private void fetchListData() {
        showCustomProgrssDialog();
        ((PropertyWuyetongzhiRequestInfo) mRequestInfo).pageno = getCurrentPage()
                + "";
        ((PropertyWuyetongzhiRequestInfo) mRequestInfo).pageSize = PAGE_SIZE
                + "";

        ((PropertyWuyetongzhiMessageProcessProxy) mProcess).requestWuyetongzhi(
                this, mRequestInfo, this);
    }

    protected void saveData(String content) {
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyWuyetongzhiHomeContentItem>() {
        }.getType();
        mContent = JsonUtils.parseJson(content, type);
        if ("5310".equals(mContent.iccode) && "00".equals(mContent.answercode)) {
            loadDBData();
            setDataPos(mContent.icobject.size());
        }
        finishDialogAndRefresh();
    }

    protected void failedRequest() {
        finishDialogAndRefresh();
    }

    private void loadDBData() {
        mContent.loadDBData(this);
        LogUtil.d(TAG, "loadDBData mContent: " + mContent);
        requestRefreshUI();
    }

    protected void refreshUI() {
        mDatas.addAll(mContent.icobject);
        initYezhuxinxi();
        notifyDataSetChanged();
    }

    private void initYezhuxinxi() {
        initText(R.id.yezhuxinxi_xingming, mContent.customer.username);
        initText(R.id.yezhuxinxi_dianhua, "(" + mContent.customer.userphone
                + ")");
        initText(R.id.yezhuxinxi_dizhi, mContent.customer.useraddress);

        ImageView image = (ImageView) mRoot
                .findViewById(R.id.yezhuxinxi_touxiang);
        ImageLoaderManager.getInstance().loadImage(
                mContent.customer.pictureurl.get(0), image);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_wuyetongzhi);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        setFetchListener(mDatas);
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
        return new PropertyNotifyMessageAdapter(this, data);
    }
}
