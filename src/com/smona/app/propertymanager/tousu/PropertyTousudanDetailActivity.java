package com.smona.app.propertymanager.tousu;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyPictureZoomActivity;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTousujianyidanContentItem;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiDetailRequestInfo;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiMessageProcessProxy;
import com.smona.app.propertymanager.tousu.process.PropertyTousujianyiSubmitPingjiaRequestInfo;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

public class PropertyTousudanDetailActivity extends PropertyPictureZoomActivity {
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
                .getParcelableExtra(PropertyConstants.DATA_ITEM_INFO);
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        showCustomProgrssDialog();
        mProcess = new PropertyTousujianyiMessageProcessProxy();
        mRequestInfo = new PropertyTousujianyiDetailRequestInfo();
        ((PropertyTousujianyiDetailRequestInfo) mRequestInfo).complaintid = mItem.complaintid;
        ((PropertyTousujianyiMessageProcessProxy) mProcess)
                .requestTousujianyidanDetail(this, mRequestInfo, this);
    }

    protected void saveData(String content) {
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        if ("4010".equals(info.iccode)) {
            if ("00".equals(info.answercode)) {
                type = new TypeToken<PropertyTousujianyidanContentItem>() {
                }.getType();
                mItem = JsonUtils.parseJson(content, type);
                requestRefreshUI();
            } else {
                showMessage("失败");
            }
        } else if ("4110".equals(info.iccode)) {
            if ("00".equals(info.answercode)) {
                showMessage("评价成功");
            } else {
                showMessage("评价失败");
            }
        }
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    protected void refreshUI() {
        View view = findViewById(R.id.tousu_time);
        initText(view, R.id.value, mItem.requesttime);

        view = findViewById(R.id.tousu_type);
        initText(view, R.id.value, mItem.complaintname);

        view = findViewById(R.id.tousu_problem);
        initText(view, R.id.value, mItem.complaintdesc);

        view = findViewById(R.id.tousu_wuyefankui);
        initText(view, R.id.value, mItem.feedback);

        view = findViewById(R.id.tousu_fankuishijian);
        initText(view, R.id.value, mItem.feedbacktime);

        // pingjia
        if (mItem.evalute != null) {
            for (int i = 0; i < mItem.evalute.size(); i++) {
                String code = mItem.evalute.get(i).evalcode;
                String num = mItem.evalute.get(i).evalvalue;
                if ("5".equals(code) && !TextUtils.isEmpty(num)) {
                    initRatingBar(R.id.pingjia_fuwutaidu, num);
                } else if ("6".equals(code) && !TextUtils.isEmpty(num)) {
                    initRatingBar(R.id.pingjia_jishixing, num);
                }
            }
        }

        // piture
        mPictureContainer = (ViewGroup) mRoot.findViewById(R.id.list_hor_image);
        if (mItem.complaintpicture != null) {
            for (int i = 0; i < mItem.complaintpicture.size(); i++) {
                addImageView(mItem.complaintpicture.get(i));
            }
        }
    }

    private void initRatingBar(int parentId, String num) {
        View view = findViewById(parentId);
        RatingBar bar = (RatingBar) view.findViewById(R.id.pingjia_result);
        bar.setRating(Integer.valueOf(num));
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

        initText(R.id.submit_pingjia, R.string.property_common_submit_pingjia);
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

    private void clickSubmitPingjia() {
        if (mItem == null || TextUtils.isEmpty(mItem.feedback)) {
            return;
        }

        showCustomProgrssDialog();
        PropertyTousujianyiSubmitPingjiaRequestInfo requestInfo = new PropertyTousujianyiSubmitPingjiaRequestInfo();

        addPingjiaInfo(requestInfo, R.id.pingjia_fuwutaidu, "5");
        addPingjiaInfo(requestInfo, R.id.pingjia_jishixing, "6");

        requestInfo.complaintid = mItem.complaintid;
        ((PropertyTousujianyiMessageProcessProxy) mProcess)
                .submitTousujianyidanPingjia(this, requestInfo, this);

    }

    private void addPingjiaInfo(
            PropertyTousujianyiSubmitPingjiaRequestInfo requestInfo, int resid,
            String eveCode) {
        View parent = findViewById(resid);
        RatingBar bar = (RatingBar) parent.findViewById(R.id.pingjia_result);
        int num = (int) bar.getRating();
        if (num > 0) {
            requestInfo.add(eveCode, num);
        }
    }

    @Override
    protected boolean isDelete() {
        // TODO Auto-generated method stub
        return false;
    }
}
