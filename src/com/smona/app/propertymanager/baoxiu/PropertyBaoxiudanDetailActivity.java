package com.smona.app.propertymanager.baoxiu;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.baoxiu.process.PropertyWuyebaoxiuDetailRequestInfo;
import com.smona.app.propertymanager.baoxiu.process.PropertyWuyebaoxiuMessageProcessProxy;
import com.smona.app.propertymanager.baoxiu.process.PropertyWuyebaoxiuSubmitPingjiaRequestInfo;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyWuyebaoxiudanContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

public class PropertyBaoxiudanDetailActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyBaoxiudanDetailActivity";

    private PropertyWuyebaoxiudanContentItem mItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyebaoxiu_baoxiudan_detail);
        acquireData();
        initViews();
        requestLoadData();
    }

    private void acquireData() {
        mItem = (PropertyWuyebaoxiudanContentItem) getIntent()
                .getParcelableExtra("iteminfo");
    }

    protected void loadData() {
        requestData();
    }

    private void requestData() {
        mProcess = new PropertyWuyebaoxiuMessageProcessProxy();
        mRequestInfo = new PropertyWuyebaoxiuDetailRequestInfo();
        ((PropertyWuyebaoxiuDetailRequestInfo) mRequestInfo).repairid = mItem.repairid;
        ((PropertyWuyebaoxiuMessageProcessProxy) mProcess)
                .requestWuyebaoxiudanDetail(this, mRequestInfo, this);
    }

    protected void saveData(String content) {
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        if ("3510".equals(info.iccode)) {
            type = new TypeToken<PropertyWuyebaoxiudanContentItem>() {
            }.getType();
            mItem = JsonUtils.parseJson(content, type);
            requestRefreshUI();
        } else if ("3610".equals(info.iccode)) {
            if ("00".equals(info.answercode)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showMessage("评价成功");
                    }
                });
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showMessage("评价失败");
                    }
                });
            }
        }

    }

    protected void failedRequest() {

    }

    protected void refreshUI() {
        View view = findViewById(R.id.baoxiu_time);
        initText(view, R.id.value, mItem.requesttime);

        view = findViewById(R.id.baoxiu_type);
        initText(view, R.id.value, mItem.repairname);

        view = findViewById(R.id.baoxiu_problem);
        initText(view, R.id.value, mItem.repairdesc);

        view = findViewById(R.id.baoxiu_paidan_time);
        initText(view, R.id.value, mItem.workbegintime);

        view = findViewById(R.id.baoxiu_paidan_weixiuren);
        initText(view, R.id.value, mItem.worker);

        view = findViewById(R.id.baoxiu_wancheng_time);
        initText(view, R.id.value, mItem.workendtime);

        view = findViewById(R.id.baoxiu_wancheng_result);
        initText(view, R.id.value, mItem.repairstatus);

        view = findViewById(R.id.baoxiu_wancheng_fee);
        initText(view, R.id.value, mItem.repairstatus);

        // pingjia
        if (mItem.evalute != null) {
            for (int i = 0; i < mItem.evalute.size(); i++) {
                String code = mItem.evalute.get(i).evalcode;
                String num = mItem.evalute.get(i).evalvalue;
                if ("1".equals(code) && !TextUtils.isEmpty(num)) {
                    initRatingBar(R.id.wancheng_zhiliang, num);
                } else if ("2".equals(code) && !TextUtils.isEmpty(num)) {
                    initRatingBar(R.id.wancheng_taidu, num);
                } else if ("3".equals(code) && !TextUtils.isEmpty(num)) {
                    initRatingBar(R.id.wancheng_jishixing, num);
                } else if ("4".equals(code) && !TextUtils.isEmpty(num)) {
                    initRatingBar(R.id.wancheng_price, num);
                }
            }
        }

        // piture
        ViewGroup list = (ViewGroup) mRoot.findViewById(R.id.list_hor_image);
        for (int i = 0; i < ((PropertyWuyebaoxiudanContentItem) mItem).repairpicture
                .size(); i++) {
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
            ImageLoaderManager
                    .getInstance()
                    .loadImage(
                            ((PropertyWuyebaoxiudanContentItem) mItem).repairpicture
                                    .get(i), image);
        }
    }

    private void initRatingBar(int parentId, String num) {
        View view = findViewById(parentId);
        RatingBar bar = (RatingBar) view.findViewById(R.id.pingjia_result);
        bar.setNumStars(Integer.valueOf(num));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_wuyebaoxiu_baoxiudandetail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View view = findViewById(R.id.baoxiu_time);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_time);

        view = findViewById(R.id.baoxiu_type);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_type);

        view = findViewById(R.id.baoxiu_problem);
        initText(view, R.id.name,
                R.string.property_wuyebaoxiu_baoxiuwentimiaoshu);

        view = findViewById(R.id.baoxiu_paidan_time);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_paidan_time);

        view = findViewById(R.id.baoxiu_paidan_weixiuren);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_weixiuren);

        view = findViewById(R.id.baoxiu_wancheng_time);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_wancheng_time);

        view = findViewById(R.id.baoxiu_wancheng_result);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_wancheng_result);

        view = findViewById(R.id.baoxiu_wancheng_fee);
        initText(view, R.id.name,
                R.string.property_wuyebaoxi_baoxiudan_item_wancheng_fee);

        initText(R.id.submit_pingjia, R.string.property_common_submit_pingjia);
        initView(R.id.submit_pingjia);

        // pingjia
        view = findViewById(R.id.wancheng_zhiliang);
        initText(view, R.id.pingjia_name,
                R.string.property_wuyebaoxi_baoxiudan_item_wancheng_zhiliang);
        view = findViewById(R.id.wancheng_taidu);
        initText(view, R.id.pingjia_name,
                R.string.property_wuyebaoxi_baoxiudan_item_wancheng_taidu);
        view = findViewById(R.id.wancheng_jishixing);
        initText(view, R.id.pingjia_name,
                R.string.property_wuyebaoxi_baoxiudan_item_wancheng_jishixing);
        view = findViewById(R.id.wancheng_price);
        initText(view, R.id.pingjia_name,
                R.string.property_wuyebaoxi_baoxiudan_item_wancheng_price);
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
        PropertyWuyebaoxiuSubmitPingjiaRequestInfo requestInfo = new PropertyWuyebaoxiuSubmitPingjiaRequestInfo();

        addPingjiaInfo(requestInfo, R.id.wancheng_zhiliang);
        addPingjiaInfo(requestInfo, R.id.wancheng_taidu);
        addPingjiaInfo(requestInfo, R.id.wancheng_jishixing);
        addPingjiaInfo(requestInfo, R.id.wancheng_price);

        requestInfo.repairid = mItem.repairid;
        ((PropertyWuyebaoxiuMessageProcessProxy) mProcess)
                .submitWuyebaoxiudanPingjia(this, requestInfo, this);
    }

    private void addPingjiaInfo(
            PropertyWuyebaoxiuSubmitPingjiaRequestInfo requestInfo, int resid) {
        View parent = findViewById(resid);
        RatingBar bar = (RatingBar) parent.findViewById(R.id.pingjia_result);
        int num = bar.getNumStars();
        if (num > 0) {
            requestInfo.add("1", num);
        }
    }
}
