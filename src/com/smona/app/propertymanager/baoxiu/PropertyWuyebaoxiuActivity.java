package com.smona.app.propertymanager.baoxiu;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.baoxiu.process.PropertyWuyebaoxiuMessageProcessProxy;
import com.smona.app.propertymanager.baoxiu.process.PropertyWuyebaoxiuSubmitRequestInfo;
import com.smona.app.propertymanager.common.PropertyStartupCameraActivity;
import com.smona.app.propertymanager.data.bean.PropertyBeanWuyebaoxiu;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.data.model.PropertyWuyebaoxiuContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PropertyWuyebaoxiuActivity extends PropertyStartupCameraActivity {
    private static final String TAG = "PropertyWuyebaoxiuActivity";

    private PropertyWuyebaoxiuContentItem mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyebaoxiu);
        initViews();
        requestLoadData();
    }

    protected void loadData() {
        requestData();
    }

    protected void requestData() {
        showCustomProgrssDialog();

        mContent = new PropertyWuyebaoxiuContentItem();
        mProcess = new PropertyWuyebaoxiuMessageProcessProxy();
        ((PropertyWuyebaoxiuMessageProcessProxy) mProcess).requestWuyebaoxiu(
                this, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo bean = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "bean: " + bean);
        if ("3210".equals(bean.iccode) && "00".equals(bean.answercode)) {
            type = new TypeToken<PropertyBeanWuyebaoxiu>() {
            }.getType();
            PropertyBeanWuyebaoxiu baoxiu = JsonUtils.parseJson(content, type);
            baoxiu.saveDataToDB(this);
            loadDBData();
        } else if ("3310".equals(bean.iccode)) {
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
        mContent.loadDBData(this);
        requestRefreshUI();
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

        ImageView image = (ImageView) mRoot
                .findViewById(R.id.yezhuxinxi_touxiang);
        ImageLoaderManager.getInstance().loadImage(
                mContent.customer.pictureurl.get(0), image);
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_wuyebaoxiu);
        initText(R.id.detail, R.string.property_wuyebaoxiu_baoxiudandetail);
        initView(R.id.back);
        initView(R.id.detail);
    }

    protected void initBody() {
        super.initBody();
        initTextHint(R.id.select_type,
                R.string.property_common_xuanzebaoxiuleixing);
        initText(R.id.select_type_value,
                R.string.property_wuyebaoxi_baoxiudan_item_type);

        initText(R.id.action_now,
                R.string.property_wuyebaoxiu_now_action_baoxiu);

        initView(R.id.select_type_container);
        initView(R.id.action_now);
        initView(R.id.call_wuye);

        mPictureContainer = (ViewGroup) findViewById(R.id.list_hor_image);
    }

    protected void clickView(View v) {
        super.clickView(v);
        int id = v.getId();
        if (id == R.id.back) {
            finish();
            return;
        }

        switch (id) {
        case R.id.action_now:
            clickActionNow();
            break;
        case R.id.select_type_container:
            clickSelectType();
            break;
        case R.id.detail:
            gotoSubActivity();
            break;
        case R.id.call_wuye:
            clickCallWuye();
            break;
        }
    }

    private void gotoSubActivity() {
        Intent intent = new Intent();
        intent.putExtra("customer", mContent.customer);
        intent.setClass(this, PropertyBaoxiudanActivity.class);
        startActivity(intent);
    }

    private void clickActionNow() {

        Object obj = getTag(R.id.select_type);
        if (!(obj instanceof PropertyTypeItem)) {
            showMessage("请选择报修类型!");
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
            if (TextUtils.isEmpty(tag)) {
                showMessage(R.string.property_common_upload_control_hint);
                return;
            }
            files.add(tag);
        }

        showCustomProgrssDialog();
        PropertyWuyebaoxiuSubmitRequestInfo submit = new PropertyWuyebaoxiuSubmitRequestInfo();
        submit.repaircode = ((PropertyTypeItem) obj).type_id;
        submit.repairdesc = desc;
        submit.icobject = files;
        ((PropertyWuyebaoxiuMessageProcessProxy) mProcess).submitWuyebaoxiudan(
                this, submit, this);
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
