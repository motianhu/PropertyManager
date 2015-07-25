package com.smona.app.propertymanager.wupin;

import java.lang.reflect.Type;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinContentItem;
import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

public class PropertyWupinDetailActivity extends PropertyBaseActivity {

    private static final String TAG = "PropertyWupinDetailActivity";

    private PropertyErshouwupinContentItem mItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin_detail);
        acquireData();
        initViews();
        requestLoadData();
    }

    private void acquireData() {
        mItem = (PropertyErshouwupinContentItem) getIntent()
                .getParcelableExtra("iteminfo");
    }

    protected void loadData() {
        requestData();
        loadDBData();
    }

    private void requestData() {
        mProcess = new PropertyMessageProcessProxy();
        mProcess.requestErshouwupinDetail(this, this);
    }

    protected void saveData(String content) {
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyErshouwupinContentItem>() {
        }.getType();
        mItem = JsonUtils.parseJson(content, type);

        loadDBData();
    }

    protected void failedRequest() {

    }

    private void loadDBData() {
        requestRefreshUI();
    }

    protected void refreshUI() {
        View parent = mRoot.findViewById(R.id.wupinfenlei);
        initText(parent, R.id.value, mItem.classname);

        parent = mRoot.findViewById(R.id.pinpai);
        initText(parent, R.id.value, mItem.brand);

        parent = mRoot.findViewById(R.id.wupinmingchen);
        initText(parent, R.id.value, mItem.goodsname);

        parent = mRoot.findViewById(R.id.wupinmiaoshu);
        initText(parent, R.id.value, mItem.goodsdesc);

        parent = mRoot.findViewById(R.id.xinjiu);
        initText(parent, R.id.value, mItem.goosstatus);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.value, mItem.username);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.value, mItem.userphone);

        parent = mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.value, mItem.publishtime);

        parent = mRoot.findViewById(R.id.wupin_picture);
        ImageView image = (ImageView) parent.findViewById(R.id.image);
        ImageLoaderManager.getInstance().loadImage(mItem.picurl.get(0), image);

        TextView text = (TextView) mRoot.findViewById(R.id.call_phone);
        text.setText(getResources().getString(
                R.string.property_common_call_contact_phone)
                + "(" + mItem.userphone + ")");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_ershouwupin_wupin_detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.wupinfenlei);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_detail_wupinfenlei);

        parent = mRoot.findViewById(R.id.pinpai);
        initText(parent, R.id.name, R.string.property_ershouwupin_detail_pinpai);

        parent = mRoot.findViewById(R.id.wupinmingchen);
        initText(parent, R.id.name, R.string.property_ershouwupin_wupinmingchen);

        parent = mRoot.findViewById(R.id.wupinmiaoshu);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_wupinmiaoshu);

        parent = mRoot.findViewById(R.id.xinjiu);
        initText(parent, R.id.name, R.string.property_ershouwupin_xuanzexinjiu);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_lianxiren);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_dianhu);

        parent = mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_pulish_time);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.call_phone:
            clickCall();
            break;
        }
    }

    private void clickCall() {
        String phone = "tel:" + mItem.userphone;
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
