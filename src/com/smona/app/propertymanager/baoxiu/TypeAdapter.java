package com.smona.app.propertymanager.baoxiu;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyTypeItem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class TypeAdapter extends PropertyBaseDataAdapter {

    public TypeAdapter(Context context, ArrayList<PropertyItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                android.R.layout.simple_list_item_single_choice, null);
    }

    public void initConvertView(View convertView, final PropertyItemInfo info) {
        TextView text = (TextView) convertView;
        text.setText(((PropertyTypeItem) info).type_name);
        text.setTextColor(Color.BLACK);
    }
}
