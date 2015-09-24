package com.smona.app.propertymanager.wupin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyStartupCameraActivity;
import com.smona.app.propertymanager.data.bean.PropertyBeanErshouwupinpinpais;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinContentItem;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinMessageProcessProxy;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinPinpaiRequestInfo;
import com.smona.app.propertymanager.wupin.process.PropertyErshouwupinSubmitRequestInfo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class PropertyWupinfabuActivity extends PropertyStartupCameraActivity {
    private static final String TAG = "PropertyWupinfabuActivity";

    private PropertyErshouwupinContentItem mItem;

    private boolean mIsModify = false;

    // type
    private PropertyErshouwupinTypeItem mTypes;
    private ArrayList<PropertyItemInfo> mPinpaiDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mWupinDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mXinjiuDatas = new ArrayList<PropertyItemInfo>();

    private String mWupinType = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin_fabu);
        aquireData();
        initViews();
        requestLoadData();
    }

    private void aquireData() {
        // modify info
        mItem = (PropertyErshouwupinContentItem) getIntent()
                .getParcelableExtra(PropertyConstants.DATA_ITEM_INFO);
        mIsModify = mItem != null;
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
        mWupinDatas.addAll(mTypes.wupins);
        mXinjiuDatas.addAll(mTypes.xinjius);
        requestRefreshUI();
    }

    private void loadPinpaiTypeData() {
        mTypes.loadPinpais(this);
        mPinpaiDatas.clear();
        mPinpaiDatas.addAll(mTypes.pinpais);
    }

    protected void refreshUI() {
        if (!mIsModify) {
            return;
        }

        View parent = mRoot.findViewById(R.id.wupintype);
        // wupinType
        int size = mWupinDatas.size();
        PropertyTypeItem wupinType = null;
        for (int i = 0; i < size; i++) {
            if (((PropertyTypeItem) mWupinDatas.get(i)).type_id
                    .equals(mItem.classcode)) {
                wupinType = ((PropertyTypeItem) mWupinDatas.get(i));
                break;
            }
        }

        if (wupinType != null) {
            initText(parent, R.id.select_type, mItem.classname);
            setTag(parent, R.id.select_type, wupinType);
        }

        // brand
        parent = mRoot.findViewById(R.id.pinpai);
        PropertyTypeItem pinpaiType = new PropertyTypeItem();
        pinpaiType.type_id = mItem.brandcode;
        pinpaiType.type_name = mItem.brand;

        initText(parent, R.id.select_type, mItem.brand);
        setTag(parent, R.id.select_type, pinpaiType);

        // good newcode
        parent = mRoot.findViewById(R.id.xinjiu);
        size = mXinjiuDatas.size();
        PropertyTypeItem xinjiuType = null;
        for (int i = 0; i < size; i++) {
            if (((PropertyTypeItem) mXinjiuDatas.get(i)).type_id
                    .equals(mItem.goodscode)) {
                xinjiuType = ((PropertyTypeItem) mXinjiuDatas.get(i));
                break;
            }
        }

        if (xinjiuType != null) {
            initText(parent, R.id.select_type, mItem.goosstatus);
            setTag(parent, R.id.select_type, xinjiuType);
        }

        initText(R.id.problem_content, mItem.goodsdesc);

        parent = mRoot.findViewById(R.id.goodsname);
        initText(parent, R.id.value, mItem.goodsname);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.value, mItem.username);
        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.value, mItem.userphone);

        for (int i = 0; i < mItem.picurl.size(); i++) {
            addImageView(mItem.picurl.get(i));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_ershouwupin_wupinfabu);
        if (mIsModify) {
            findViewById(R.id.detail).setVisibility(View.GONE);
        } else {
            findViewById(R.id.detail).setVisibility(View.VISIBLE);
            initText(R.id.detail, R.string.property_ershouwupin_mine);
            initView(R.id.detail);
        }
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        super.initBody();
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

        parent = mRoot.findViewById(R.id.goodsname);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_wupmingcheng);

        EditText text = (EditText) mRoot.findViewById(R.id.problem_content);
        text.setHint(R.string.property_ershouwupin_wupinfabu_shuru_wupinmiaoshu);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_lianxiren);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_dianhu);

        initView(R.id.publish);

        mPictureContainer = (ViewGroup) findViewById(R.id.list_hor_image);
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);
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
        case R.id.publish:
            actionPublish();
            break;
        }
    }

    private void actionPublish() {
        View parent = findViewById(R.id.wupintype);
        Object wupinType = getTag(parent, R.id.select_type);
        if (!(wupinType instanceof PropertyTypeItem)) {
            showMessage("请选择物品类型");
            return;
        }

        parent = findViewById(R.id.pinpai);
        Object pinpaiType = getTag(parent, R.id.select_type);
        if (!(pinpaiType instanceof PropertyTypeItem)) {
            showMessage("请选择品牌");
            return;
        }
        parent = findViewById(R.id.xinjiu);
        Object xinjiuType = getTag(parent, R.id.select_type);
        if (!(xinjiuType instanceof PropertyTypeItem)) {
            showMessage("请选择新旧程度");
            return;
        }
        parent = findViewById(R.id.goodsname);
        String goodsName = getTextContent(parent, R.id.value);
        if (TextUtils.isEmpty(goodsName)) {
            showMessage("请输入物品名称");
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

        final ArrayList<String> files = new ArrayList<String>();
        for (int i = 0; i < mPictureContainer.getChildCount(); i++) {
            String tag = (String) mPictureContainer.getChildAt(i).getTag();
            if (TextUtils.isEmpty(tag)) {
                showMessage(R.string.property_common_upload_control_hint);
                return;
            }
            files.add(tag);
        }

        PropertyErshouwupinSubmitRequestInfo request = new PropertyErshouwupinSubmitRequestInfo();
        if (mIsModify) {
            request.publishid = mItem.publishid;
            // modify info
            mItem.goodsdesc = wupinDesc;
            mItem.username = lianxiren;
            mItem.userphone = dianhua;
            mItem.goodsname = goodsName;
            mItem.picurl = files;

        }
        request.classcode = ((PropertyTypeItem) wupinType).type_id;
        request.newcode = ((PropertyTypeItem) xinjiuType).type_id;
        request.brandcode = ((PropertyTypeItem) pinpaiType).type_id;
        request.goodsdesc = wupinDesc;
        request.username = lianxiren;
        request.goodsname = goodsName;
        request.userphone = dianhua;
        request.icobject = files;

        ((PropertyErshouwupinMessageProcessProxy) mProcess)
                .submitErshouwupindan(this, request, this);
        showCustomProgrssDialog();
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        LogUtil.d(TAG, "info.iccode: " + info.iccode + "; content: " + content);
        if ("4910".equals(info.iccode)) {
            type = new TypeToken<PropertyBeanErshouwupinpinpais>() {
            }.getType();
            PropertyBeanErshouwupinpinpais bean = JsonUtils.parseJson(content,
                    type);
            bean.saveDataToDB(this);
            loadPinpaiTypeData();
        } else if ("5010".equals(info.iccode)) {
            if ("00".equals(info.answercode)) {
                if (mIsModify) {
                    showMessage("修改成功");
                    Intent intent = new Intent();
                    intent.putExtra(PropertyConstants.DATA_MODIFY_ITEM, mItem);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    showMessage("发布成功");
                    finish();
                }
            }
        }
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    private void initPinpaiTypes(String classCode) {
        PropertyErshouwupinPinpaiRequestInfo request = new PropertyErshouwupinPinpaiRequestInfo();
        request.classcode = classCode;
        ((PropertyErshouwupinMessageProcessProxy) mProcess)
                .requestErshouwupinPinpaiType(this, request, this);
        showCustomProgrssDialog();
    }

    private void clickChoidWupinType() {
        final ArrayList<PropertyItemInfo> datas = mWupinDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                String typeid = ((PropertyTypeItem) info).type_id;

                if (typeid.equals(mWupinType)) {
                    return;
                }

                // wupin
                View parent = mRoot.findViewById(R.id.wupintype);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
                setTag(parent, R.id.select_type, info);
                mWupinType = ((PropertyTypeItem) info).type_id;

                // pinpai
                parent = mRoot.findViewById(R.id.pinpai);
                initTextHint(parent, R.id.select_type,
                        R.string.property_ershouwupin_pinpaifenlei);
                initText(parent, R.id.select_type, "");
                setTag(parent, R.id.select_type, null);

                mPinpaiDatas.clear();
                initPinpaiTypes(((PropertyTypeItem) info).type_id);

                if (mIsModify) {
                    mItem.brand = "";
                    mItem.brandcode = "";
                    mItem.classcode = ((PropertyTypeItem) info).type_id;
                    mItem.classname = ((PropertyTypeItem) info).type_name;
                }
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

                if (mIsModify) {
                    mItem.goodscode = ((PropertyTypeItem) info).type_id;
                    mItem.goosstatus = ((PropertyTypeItem) info).type_name;
                }
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

                if (mIsModify) {
                    mItem.brandcode = ((PropertyTypeItem) info).type_id;
                    mItem.brand = ((PropertyTypeItem) info).type_name;
                }
            }
        });
    }
}
