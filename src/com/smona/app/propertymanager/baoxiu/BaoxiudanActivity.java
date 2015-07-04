package com.smona.app.propertymanager.baoxiu;

import java.util.ArrayList;

import com.smona.app.propertymanager.BaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class BaoxiudanActivity extends BaseActivity {

    private ListView mBaoxiudans;
    private BaoxiudansAdapter mBaoxiudansAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baoxiudan);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.baoxiudan);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        mBaoxiudans = (ListView) mRoot.findViewById(R.id.baoxiudans);
        ArrayList<ItemInfo> datas = new ArrayList<ItemInfo>();
        for (int i = 0; i < 10; i++) {
            ItemInfo item = new ItemInfo();
            datas.add(item);
        }
        mBaoxiudansAdapter = new BaoxiudansAdapter(this, datas);
        mBaoxiudans.setAdapter(mBaoxiudansAdapter);
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
