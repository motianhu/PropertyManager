package com.smona.app.propertymanager.tousu;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyTousujianyidanContentItem;
import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
        loadDBData();
    }

    private void requestData() {
        mProcess = new PropertyMessageProcessProxy();
        mProcess.requestTousujianyidanDetail(this, this);
    }

    protected void saveData(String content) {
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyTousujianyidanContentItem>() {
        }.getType();
        mItem = JsonUtils.parseJson(content, type);

        loadDBData();
    }

    protected void failedRequest() {

    }

    private void loadDBData() {
        View view = findViewById(R.id.tousu_time);
        initText(view, R.id.value, mItem.requesttime);

        view = findViewById(R.id.tousu_type);
        initText(view, R.id.value, mItem.complaintcode);

        view = findViewById(R.id.tousu_problem);
        initText(view, R.id.value, mItem.complaintdesc);

        view = findViewById(R.id.tousudan_picture);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        ImageLoaderManager.getInstance().loadImage(mItem.complaintpicture,
                imageView);

        view = findViewById(R.id.tousu_wuyefankui);
        initText(view, R.id.value, mItem.feedback);

        view = findViewById(R.id.tousu_fankuishijian);
        initText(view, R.id.value, mItem.feedbacktime);

        requestRefreshUI();
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
