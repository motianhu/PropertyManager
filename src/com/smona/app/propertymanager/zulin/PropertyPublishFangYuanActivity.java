package com.smona.app.propertymanager.zulin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyStartupCameraActivity;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinContentItem;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinMessageProcessProxy;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinSubmitRequestInfo;

public class PropertyPublishFangYuanActivity extends
        PropertyStartupCameraActivity {
    private static final String TAG = "PropertyPublishFangYuanActivity";

    private PropertyFangwuzulinContentItem mItem;

    private boolean mIsModify = false;

    // type
    private PropertyFangwuzulinTypeItem mTypes;
    private ArrayList<PropertyItemInfo> mYewuDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mHuxingDatas = new ArrayList<PropertyItemInfo>();
    private ArrayList<PropertyItemInfo> mAreaDatas = new ArrayList<PropertyItemInfo>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_fangwuzulin_fabufangyuan);
        aquireData();
        initViews();
    }

    private void aquireData() {
        mProcess = new PropertyFangwuzulinMessageProcessProxy();
        initType();

        // modify info
        mItem = (PropertyFangwuzulinContentItem) getIntent()
                .getParcelableExtra(PropertyConstants.DATA_ITEM_INFO);
        LogUtil.d(TAG, "mItem: " + mItem);

        mIsModify = mItem != null;
        if (mIsModify) {
            requestRefreshUI();
        }
    }

    private void initType() {
        mTypes = new PropertyFangwuzulinTypeItem();
        mTypes.loadDBData(this);
        mHuxingDatas.addAll(mTypes.hourse);
        mAreaDatas.addAll(mTypes.areas);

        initYewuDatas();
    }

    private void initYewuDatas() {
        String[] ids = getResources().getStringArray(
                R.array.fangwuzulin_ywtype_id);
        String[] ywnames = getResources().getStringArray(
                R.array.fangwuzulin_ywtype_name);

        for (int i = 0; i < ids.length; i++) {
            PropertyTypeItem item = new PropertyTypeItem();
            item.type_id = ids[i];
            item.type_name = ywnames[i];
            mYewuDatas.add(item);
        }
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_fangwuzulin_publish_fangyuan);
        if (mIsModify) {
            findViewById(R.id.detail).setVisibility(View.GONE);
        } else {
            findViewById(R.id.detail).setVisibility(View.VISIBLE);
            initText(R.id.detail, R.string.property_fangwuzulin_mine);
            initView(R.id.detail);
        }
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        super.initBody();
        View parent = mRoot.findViewById(R.id.ywtype);
        initTextHint(parent, R.id.select_type,
                R.string.property_fangwuzulin_xuanzeyewuleixing);
        initText(parent, R.id.select_type_value,
                R.string.property_fangwuzulin_item_yewuleixing);
        initView(R.id.ywtype);

        parent = mRoot.findViewById(R.id.area);
        initTextHint(parent, R.id.select_type,
                R.string.property_fangwuzulin_arealeixing);
        initText(parent, R.id.select_type_value,
                R.string.property_fangwuzulin_item_area);
        initView(R.id.area);

        parent = mRoot.findViewById(R.id.housetype);
        initTextHint(parent, R.id.select_type,
                R.string.property_fangwuzulin_xuanzehuxing);
        initText(parent, R.id.select_type_value,
                R.string.property_fangwuzulin_item_huxing);
        initView(R.id.housetype);

        EditText text = (EditText) mRoot.findViewById(R.id.problem_content);
        text.setHint(R.string.property_fangwuzulin_fangyuanfabu_shurupeitaomiaoshu);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_lianxiren);

        parent = mRoot.findViewById(R.id.weizhi);
        initText(parent, R.id.name,
                R.string.property_fangwuzulin_publish_position);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_dianhu);

        initView(R.id.publish);

        mPictureContainer = (ViewGroup) findViewById(R.id.list_hor_image);
    }

    @Override
    public void refreshUI() {
        if (!mIsModify) {
            return;
        }

        // ywType
        View parent = mRoot.findViewById(R.id.ywtype);
        int size = mYewuDatas.size();
        PropertyTypeItem chooseType = null;
        for (int i = 0; i < size; i++) {
            if (((PropertyTypeItem) mYewuDatas.get(i)).type_id
                    .equals(mItem.choosetype)) {
                chooseType = ((PropertyTypeItem) mYewuDatas.get(i));
                break;
            }
        }

        if (chooseType != null) {
            initText(parent, R.id.select_type, chooseType.type_name);
            setTag(parent, R.id.select_type, chooseType);
        }

        // areaType
        parent = mRoot.findViewById(R.id.area);
        PropertyTypeItem chooseArea = null;
        size = mAreaDatas.size();
        for (int i = 0; i < size; i++) {
            if (((PropertyTypeItem) mAreaDatas.get(i)).type_id
                    .equals(mItem.areacode)) {
                chooseArea = ((PropertyTypeItem) mAreaDatas.get(i));
                break;
            }
        }
        if (chooseArea != null) {
            initText(parent, R.id.select_type, mItem.areaname);
            setTag(parent, R.id.select_type, chooseArea);
        }

        // houseType
        parent = mRoot.findViewById(R.id.housetype);
        PropertyTypeItem chooseHouse = null;
        size = mHuxingDatas.size();
        for (int i = 0; i < size; i++) {
            if (((PropertyTypeItem) mHuxingDatas.get(i)).type_id
                    .equals(mItem.housecode)) {
                chooseHouse = ((PropertyTypeItem) mHuxingDatas.get(i));
                break;
            }
        }
        if (chooseHouse != null) {
            initText(parent, R.id.select_type, mItem.housename);
            setTag(parent, R.id.select_type, chooseHouse);
        }

        initText(R.id.problem_content, mItem.housedesc);

        parent = mRoot.findViewById(R.id.weizhi);
        initText(parent, R.id.value, mItem.houseaddress);
        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.value, mItem.username);
        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.value, mItem.userphone);

        for (int i = 0; i < mItem.icobject.size(); i++) {
            addImageView(mItem.icobject.get(i));
        }
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
            gotoSubActivity(PropertyMineFangyuanActivity.class);
            break;
        case R.id.ywtype:
            clickSelectType();
            break;
        case R.id.area:
            clickSelectArea();
            break;
        case R.id.housetype:
            clickSelectHuxing();
            break;
        case R.id.publish:
            actionPublish();
            break;
        }
    }

    private void actionPublish() {
        View parent = findViewById(R.id.ywtype);
        Object ywlx = getTag(parent, R.id.select_type);
        if (!(ywlx instanceof PropertyTypeItem)) {
            showMessage("请选择业务类型");
            return;
        }
        parent = findViewById(R.id.area);
        Object area = getTag(parent, R.id.select_type);
        if (!(area instanceof PropertyTypeItem)) {
            showMessage("请选择面积");
            return;
        }
        parent = findViewById(R.id.housetype);
        Object huxing = getTag(parent, R.id.select_type);
        if (!(huxing instanceof PropertyTypeItem)) {
            showMessage("请选择户型");
            return;
        }
        String peitao = getTextContent(R.id.problem_content);
        if (TextUtils.isEmpty(peitao)) {
            showMessage("请填写配套描述");
            return;
        }

        parent = findViewById(R.id.weizhi);
        String weizhi = getTextContent(parent, R.id.value);
        if (TextUtils.isEmpty(weizhi)) {
            showMessage("请填写详细地址");
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

        PropertyFangwuzulinSubmitRequestInfo request = new PropertyFangwuzulinSubmitRequestInfo();
        if (mIsModify) {
            request.house.publishid = mItem.publishid;
            // modify info
            mItem.housedesc = peitao;
            mItem.username = lianxiren;
            mItem.userphone = dianhua;
            mItem.houseaddress = weizhi;
            mItem.icobject = files;
        }
        request.house.choosetype = ((PropertyTypeItem) ywlx).type_id;
        request.house.housecode = ((PropertyTypeItem) huxing).type_id;
        request.house.areacode = ((PropertyTypeItem) area).type_id;
        request.house.housedesc = peitao;
        request.house.username = lianxiren;
        request.house.userphone = dianhua;
        request.house.houseaddress = weizhi;
        request.house.icobject = files;

        ((PropertyFangwuzulinMessageProcessProxy) mProcess)
                .submitFangwuzulindan(this, request, this);
        showCustomProgrssDialog();
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        if ("4410".equals(info.iccode)) {
            processResult(info);
        }
        hideCustomProgressDialog();
    }

    private void processResult(PropertyItemInfo info) {
        if ("00".equals(info.answercode)) {
            resultSuccess();
        } else {
            showMessage("发布失败");
        }
    }

    private void resultSuccess() {
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

    protected void failedRequest() {
        hideCustomProgressDialog();
    }

    private void clickSelectType() {
        final ArrayList<PropertyItemInfo> datas = mYewuDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.ywtype);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
                setTag(parent, R.id.select_type, info);

                if (mIsModify) {
                    mItem.choosetype = ((PropertyTypeItem) info).type_id;
                }
            }
        });
    }

    private void clickSelectHuxing() {
        final ArrayList<PropertyItemInfo> datas = mHuxingDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.housetype);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
                setTag(parent, R.id.select_type, info);

                if (mIsModify) {
                    mItem.housecode = ((PropertyTypeItem) info).type_id;
                    mItem.housename = ((PropertyTypeItem) info).type_name;
                }
            }
        });
    }

    private void clickSelectArea() {
        final ArrayList<PropertyItemInfo> datas = mAreaDatas;

        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PropertyItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickSelectType: info: "
                        + ((PropertyTypeItem) info).type_name);
                View parent = mRoot.findViewById(R.id.area);
                initText(parent, R.id.select_type,
                        ((PropertyTypeItem) info).type_name);
                setTag(parent, R.id.select_type, info);

                if (mIsModify) {
                    mItem.areacode = ((PropertyTypeItem) info).type_id;
                    mItem.areaname = ((PropertyTypeItem) info).type_name;
                }
            }
        });
    }
}
