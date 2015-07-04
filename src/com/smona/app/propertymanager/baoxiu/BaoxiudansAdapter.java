package com.smona.app.propertymanager.baoxiu;

import java.util.ArrayList;
import com.smona.app.propertymanager.BaseDataAdapter;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class BaoxiudansAdapter extends BaseDataAdapter {

    public BaoxiudansAdapter(Context context, ArrayList<ItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.baoxiudan_item,
                null);
    }

    public void initConvertView(View convertView, final ItemInfo info) {
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });
    }

    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setClass(mContext, BaoxiudanDetailActivity.class);
        return intent;
    }

}
