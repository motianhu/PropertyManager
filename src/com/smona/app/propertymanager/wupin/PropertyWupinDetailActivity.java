package com.smona.app.propertymanager.wupin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.LogUtil;

public class PropertyWupinDetailActivity extends PropertyBaseActivity {

    private static final String TAG = "PropertyWupinDetailActivity";

    private PropertyErshouwupinContentItem mItem;
    private boolean mIsMySelf = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_ershouwupin_detail);
        acquireData();
        initViews();
        requestRefreshUI();
    }

    private void acquireData() {
        mItem = (PropertyErshouwupinContentItem) getIntent()
                .getParcelableExtra("iteminfo");
        mIsMySelf = mItem.customerid == mItem.loginname;
        LogUtil.d(TAG, "acquireData mItem: " + mItem);
    }

    protected void refreshUI() {
        View parent = mRoot.findViewById(R.id.wupinfenlei);
        initText(parent, R.id.value, mItem.classname);

        parent = mRoot.findViewById(R.id.pinpai);
        initText(parent, R.id.value, mItem.brand);

        parent = mRoot.findViewById(R.id.wupinmingchen);
        initText(parent, R.id.value, mItem.goodsname);

        parent = mRoot.findViewById(R.id.wupinmiaoshu);
        initText(parent, R.id.value, mItem.goodsdesc);

        parent = mRoot.findViewById(R.id.xinjiu);
        initText(parent, R.id.value, mItem.goosstatus);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.value, mItem.username);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.value, mItem.userphone);

        parent = mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.value, mItem.publishtime);

        ViewGroup list = (ViewGroup) mRoot.findViewById(R.id.list_hor_image);
        for (int i = 0; i < mItem.picurl.size(); i++) {
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
            ImageLoaderManager.getInstance().loadImage(mItem.picurl.get(i),
                    image);
        }

        if (mIsMySelf) {
            findViewById(R.id.other_operate).setVisibility(View.GONE);
            initView(R.id.modify_info);
            initView(R.id.cancel_publish);
        } else {
            findViewById(R.id.my_operate).setVisibility(View.GONE);
            initView(R.id.call_phone);
            TextView text = (TextView) mRoot.findViewById(R.id.call_phone);
            text.setText(getResources().getString(
                    R.string.property_common_call_contact_phone)
                    + "(" + mItem.userphone + ")");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_ershouwupin_wupin_detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.wupinfenlei);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_detail_wupinfenlei);

        parent = mRoot.findViewById(R.id.pinpai);
        initText(parent, R.id.name, R.string.property_ershouwupin_detail_pinpai);

        parent = mRoot.findViewById(R.id.wupinmingchen);
        initText(parent, R.id.name, R.string.property_ershouwupin_wupinmingchen);

        parent = mRoot.findViewById(R.id.wupinmiaoshu);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_wupinmiaoshu);

        parent = mRoot.findViewById(R.id.xinjiu);
        initText(parent, R.id.name, R.string.property_ershouwupin_xuanzexinjiu);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_lianxiren);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_dianhu);

        parent = mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_pulish_time);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.call_phone:
            clickCall();
            break;
        }
    }

    private void clickCall() {
        String phone = "tel:" + mItem.userphone;
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
