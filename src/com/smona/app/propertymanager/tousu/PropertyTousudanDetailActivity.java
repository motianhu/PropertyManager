package com.smona.app.propertymanager.tousu;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyTousujianyidanContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiDetailRequestInfo;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiMessageProcessProxy;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PropertyTousudanDetailActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyTousudanDetailActivity";

    private PropertyTousujianyidanContentItem mItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_tousujianyi_tousudan_detail);
        acquireData();
        initViews();
        requestLoadData();
    }

    private void acquireData() {
        mItem = (PropertyTousujianyidanContentItem) getIntent()
                .getParcelableExtra("iteminfo");
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        mProcess = new PropertyTousujianyiMessageProcessProxy();
        mRequestInfo = new PropertyTousujianyiDetailRequestInfo();
        ((PropertyTousujianyiDetailRequestInfo) mRequestInfo).complaintid = mItem.complaintid;
        ((PropertyTousujianyiMessageProcessProxy) mProcess)
                .requestTousujianyidanDetail(this, mRequestInfo, this);
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyTousujianyidanContentItem>() {
        }.getType();
        mItem = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "mItem: " + mItem);

        requestRefreshUI();
    }

    protected void failedRequest() {

    }

    protected void refreshUI() {
        View view = findViewById(R.id.tousu_time);
        initText(view, R.id.value, mItem.requesttime);

        view = findViewById(R.id.tousu_type);
        initText(view, R.id.value, mItem.complaintcode);

        view = findViewById(R.id.tousu_problem);
        initText(view, R.id.value, mItem.complaintdesc);

        ViewGroup list = (ViewGroup) mRoot.findViewById(R.id.list_hor_image);
        for (int i = 0; i < mItem.complaintpicture.size(); i++) {
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
            ImageLoaderManager.getInstance().loadImage(
                    mItem.complaintpicture.get(i), image);
        }

        view = findViewById(R.id.tousu_wuyefankui);
        initText(view, R.id.value, mItem.feedback);

        view = findViewById(R.id.tousu_fankuishijian);
        initText(view, R.id.value, mItem.feedbacktime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_tousujianyi_tousu_detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View view = findViewById(R.id.tousu_time);
        initText(view, R.id.name,
                R.string.property_tousujianyi_tousudan_item_time);
        view = findViewById(R.id.tousu_type);
        initText(view, R.id.name,
                R.string.property_tousujianyi_tousudan_item_type);
        view = findViewById(R.id.tousu_problem);
        initText(view, R.id.name,
                R.string.property_tousujianyi_tousuwentimiaoshu);

        view = findViewById(R.id.tousu_wuyefankui);
        initText(view, R.id.name,
                R.string.property_tousujianyi_tousudan_item_wuyefankui);
        view = findViewById(R.id.tousu_fankuishijian);
        initText(view, R.id.name,
                R.string.property_tousujianyi_tousudan_item_fankuishijian);

        view = findViewById(R.id.pingjia_fuwutaidu);
        initText(view, R.id.pingjia_name,
                R.string.property_tousujianyi_tousudan_item_tousu_taidu);
        view = findViewById(R.id.pingjia_jishixing);
        initText(view, R.id.pingjia_name,
                R.string.property_tousujianyi_tousudan_item_tousu_jishixing);

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
