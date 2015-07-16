package com.smona.app.propertymanager.wupin;

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

public class PropertyWupinDetailAdapter extends PropertyBaseDataAdapter {

    public PropertyWupinDetailAdapter(Context context,
            ArrayList<PropertyItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.property_ershouwupin_item, null);
    }

    public void initConvertView(View convertView, final PropertyItemInfo info) {
        View parent = convertView.findViewById(R.id.pulish_time);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_pulish_time);

        parent = convertView.findViewById(R.id.position);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_wupmingcheng);

        parent = convertView.findViewById(R.id.tel);
        initText(parent, R.id.name, R.string.property_ershouwupin_item_xinjiu);

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });
    }

    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setClass(mContext, PropertyWupinDetailActivity.class);
        return intent;
    }

    private void initText(View parent, int resId, int text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
    }

}
