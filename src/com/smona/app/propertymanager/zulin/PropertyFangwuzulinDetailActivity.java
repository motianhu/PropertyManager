package com.smona.app.propertymanager.zulin;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinContentItem;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.LogUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PropertyFangwuzulinDetailActivity extends PropertyBaseActivity {
    private static final String TAG = "PropertyFangwuzulinDetailActivity";

    private PropertyFangwuzulinContentItem mItem;
    private boolean mIsMySelf = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_fangwuzulin_detail);
        acquireData();
        initViews();
        requestRefreshUI();
    }

    private void acquireData() {
        mItem = (PropertyFangwuzulinContentItem) getIntent()
                .getParcelableExtra("iteminfo");
        mIsMySelf = true;
        LogUtil.d(TAG, "acquireData mItem: " + mItem);
    }

    protected void refreshUI() {
        View parent = mRoot.findViewById(R.id.ywtype);
        initText(parent, R.id.value, mItem.choosetype);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.value, mItem.housearea);

        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.value, mItem.housetype);

        parent = mRoot.findViewById(R.id.peitaomiaoshu);
        initText(parent, R.id.value, mItem.housedesc);

        parent = mRoot.findViewById(R.id.position);
        initText(parent, R.id.value, mItem.houseaddress);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.value, mItem.username);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.value, mItem.userphone);

        parent = mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.value, mItem.publishtime);

        parent = mRoot.findViewById(R.id.fangyuan_picture);
        ImageView image = (ImageView) parent.findViewById(R.id.image);
        ImageLoaderManager.getInstance().loadImage(mItem.picurl.get(0), image);

        TextView text = (TextView) mRoot.findViewById(R.id.call_phone);
        text.setText(getResources().getString(
                R.string.property_common_call_contact_phone)
                + "(" + mItem.userphone + ")");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_fangwuzulin_fangyuanxinxi);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.ywtype);
        initText(parent, R.id.name,
                R.string.property_fangwuzulin_item_yewuleixing);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_area);

        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_huxing);

        parent = mRoot.findViewById(R.id.peitaomiaoshu);
        initText(parent, R.id.name,
                R.string.property_fangwuzulin_fangyuanfabu_peitaomiaoshu);

        parent = mRoot.findViewById(R.id.position);
        initText(parent, R.id.name, R.string.property_fangwuzulin_position);

        parent = mRoot.findViewById(R.id.lianxiren);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_lianxiren);

        parent = mRoot.findViewById(R.id.dianhua);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_wupinfabu_dianhu);

        parent = mRoot.findViewById(R.id.fabushijian);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_pulish_time);

        if (mIsMySelf) {
            findViewById(R.id.other_operate).setVisibility(View.GONE);
            initView(R.id.modify_info);
            initView(R.id.cancel_publish);
        } else {
            findViewById(R.id.my_operate).setVisibility(View.GONE);
            initView(R.id.call_phone);
        }

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
