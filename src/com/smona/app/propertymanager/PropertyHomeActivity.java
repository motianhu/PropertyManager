package com.smona.app.propertymanager;

import com.smona.app.propertymanager.baoxiu.PropertyWuyebaoxiuActivity;
import com.smona.app.propertymanager.data.table.PropertyWuyebaoxiudanTable;
import com.smona.app.propertymanager.notify.PropertyWuyetongzhiActivity;
import com.smona.app.propertymanager.tousu.PropertyTousujianyiActivity;
import com.smona.app.propertymanager.wupin.PropertyErshouwupinActivity;
import com.smona.app.propertymanager.zulin.PropertyFangwuzulinActivity;

import android.content.ContentResolver;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PropertyHomeActivity extends PropertyBaseActivity {

    private static final  boolean DEBUG = true;
    private boolean mIsLogin = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_home);
        initViews();
        testProvider();
    }

    private void testProvider() {
        ContentResolver cr = this.getContentResolver();
        cr.query(PropertyWuyebaoxiudanTable.getInstance().mContentUri_NoNotify,
                null, null, null, null);

        // login
        mProcess = new PropertyNetLoginMessageProcess();
        ((PropertyNetLoginMessageProcess) mProcess).login(this);
        showDialog(0);
    }

    protected void saveData(String content) {
        mIsLogin = true;
        hideCustomProgressDialog();
    }

    protected void failedRequest() {
        mIsLogin = false;
        hideCustomProgressDialog();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_wuyefuwu);
        initView(R.id.back);
    }

    protected void initBody() {
        initView(R.id.wuyebaoxiu);
        initImageAndText(R.id.wuyebaoxiu, R.drawable.property_home_wuyebaoxiu,
                R.string.property_home_wuyebaoxiu);

        initView(R.id.tousujianyi);
        initImageAndText(R.id.tousujianyi,
                R.drawable.property_home_tousujianyi,
                R.string.property_home_tousujianyi);

        initView(R.id.wuyetongzhi);
        initImageAndText(R.id.wuyetongzhi,
                R.drawable.property_home_wuyetongzhi,
                R.string.property_home_wuyetongzhi);

        initView(R.id.fangwuzulin);
        initImageAndText(R.id.fangwuzulin,
                R.drawable.property_home_fangwuzulin,
                R.string.property_home_fangwuzulin);

        initView(R.id.ershouwupin);
        initImageAndText(R.id.ershouwupin,
                R.drawable.property_home_ershouwupin,
                R.string.property_home_ershouwupin);

    }

    private void initImageAndText(int parentResId, int imageResId, int textResId) {
        View parent = mRoot.findViewById(parentResId);
        ImageView image = (ImageView) parent.findViewById(R.id.home_item_image);
        image.setImageResource(imageResId);
        TextView text = (TextView) parent.findViewById(R.id.home_item_text);
        text.setText(textResId);
    }

    protected void clickView(View v) {
        int id = v.getId();
        if (R.id.back == id) {
            finish();
            return;
        }
        
        if (!mIsLogin && !DEBUG) {
            Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
            return;
        }
        
        switch (id) {
        case R.id.wuyebaoxiu:
            gotoSubActivity(PropertyWuyebaoxiuActivity.class);
            break;
        case R.id.tousujianyi:
            gotoSubActivity(PropertyTousujianyiActivity.class);
            break;
        case R.id.wuyetongzhi:
            gotoSubActivity(PropertyWuyetongzhiActivity.class);
            break;
        case R.id.fangwuzulin:
            gotoSubActivity(PropertyFangwuzulinActivity.class);
            break;
        case R.id.ershouwupin:
            gotoSubActivity(PropertyErshouwupinActivity.class);
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
