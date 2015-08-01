package com.smona.app.propertymanager.zulin;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.reflect.TypeToken;
import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinContentItem;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinTypeItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;
import com.smona.app.propertymanager.util.JsonUtils;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinMessageProcessProxy;
import com.smona.app.propertymanager.zulin.process.PropertyFangwuzulinSubmitRequestInfo;

public class PropertyPublishFangYuanActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyPublishFangYuanActivity";

    private PropertyFangwuzulinContentItem mItem;

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
                .getParcelableExtra("iteminfo");
        if (mItem != null) {
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
        if (mItem != null) {
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

        initView(R.id.start_camera);

        initView(R.id.publish);
    }

    @Override
    public void refreshUI() {
        if (mItem == null) {
            return;
        }

        View parent = mRoot.findViewById(R.id.ywtype);
        int size = mYewuDatas.size();
        String chooseName = "";
        for (int i = 0; i < size; i++) {
            if (((PropertyTypeItem) mYewuDatas.get(i)).type_id.equals(mItem.choosetype)) {
                chooseName = ((PropertyTypeItem) mYewuDatas.get(i)).type_name;
                break;
            }
        }
        initText(parent, R.id.select_type, chooseName);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.select_type, mItem.housearea);
        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.select_type, mItem.housetype);

        initText(R.id.problem_content, mItem.housedesc);

        parent = mRoot.findViewById(R.id.weizhi);
        initText(parent, R.id.value, mItem.houseaddress);
        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.value, mItem.username);
        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.value, mItem.userphone);
    }

    @Override
    protected void clickView(View v) {
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

        PropertyFangwuzulinSubmitRequestInfo request = new PropertyFangwuzulinSubmitRequestInfo();
        request.house.choosetype = ((PropertyTypeItem) ywlx).type_id;
        request.house.housecode = ((PropertyTypeItem) huxing).type_id;
        request.house.areacode = ((PropertyTypeItem) area).type_id;
        request.house.housedesc = peitao;
        request.house.username = lianxiren;
        request.house.userphone = dianhua;
        request.house.houseaddress = weizhi;

        ((PropertyFangwuzulinMessageProcessProxy) mProcess)
                .submitFangwuzulindan(this, request, this);
        showCustomProgrssDialog();
    }

    protected void saveData(String content) {
        Type type = new TypeToken<PropertyItemInfo>() {
        }.getType();
        PropertyItemInfo info = JsonUtils.parseJson(content, type);
        if ("4410".equals(info.iccode) && "00".equals(info.answercode)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showMessage("发布成功");
                }
            });
        }
        hideCustomProgressDialog();
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
            }
        });
    }
}
