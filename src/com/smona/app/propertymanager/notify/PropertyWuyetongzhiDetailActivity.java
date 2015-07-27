package com.smona.app.propertymanager.notify;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyWuyetongzhiContentItem;
import com.smona.app.propertymanager.notify.process.PropertyWuyetongzhiMessageProcessProxy;
import com.smona.app.propertymanager.notify.process.PropertyWuyetongzhiSubmitRequestInfo;

public class PropertyWuyetongzhiDetailActivity extends PropertyBaseActivity {

    private PropertyWuyetongzhiContentItem mItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyetongzhi_detail);
        acquireData();
        initViews();
        requestLoadData();
    }

    private void acquireData() {
        mItem = (PropertyWuyetongzhiContentItem) getIntent()
                .getParcelableExtra("iteminfo");
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        mProcess = new PropertyWuyetongzhiMessageProcessProxy();

        mRequestInfo = new PropertyWuyetongzhiSubmitRequestInfo();
        ((PropertyWuyetongzhiSubmitRequestInfo) mRequestInfo).publishid = mItem.publishid;

        ((PropertyWuyetongzhiMessageProcessProxy) mProcess)
                .submitWuyetongzhiRead(this, mRequestInfo, this);
    }

    protected void saveData(String content) {
        requestRefreshUI();
    }

    protected void refreshUI() {
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
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
        TextView text = (TextView) mRoot.findViewById(R.id.notify_time);
        text.setText(mItem.publishtime);

        text = (TextView) mRoot.findViewById(R.id.notify_title);
        text.setText(mItem.title);

        text = (TextView) mRoot.findViewById(R.id.notify_content);
        text.setText(mItem.notice);
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
