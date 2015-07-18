package com.smona.app.propertymanager.tousu;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.baoxiu.PropertyWuyebaoxiuMessageProcess;
import com.smona.app.propertymanager.data.bean.PropertyBeanTousujianyi;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTousujianyiContentItem;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class PropertyTousujianyiActivity extends PropertyBaseActivity {

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
        mProcess = new PropertyWuyebaoxiuMessageProcess();
        String content = mProcess.getTousujianyi(this);
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyBeanTousujianyi>() {
        }.getType();
        PropertyBeanTousujianyi bean = JsonUtils.parseJson(content, type);
        bean.saveDataToDB(this);
    }

    private void loadDBData() {
        mContent = new PropertyTousujianyiContentItem();
        mContent.loadDBData(this);
        LogUtil.d(TAG, "loadDBData mContent: " + mContent);

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
        initText(R.id.title, R.string.property_home_tousujianyi);
        initText(R.id.detail, R.string.property_tousudan_detail);
        initView(R.id.back);
        initView(R.id.detail);
    }

    protected void initBody() {
        initText(R.id.select_type,
                R.string.property_tousujianyi_xuanzetousuleixing);
        initText(R.id.action_now, R.string.property_tousujianyi_action_tousu);

        EditText text = (EditText) mRoot.findViewById(R.id.problem_content);
        text.setHint(R.string.property_tousujianyi_tousuwentimiaoshu);

        initView(R.id.select_type_container);
        initView(R.id.start_camera);
        initView(R.id.action_now);
        initView(R.id.call_wuye);
    }

    @Override
    protected void clickView(View v) {
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
        case R.id.start_camera:
            actionCamera();
            break;
        }
    }

    private void gotoSubActivity() {
        Intent intent = new Intent();
        intent.putExtra("customer", mContent.customer);
        intent.setClass(this, PropertyMineTousuActivity.class);
        startActivity(intent);
    }
    
    private void actionCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        startActivityForResult(intent, 1);
    }

    private void clickActionNow() {

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
                initText(R.id.select_type_value,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }
}
