package com.smona.app.propertymanager.tousu;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyTousujianyidanContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiDetailRequestInfo;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiMessageProcessProxy;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiSubmitPingjiaRequestInfo;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

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
        showCustomProgrssDialog();
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyTousujianyidanContentItem>() {
        }.getType();
        mItem = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "mItem: " + mItem);

        requestRefreshUI();
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    protected void refreshUI() {
        View view = findViewById(R.id.tousu_time);
        initText(view, R.id.value, mItem.requesttime);

        view = findViewById(R.id.tousu_type);
        initText(view, R.id.value, mItem.complaintcode);

        view = findViewById(R.id.tousu_problem);
        initText(view, R.id.value, mItem.complaintdesc);
        
        initText(view, R.id.submit_pingjia, R.string.property_common_submit_pingjia);
        initView(R.id.submit_pingjia);

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
        
        // pingjia
        if (mItem.evalute.size() > 0) {
            String code = mItem.evalute.get(0).evalcode;
            String num = mItem.evalute.get(0).evalvalue;
            if ("5".equals(code)) {
                view = findViewById(R.id.pingjia_fuwutaidu);
                RatingBar bar = (RatingBar) view
                        .findViewById(R.id.pingjia_result);
                bar.setNumStars(Integer.valueOf(num));
            } else if ("6".equals(code)) {
                view = findViewById(R.id.pingjia_jishixing);
                RatingBar bar = (RatingBar) view
                        .findViewById(R.id.pingjia_result);
                bar.setNumStars(Integer.valueOf(num));
            }
        }
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
        
        initText(view, R.id.submit_pingjia,
                R.string.property_common_submit_pingjia);
        initView(R.id.submit_pingjia);

    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.submit_pingjia:
            clickSubmitPingjia();
            break;
        }
    }
    
    private void clickSubmitPingjia () {
        PropertyTousujianyiSubmitPingjiaRequestInfo requestInfo = new PropertyTousujianyiSubmitPingjiaRequestInfo();

        addPingjiaInfo(requestInfo, R.id.pingjia_fuwutaidu);
        addPingjiaInfo(requestInfo, R.id.pingjia_jishixing);

        requestInfo.repairid = mItem.complaintid;
        ((PropertyTousujianyiMessageProcessProxy) mProcess)
                .submitTousujianyidanPingjia(this, requestInfo, this);
    }

    private void addPingjiaInfo(
            PropertyTousujianyiSubmitPingjiaRequestInfo requestInfo, int resid) {
        View parent = findViewById(resid);
        RatingBar bar = (RatingBar) parent.findViewById(R.id.pingjia_result);
        int num = bar.getNumStars();
        if (num > 0) {
            requestInfo.add("1", num);
        }
    }
}
