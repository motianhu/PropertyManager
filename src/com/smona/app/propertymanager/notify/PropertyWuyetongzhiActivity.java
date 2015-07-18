package com.smona.app.propertymanager.notify;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.baoxiu.PropertyWuyebaoxiuMessageProcess;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyWuyetongzhiHomeContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class PropertyWuyetongzhiActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyWuyetongzhiActivity";

    ArrayList<PropertyItemInfo> mDatas = new ArrayList<PropertyItemInfo>();
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
        loadDBData();
    }

    private void requestData() {
        mProcess = new PropertyWuyebaoxiuMessageProcess();
        String content = mProcess.getWuyetongzhiContent(this);
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyWuyetongzhiHomeContentItem>() {
        }.getType();
        mContent = JsonUtils.parseJson(content, type);

    }

    private void loadDBData() {
        mContent.loadDBData(this);
        LogUtil.d(TAG, "loadDBData mContent: " + mContent);
        mDatas.addAll(mContent.icobject);
        requestRefreshUI();
    }

    protected void refreshUI() {
        initYezhuxinxi();
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
        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        PropertyNotifyMessageAdapter adapter = new PropertyNotifyMessageAdapter(
                this, mDatas);
        list.setAdapter(adapter);
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
