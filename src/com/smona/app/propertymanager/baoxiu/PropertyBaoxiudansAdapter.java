package com.smona.app.propertymanager.baoxiu;

import java.util.ArrayList;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyWuyebaoxiudanContentItem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

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

        PropertyWuyebaoxiudanContentItem item;
        if (info instanceof PropertyWuyebaoxiudanContentItem) {
            item = (PropertyWuyebaoxiudanContentItem) info;
        } else {
            return;
        }

        TextView text = (TextView) convertView
                .findViewById(R.id.baoxiudan_time);
        text.setText(item.requesttime);

        text = (TextView) convertView.findViewById(R.id.baoxiudan_status);
        text.setText(item.repairstatus);

        text = (TextView) convertView.findViewById(R.id.baoxiudan_title);
        text.setText(item.repairname);
    }

    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setClass(mContext, PropertyBaoxiudanDetailActivity.class);
        return intent;
    }

}
