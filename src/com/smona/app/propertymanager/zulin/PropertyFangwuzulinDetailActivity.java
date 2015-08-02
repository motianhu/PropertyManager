package com.smona.app.propertymanager.zulin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyBaseActivity;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinDetailRequestInfo;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinMessageProcessProxy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PropertyFangwuzulinDetailActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyFangwuzulinDetailActivity";

    private PropertyFangwuzulinContentItem mItem;
    private boolean mIsMySelf = false;

    private ArrayList<PropertyItemInfo> mYewuDatas = new ArrayList<PropertyItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_fangwuzulin_detail);
        acquireData();
        initViews();
        requestRefreshUI();
    }

    private void acquireData() {
        mItem = (PropertyFangwuzulinContentItem) getIntent()
                .getParcelableExtra("iteminfo");
        mIsMySelf = mItem.loginname == mItem.customerid;
        LogUtil.d(TAG, "acquireData mItem: " + mItem);
        initYewuDatas();
    }

    private void initYewuDatas() {
        String[] ids = getResources().getStringArray(
                R.array.fangwuzulin_ywtype_id);
        String[] ywnames = getResources().getStringArray(
                R.array.fangwuzulin_ywtype_name);

        for (int i = 0; i < ids.length; i++) {
            PropertyTypeItem item = new PropertyTypeItem();
            item.type_id = ids[i];
            item.type_name = ywnames[i];
            mYewuDatas.add(item);
        }
    }

    protected void refreshUI() {
        View parent = mRoot.findViewById(R.id.ywtype);
        String chooseName = "";
        int size = mYewuDatas.size();
        for (int i = 0; i < size; i++) {
            if (((PropertyTypeItem) mYewuDatas.get(i)).type_id
                    .equals(mItem.choosetype)) {
                chooseName = ((PropertyTypeItem) mYewuDatas.get(i)).type_name;
                break;
            }
        }
        initText(parent, R.id.value, chooseName);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.value, mItem.housearea);

        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.value, mItem.housetype);

        parent = mRoot.findViewById(R.id.peitaomiaoshu);
        initText(parent, R.id.value, mItem.housedesc);

        parent = mRoot.findViewById(R.id.position);
        initText(parent, R.id.value, mItem.houseaddress);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.value, mItem.username);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.value, mItem.userphone);

        parent = mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.value, mItem.publishtime);

        ViewGroup list = (ViewGroup) mRoot.findViewById(R.id.list_hor_image);
        for (int i = 0; i < mItem.picurl.size(); i++) {
            ImageView image = new ImageView(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    getResources()
                            .getDimensionPixelSize(
                                    R.dimen.property_common_paishezhaoping_container_height),
                    getResources()
                            .getDimensionPixelSize(
                                    R.dimen.property_common_paishezhaoping_container_height));
            param.leftMargin = 10;
            list.addView(image, param);
            ImageLoaderManager.getInstance().loadImage(mItem.picurl.get(i),
                    image);
        }

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
        initText(R.id.title, R.string.property_fangwuzulin_fangyuanxinxi);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.ywtype);
        initText(parent, R.id.name,
                R.string.property_fangwuzulin_item_yewuleixing);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_area);

        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_huxing);

        parent = mRoot.findViewById(R.id.peitaomiaoshu);
        initText(parent, R.id.name,
                R.string.property_fangwuzulin_fangyuanfabu_peitaomiaoshu);

        parent = mRoot.findViewById(R.id.position);
        initText(parent, R.id.name, R.string.property_fangwuzulin_position);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_lianxiren);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_dianhu);

        parent = mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_pulish_time);

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
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
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
        intent.setClass(this, PropertyPublishFangYuanActivity.class);
        intent.putExtra("iteminfo", mItem);
        startActivity(intent);
    }

    protected void cancelPublish() {
        mProcess = new PropertyFangwuzulinMessageProcessProxy();

        mRequestInfo = new PropertyFangwuzulinDetailRequestInfo();
        ((PropertyFangwuzulinDetailRequestInfo) mRequestInfo).publishid = mItem.publishid;

        ((PropertyFangwuzulinMessageProcessProxy) mProcess)
                .submitFangwuzulinCancelPublish(this, mRequestInfo, this);
        showCustomProgrssDialog();
    }
    
    protected void saveData(String content) {
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        if("4610".equals(info.iccode) && "00".equals(info.answercode)) {
            showMessage("取消发布成功");
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
}
