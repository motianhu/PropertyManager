package com.smona.app.propertymanager.baoxiu;

import java.util.ArrayList;
import com.smona.app.propertymanager.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.PropertyItemInfo;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class PropertyBaoxiudansAdapter extends PropertyBaseDataAdapter {

    public PropertyBaoxiudansAdapter(Context context,
            ArrayList<PropertyItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.property_wuyebaoxiu_baoxiudan_item, null);
    }

    public void initConvertView(View convertView, final PropertyItemInfo info) {
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });
    }

    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setClass(mContext, PropertyBaoxiudanDetailActivity.class);
        return intent;
    }

}
