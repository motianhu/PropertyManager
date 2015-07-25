package com.smona.app.propertymanager.baoxiu;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyWuyebaoxiudanContentItem;
import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;

import android.os.Bundle;
import android.view.View;

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
        loadDBData();
    }

    private void requestData() {
        mProcess = new PropertyMessageProcessProxy();
        mProcess.requestWuyebaoxiudanDetail(this, this);
    }

    protected void saveData(String content) {
        LogUtil.d(TAG, "content: " + content);
        Type type = new TypeToken<PropertyWuyebaoxiudanContentItem>() {
        }.getType();
        mItem = JsonUtils.parseJson(content, type);
        loadDBData();
    }

    protected void failedRequest() {

    }

    private void loadDBData() {
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

        requestRefreshUI();
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
        }
    }
}
