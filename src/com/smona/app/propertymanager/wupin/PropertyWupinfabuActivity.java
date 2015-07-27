package com.smona.app.propertymanager.wupin;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinMessageProcessProxy;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinSubmitRequestInfo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class PropertyWupinfabuActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyWupinfabuActivity";

    // type
    private PropertyErshouwupinTypeItem mTypes;
    private ArrayList<PropertyItemInfo> mPinpaiDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mWupinDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mXinjiuDatas = new ArrayList<PropertyItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin_fabu);
        initViews();
        requestLoadData();
    }

    protected void loadData() {
        requestData();
        loadDBData();
    }

    private void requestData() {
        mProcess = new PropertyErshouwupinMessageProcessProxy();
    }

    private void loadDBData() {
        mTypes = new PropertyErshouwupinTypeItem();
        mTypes.loadDBData(this);
        mPinpaiDatas.addAll(mTypes.pinpais);
        mWupinDatas.addAll(mTypes.wupins);
        mXinjiuDatas.addAll(mTypes.xinjius);

        requestRefreshUI();
    }

    protected void refreshUI() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_ershouwupin_wupinfabu);
        initText(R.id.detail, R.string.property_ershouwupin_mine);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.wupintype);
        initTextHint(parent, R.id.select_type,
                R.string.property_ershouwupin_xuanzewupinfenlei);
        initText(parent, R.id.select_type_value,
                R.string.property_ershouwupin_detail_wupinfenlei);
        initView(R.id.wupintype);

        parent = mRoot.findViewById(R.id.pinpai);
        initTextHint(parent, R.id.select_type,
                R.string.property_ershouwupin_pinpaifenlei);
        initText(parent, R.id.select_type_value,
                R.string.property_ershouwupin_detail_pinpai);
        initView(R.id.pinpai);

        parent = mRoot.findViewById(R.id.xinjiu);
        initTextHint(parent, R.id.select_type,
                R.string.property_ershouwupin_xuanzexinjiu);
        initText(parent, R.id.select_type_value,
                R.string.property_ershouwupin_item_xinjiu);
        initView(R.id.xinjiu);

        EditText text = (EditText) mRoot.findViewById(R.id.problem_content);
        text.setHint(R.string.property_ershouwupin_wupinfabu_shuru_wupinmiaoshu);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_lianxiren);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_dianhu);

        initView(R.id.start_camera);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity(PropertyMineWupinActivity.class);
            break;
        case R.id.wupintype:
            clickChoidWupinType();
            break;
        case R.id.pinpai:
            clickChoicePinpai();
            break;
        case R.id.xinjiu:
            clickChoiceXinjiu();
            break;
        case R.id.start_camera:
            actionCamera();
            break;
        case R.id.publish:
            actionPublish();
            break;
        }
    }

    private void actionCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        startActivityForResult(intent, 1);
    }
    
    
    @SuppressWarnings("deprecation")
    private void actionPublish() {
        View parent = findViewById(R.id.wupintype);
        Object wupinType = getTag(parent, R.id.select_type);
        if (!(wupinType instanceof PropertyTypeItem)) {
            showMessage("请选择物品类型");
            return;
        }
        parent = findViewById(R.id.area);
        Object pinpaiType = getTag(parent, R.id.pinpai);
        if (!(pinpaiType instanceof PropertyTypeItem)) {
            showMessage("请选择品牌");
            return;
        }
        parent = findViewById(R.id.xinjiu);
        Object xinjiuType = getTag(parent, R.id.select_type);
        if (!(xinjiuType instanceof PropertyTypeItem)) {
            showMessage("请选择新旧");
            return;
        }
        String wupinDesc = getTextContent(R.id.problem_content);
        if (TextUtils.isEmpty(wupinDesc)) {
            showMessage("请填写物品描述");
            return;
        }
        parent = findViewById(R.id.lianxiren);
        String lianxiren = getTextContent(parent, R.id.value);
        if (TextUtils.isEmpty(lianxiren)) {
            showMessage("请输入联系姓名");
            return;
        }

        parent = findViewById(R.id.dianhua);
        String dianhua = getTextContent(parent, R.id.value);
        if (TextUtils.isEmpty(dianhua)) {
            showMessage("请输入联系电话");
            return;
        }

        PropertyErshouwupinSubmitRequestInfo request = new PropertyErshouwupinSubmitRequestInfo();
        request.classcode = ((PropertyTypeItem) wupinType).type_id;
        request.newcode = ((PropertyTypeItem) xinjiuType).type_id;
        request.brandcode = ((PropertyTypeItem) pinpaiType).type_id;
        request.goodsdesc = ((PropertyTypeItem) wupinType).type_name;
        request.goodsdesc = wupinDesc;
        request.username = lianxiren;
        request.userphone = dianhua;

        ((PropertyErshouwupinMessageProcessProxy) mProcess)
                .submitErshouwupindan(this, request, this);
        showDialog(0);
    }
    
    protected void saveData(String content) {
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    private void clickChoidWupinType() {
        final ArrayList<PropertyItemInfo> datas = mWupinDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.wupintype);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
                setTag(parent, R.id.select_type, info);
            }
        });
    }

    private void clickChoiceXinjiu() {
        final ArrayList<PropertyItemInfo> datas = mXinjiuDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.xinjiu);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
                setTag(parent, R.id.select_type, info);
            }
        });
    }

    private void clickChoicePinpai() {
        final ArrayList<PropertyItemInfo> datas = mPinpaiDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.pinpai);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
                setTag(parent, R.id.select_type, info);
            }
        });
    }
}
