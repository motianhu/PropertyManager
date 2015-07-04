package com.smona.app.propertymanager.zulin;

import java.util.ArrayList;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class FangwuzulinActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fangwuzulin);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.chuzu);
        initText(R.id.detail, R.string.publish_fangyuan);
        initView(R.id.detail);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = findViewById(R.id.ywtype);
        initText(parent, R.id.select_type_value, R.string.xuanzeyewuleixing);
        initText(parent, R.id.select_type, R.string.yewuleixing);
        initView(R.id.ywtype);

        parent = findViewById(R.id.area);
        initText(parent, R.id.select_type_value, R.string.area_leixing);
        initText(parent, R.id.select_type, R.string.area_xuanze);
        initView(R.id.area);

        parent = findViewById(R.id.housetype);
        initText(parent, R.id.select_type_value, R.string.house_leixing);
        initText(parent, R.id.select_type, R.string.house_xuanze);
        initView(R.id.housetype);

        ListView list = (ListView) findViewById(R.id.list_content);
        ArrayList<ItemInfo> data = new ArrayList<ItemInfo>();
        for (int i = 0; i < 10; i++) {
            ItemInfo info = new ItemInfo();
            data.add(info);
        }
        ZulinDetailAdapter adapter = new ZulinDetailAdapter(this, data);
        list.setAdapter(adapter);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.detail:
            gotoSubActivity(PublishFangYuanActivity.class);
            break;
        case R.id.ywtype:
            break;
        case R.id.area:
            break;
        case R.id.housetype:
            break;
        }
    }
}
