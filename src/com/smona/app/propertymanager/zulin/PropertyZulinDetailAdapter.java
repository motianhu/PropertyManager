package com.smona.app.propertymanager.zulin;

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

    public void initConvertView(View convertView, final PropertyItemInfo info) {
        View parent = convertView.findViewById(R.id.pulish_time);
        initText(parent, R.id.name,
                R.string.property_fangwuzulin_item_pulish_time);

        parent = convertView.findViewById(R.id.position);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_position);

        parent = convertView.findViewById(R.id.tel);
        initText(parent, R.id.name, R.string.property_fangwuzulin_item_tel);

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });
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
}
