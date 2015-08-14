package com.smona.app.propertymanager.wupin;

import java.lang.reflect.Type;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyModifyResultActivity;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinDetailRequestInfo;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinMessageProcessProxy;

public class PropertyWupinDetailActivity extends PropertyModifyResultActivity {

    private static final String TAG = "PropertyWupinDetailActivity";

    private PropertyErshouwupinContentItem mItem;
    private boolean mIsMySelf = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin_detail);
        acquireData();
        initViews();
        requestRefreshUI();
    }

    private void acquireData() {
        mItem = (PropertyErshouwupinContentItem) getIntent()
                .getParcelableExtra(PropertyConstants.DATA_ITEM_INFO);
        LogUtil.d(TAG, "acquireData mItem: " + mItem);
        mIsMySelf = ConfigsInfo.username.equalsIgnoreCase(mItem.customerid);
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
        initText(parent, R.id.name, R.string.property_ershouwupin_item_xinjiu);

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

        mPictureContainer = (ViewGroup) mRoot.findViewById(R.id.list_hor_image);
        mPictureContainer.removeAllViews();
        if (mItem.picurl != null) {
            for (int i = 0; i < mItem.picurl.size(); i++) {
                addImageView(mItem.picurl.get(i));
            }
        }

        if (mIsMySelf) {
            findViewById(R.id.other_operate).setVisibility(View.GONE);
            initView(R.id.modify_info);
            initView(R.id.cancel_publish);
        } else {
            findViewById(R.id.my_operate).setVisibility(View.GONE);
            initView(R.id.call_phone);
            TextView text = (TextView) mRoot.findViewById(R.id.call_phone);
            text.setText(getResources().getString(
                    R.string.property_common_call_contact_phone)
                    + "(" + mItem.userphone + ")");
        }
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
    protected void clickView(View v) {
        super.clickView(v);
        int id = v.getId();
        switch (id) {
        case R.id.call_phone:
            clickCall();
            break;
        case R.id.modify_info:
            gotoModifyActivity();
            break;
        case R.id.cancel_publish:
            cancelPublish();
            break;
        }
    }

    protected void gotoModifyActivity() {
        Intent intent = new Intent();
        intent.setClass(this, PropertyWupinfabuActivity.class);
        intent.putExtra(PropertyConstants.DATA_ITEM_INFO, mItem);
        startActivityForResult(intent, ACTION_MODIFY);
    }

    protected void cancelPublish() {
        mProcess = new PropertyErshouwupinMessageProcessProxy();

        mRequestInfo = new PropertyErshouwupinDetailRequestInfo();
        ((PropertyErshouwupinDetailRequestInfo) mRequestInfo).publishid = mItem.publishid;

        ((PropertyErshouwupinMessageProcessProxy) mProcess)
                .submitErshouwupinCancelPublish(this, mRequestInfo, this);
        showCustomProgrssDialog();
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        if ("5110".equals(info.iccode) && "00".equals(info.answercode)) {
            Intent intent = new Intent();
            intent.putExtra(PropertyConstants.DATA_CANCEL_ITEM, mItem);
            setResult(RESULT_OK, intent);
            showMessage("取消发布成功");
            finish();
        } else {
            showMessage("取消发布失败");
        }
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    private void clickCall() {
        String phone = "tel:" + mItem.userphone;
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected boolean isDelete() {
        return false;
    }

    @Override
    protected void modifySuccess() {
        mItem.cloneData((PropertyErshouwupinContentItem) mModifyItem);
        requestRefreshUI();
    }

}
