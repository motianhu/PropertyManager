package com.smona.app.propertymanager.zulin;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PropertyZulinDetailAdapter extends PropertyBaseDataAdapter {

    public PropertyZulinDetailAdapter(Context context,
            ArrayList<PropertyItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.property_fangwuzulin_item, null);
    }

    @SuppressLint("ResourceAsColor")
    public void initConvertView(View convertView, final PropertyItemInfo info) {
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });

        // static args
        View parent = convertView.findViewById(R.id.pulish_time);
        initText(parent, R.id.name,
                R.string.property_fangwuzulin_item_pulish_time);
        parent = convertView.findViewById(R.id.position);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_position);
        parent = convertView.findViewById(R.id.tel);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_tel);

        PropertyFangwuzulinContentItem item;
        if (info instanceof PropertyFangwuzulinContentItem) {
            item = (PropertyFangwuzulinContentItem) info;
        } else {
            return;
        }

        // dynamic args
        parent = convertView.findViewById(R.id.pulish_time);
        initText(parent, R.id.value, item.publishtime);
        parent = convertView.findViewById(R.id.position);
        initText(parent, R.id.value, item.houseaddress);
        parent = convertView.findViewById(R.id.tel);
        initText(parent, R.id.value, item.userphone);
        TextView text = (TextView) parent.findViewById(R.id.value);
        text.setTextColor(Color.rgb(0x37, 0x86, 0xbe));

    }

    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setClass(mContext, PropertyFangwuzulinDetailActivity.class);
        return intent;
    }

    private void initText(View parent, int resId, int text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
    }

    private void initText(View parent, int resId, String text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
    }
}
