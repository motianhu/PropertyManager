package com.smona.app.propertymanager.baoxiu;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.MessageProcess;
import com.smona.app.propertymanager.R;

import android.os.Bundle;
import android.view.View;

public class WuyebaoxiuActivity extends BaseActivity {
    private MessageProcess mProcess;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wuyebaoxiu);
        initViews();
        testData();
    }
    
    private void testData() {
        mProcess = new MessageProcess();
        mProcess.requestWuyebaoxiu();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.baoxiu);
        initText(R.id.detail, R.string.baoxiudandetail);
        initText(R.id.select_type, R.string.xuanzebaoxiuleixing);
        initText(R.id.problem_desc, R.string.baoxiuwentimiaoshu);
        initText(R.id.like_action, R.string.likebaoxiu);
        initView(R.id.back);
        initView(R.id.detail);
    }

    protected void initBody() {

    }

    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity(BaoxiudanActivity.class);
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
