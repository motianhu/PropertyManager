package com.smona.app.propertymanager.zulin;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;

public class PropertyMineFangyuanActivity extends BaseActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_fangwuzulin_mine);
        initViews();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.property_fangwuzulin_mine);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.ywtype);
        initText(parent, R.id.select_type,
                R.string.property_fangwuzulin_xuanzeyewuleixing);

        parent = mRoot.findViewById(R.id.area);
        initText(parent, R.id.select_type,
                R.string.property_fangwuzulin_arealeixing);

        parent = mRoot.findViewById(R.id.housetype);
        initText(parent, R.id.select_type,
                R.string.property_fangwuzulin_xuanzehuxing);

        ListView list = (ListView) mRoot.findViewById(R.id.list_content);
        ArrayList<ItemInfo> data = new ArrayList<ItemInfo>();
        for (int i = 0; i < 10; i++) {
            ItemInfo info = new ItemInfo();
            data.add(info);
        }
        PropertyZulinDetailAdapter adapter = new PropertyZulinDetailAdapter(
                this, data);
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
