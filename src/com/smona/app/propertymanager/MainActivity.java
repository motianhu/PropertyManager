package com.smona.app.propertymanager;

import com.smona.app.propertymanager.baoxiu.WuyebaoxiuActivity;
import com.smona.app.propertymanager.notify.WuyetongzhiActivity;
import com.smona.app.propertymanager.tousu.TousujianyiActivity;
import com.smona.app.propertymanager.wupin.ErshouwupinActivity;
import com.smona.app.propertymanager.zulin.FangwuzulinActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initViews();
    }

    protected void initHeader() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(R.string.wuyefuwu);
        initView(R.id.back);
    }

    protected void initBody() {
        initView(R.id.wuyebaoxiu);
        initView(R.id.tousujianyi);
        initView(R.id.wuyetongzhi);
        initView(R.id.fangwuzulin);
        initView(R.id.ershouwupin);
    }

    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.wuyebaoxiu:
            gotoSubActivity(WuyebaoxiuActivity.class);
            break;
        case R.id.tousujianyi:
            gotoSubActivity(TousujianyiActivity.class);
            break;
        case R.id.wuyetongzhi:
            gotoSubActivity(WuyetongzhiActivity.class);
            break;
        case R.id.fangwuzulin:
            gotoSubActivity(FangwuzulinActivity.class);
            break;
        case R.id.ershouwupin:
            gotoSubActivity(ErshouwupinActivity.class);
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
