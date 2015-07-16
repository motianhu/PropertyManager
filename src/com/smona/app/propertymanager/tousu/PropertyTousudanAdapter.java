package com.smona.app.propertymanager.tousu;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PropertyTousudanAdapter extends PropertyBaseDataAdapter {

    public PropertyTousudanAdapter(Context context,
            ArrayList<PropertyItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.property_tousujianyi_tousudan_item, null);
    }

    public void initConvertView(View convertView, final PropertyItemInfo info) {
        // View parent = convertView.findViewById(R.id.pulish_time);
        // initText(parent, R.id.name, R.string.pulish_time);
        // initText(parent, R.id.value, R.string.pulish_time);
        //
        // parent = convertView.findViewById(R.id.position);
        // initText(parent, R.id.name, R.string.position);
        // initText(parent, R.id.value, R.string.position);
        //
        // parent = convertView.findViewById(R.id.tel);
        // initText(parent, R.id.name, R.string.lianxidianhua);
        // initText(parent, R.id.value, R.string.lianxidianhua);

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });
    }

    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setClass(mContext, PropertyTousudanDetailActivity.class);
        return intent;
    }
}
