package com.smona.app.propertymanager.zulin;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;

public class MineFangyuanActivity extends BaseActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fangwuzulin_mine);
        initViews();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.mine_fangyuan);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.ywtype);
        initText(parent, R.id.select_type, R.string.yewuleixing);
        initText(parent, R.id.select_type_value, R.string.yewuleixing);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.select_type, R.string.area_xuanze);
        initText(parent, R.id.select_type_value, R.string.area_leixing);

        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.select_type, R.string.house_xuanze);
        initText(parent, R.id.select_type_value, R.string.house_leixing);

        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
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
        }
    }

}
