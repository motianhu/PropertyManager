package com.smona.app.propertymanager.tousu;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTousujianyidanContentItem;

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
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });

        PropertyTousujianyidanContentItem item;
        if (info instanceof PropertyTousujianyidanContentItem) {
            item = (PropertyTousujianyidanContentItem) info;
        } else {
            return;
        }

        TextView text = (TextView) convertView.findViewById(R.id.tousudan_time);
        text.setText(item.requesttime);

        text = (TextView) convertView.findViewById(R.id.tousudan_mingcheng);
        text.setText(item.complaintname);

        text = (TextView) convertView.findViewById(R.id.tousudan_status);
        text.setText(item.complaint);
    }

    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setClass(mContext, PropertyTousudanDetailActivity.class);
        return intent;
    }
}
