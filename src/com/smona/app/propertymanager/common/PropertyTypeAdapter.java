package com.smona.app.propertymanager.common;

import java.util.ArrayList;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class PropertyTypeAdapter extends PropertyBaseDataAdapter {

    public PropertyTypeAdapter(Context context,
            ArrayList<PropertyItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.property_common_tankuang, null);
    }

    public void initConvertView(View convertView, final PropertyItemInfo info) {
        TextView text = (TextView) convertView;
        text.setText(((PropertyTypeItem) info).type_name);
        text.setTextColor(Color.BLACK);
    }
}
