package com.smona.app.propertymanager.tousu;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyStartupCameraActivity;
import com.smona.app.propertymanager.data.bean.PropertyBeanTousujianyi;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTousujianyiContentItem;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiMessageProcessProxy;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiSubmitRequestInfo;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class PropertyTousujianyiActivity extends PropertyStartupCameraActivity {

    private static final String TAG = "PropertyTousujianyiActivity";

    private PropertyTousujianyiContentItem mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_tousujianyi);
        initViews();
        requestLoadData();
    }

    protected void loadData() {
        requestData();
        loadDBData();
    }

    private void requestData() {
        showCustomProgrssDialog();
        mProcess = new PropertyTousujianyiMessageProcessProxy();
        ((PropertyTousujianyiMessageProcessProxy) mProcess).requestTousujianyi(
                this, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo bean = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "bean: " + bean);

        LogUtil.d(TAG, "loadDBData bean: " + bean);
        if ("3710".equals(bean.iccode)) {
            if ("00".equals(bean.answercode)) {
                type = new TypeToken<PropertyBeanTousujianyi>() {
                }.getType();
                PropertyBeanTousujianyi tousu = JsonUtils.parseJson(content,
                        type);
                tousu.saveDataToDB(this);
                loadDBData();
            } else {
                showMessage("失败");
            }
        } else if ("3810".equals(bean.iccode)) {
            if ("00".equals(bean.answercode)) {
                showMessage("提交成功");
                finish();
            } else {
                showMessage("提交失败");
            }
        }
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    private void loadDBData() {
        mContent = new PropertyTousujianyiContentItem();
        mContent.loadDBData(this);
        requestRefreshUI();
        if (mContent.types != null && mContent.types.size() > 0) {
            hideCustomProgressDialog();
        }
    }

    protected void refreshUI() {
        initYezhuxinxi();

        initText(R.id.call_wuye,
                getResources().getString(R.string.property_common_wuyedianhua)
                        + "(" + mContent.customer.propertyphone + ")");
    }

    private void initYezhuxinxi() {
        initText(R.id.yezhuxinxi_xingming, mContent.customer.username);
        initText(R.id.yezhuxinxi_dianhua, "(" + mContent.customer.userphone
                + ")");
        initText(R.id.yezhuxinxi_dizhi, mContent.customer.useraddress);

        if (mContent.customer.pictureurl != null
                && mContent.customer.pictureurl.size() > 0) {
            ImageView image = (ImageView) mRoot
                    .findViewById(R.id.yezhuxinxi_touxiang);
            ImageLoaderManager.getInstance().loadImage(
                    mContent.customer.pictureurl.get(0), image);
        }
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_tousujianyi);
        initText(R.id.detail, R.string.property_tousudan_detail);
        initView(R.id.back);
        initView(R.id.detail);
    }

    protected void initBody() {
        super.initBody();
        initTextHint(R.id.select_type,
                R.string.property_tousujianyi_xuanzetousuleixing);
        initText(R.id.select_type_value,
                R.string.property_tousujianyi_tousudan_item_type);

        initText(R.id.action_now, R.string.property_tousujianyi_action_tousu);

        EditText text = (EditText) mRoot.findViewById(R.id.problem_content);
        text.setHint(R.string.property_tousujianyi_tousuwentimiaoshu);

        initView(R.id.select_type_container);
        initView(R.id.action_now);
        initView(R.id.call_wuye);

        mPictureContainer = (ViewGroup) findViewById(R.id.list_hor_image);
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity();
            break;
        case R.id.select_type_container:
            clickSelectType();
            break;
        case R.id.action_now:
            clickActionNow();
            break;
        case R.id.call_wuye:
            clickCallWuye();
            break;
        }
    }

    private void gotoSubActivity() {
        Intent intent = new Intent();
        intent.putExtra("customer", mContent.customer);
        intent.setClass(this, PropertyMineTousuActivity.class);
        startActivity(intent);
    }

    private void clickActionNow() {
        Object obj = getTag(R.id.select_type);
        if (!(obj instanceof PropertyTypeItem)) {
            showMessage("请选择投诉类型!");
            return;
        }

        String desc = getTextContent(R.id.problem_content);
        if (TextUtils.isEmpty(desc)) {
            showMessage("请描述问题!");
            return;
        }

        final ArrayList<String> files = new ArrayList<String>();
        for (int i = 0; i < mPictureContainer.getChildCount(); i++) {
            String tag = (String) mPictureContainer.getChildAt(i).getTag();
            files.add(tag);
        }

        showCustomProgrssDialog();
        PropertyTousujianyiSubmitRequestInfo submit = new PropertyTousujianyiSubmitRequestInfo();
        submit.complaintcode = ((PropertyTypeItem) obj).type_id;
        submit.complaintdesc = desc;
        submit.icobject = files;
        ((PropertyTousujianyiMessageProcessProxy) mProcess)
                .submitTousujianyidan(this, submit, this);
    }

    private void clickCallWuye() {
        String phone = "tel:" + mContent.customer.propertyphone;
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void clickSelectType() {
        final ArrayList<PropertyItemInfo> datas = mContent.types;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                initText(R.id.select_type, ((PropertyTypeItem) info).type_name);
                setTag(R.id.select_type, info);
            }
        });
    }
}
