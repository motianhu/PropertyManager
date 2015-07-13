package com.smona.app.propertymanager.notify;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;

public class PropertyWuyetongzhiDetailActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_wuyetongzhi_detail);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.property_home_wuyetongzhi);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        TextView time = (TextView) mRoot.findViewById(R.id.notify_time);
        time.setText("2015-06-27");
        TextView title = (TextView) mRoot.findViewById(R.id.notify_title);
        title.setText("Hellow world");
        TextView content = (TextView) mRoot.findViewById(R.id.notify_content);
        content.setText("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
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
