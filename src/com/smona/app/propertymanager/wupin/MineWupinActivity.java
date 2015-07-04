package com.smona.app.propertymanager.wupin;

import java.util.ArrayList;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MineWupinActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ershouwupin_mine);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.ershouwupin_mine);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.wupintype);
        initText(parent, R.id.select_type, R.string.yewuleixing);
        initText(parent, R.id.select_type_value, R.string.yewuleixing);

        parent = mRoot.findViewById(R.id.pinpai);
        initText(parent, R.id.select_type, R.string.area_leixing);
        initText(parent, R.id.select_type_value, R.string.area_xuanze);

        parent = mRoot.findViewById(R.id.xinjiu);
        initText(parent, R.id.select_type, R.string.house_leixing);
        initText(parent, R.id.select_type_value, R.string.house_xuanze);
        
        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        ArrayList<ItemInfo> data = new ArrayList<ItemInfo>();
        for (int i = 0; i < 10; i++) {
            ItemInfo info = new ItemInfo();
            data.add(info);
        }
        WupinDetailAdapter adapter = new WupinDetailAdapter(this, data);
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
