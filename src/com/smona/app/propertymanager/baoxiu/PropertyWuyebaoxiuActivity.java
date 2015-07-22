package com.smona.app.propertymanager.baoxiu;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.PropertyMessageProcessProxy;
import com.smona.app.propertymanager.R;
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
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class PropertyWuyebaoxiuActivity extends PropertyBaseActivity {
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
        loadDBData();
    }

    protected void requestData() {
        mContent = new PropertyWuyebaoxiuContentItem();
        mProcess = new PropertyMessageProcessProxy();
        mProcess.requestWuyebaoxiu(this, this);
    }
    
    protected void saveData(String content) {
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyBeanWuyebaoxiu>() {
        }.getType();
        PropertyBeanWuyebaoxiu bean = JsonUtils.parseJson(content, type);
        bean.saveDataToDB(this);
        loadDBData();
    }
    
    protected void failedRequest() {

    }   

    private void loadDBData() {
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
        initText(R.id.title, R.string.property_home_wuyebaoxiu);
        initText(R.id.detail, R.string.property_wuyebaoxiu_baoxiudandetail);
        initView(R.id.back);
        initView(R.id.detail);
    }

    protected void initBody() {
        initTextHint(R.id.select_type, R.string.property_common_xuanzebaoxiuleixing);
        initText(R.id.select_type_value, R.string.property_wuyebaoxi_baoxiudan_item_type);

        initText(R.id.action_now,
                R.string.property_wuyebaoxiu_now_action_baoxiu);

        initView(R.id.select_type_container);
        initView(R.id.start_camera);
        initView(R.id.action_now);
        initView(R.id.call_wuye);
        
    }

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
        intent.setClass(this, PropertyBaoxiudanActivity.class);
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
                initText(R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
            }
        });
    }
}
