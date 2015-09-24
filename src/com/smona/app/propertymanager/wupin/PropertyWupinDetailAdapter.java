package com.smona.app.propertymanager.wupin;

import java.util.ArrayList;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.data.model.PropertyErshouwupinContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.PropertyConstants;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
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
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });

        View parent = convertView.findViewById(R.id.pulish_time);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_pulish_time);

        parent = convertView.findViewById(R.id.wupin_name);
        initText(parent, R.id.name,
                R.string.property_ershouwupin_item_wupmingcheng);

        parent = convertView.findViewById(R.id.goodstatus);
        initText(parent, R.id.name, R.string.property_ershouwupin_item_xinjiu);

        PropertyErshouwupinContentItem item;
        if (info instanceof PropertyErshouwupinContentItem) {
            item = (PropertyErshouwupinContentItem) info;
        } else {
            return;
        }

        parent = convertView.findViewById(R.id.pulish_time);
        initText(parent, R.id.value, item.publishtime);

        parent = convertView.findViewById(R.id.wupin_name);
        initText(parent, R.id.value, item.goodsname);

        parent = convertView.findViewById(R.id.goodstatus);
        initText(parent, R.id.value, item.goosstatus);

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        if (item.picurl != null && item.picurl.size() > 0) {
            ImageLoaderManager.getInstance().loadFangwuzulin(
                    item.picurl.get(0), image);
        }
    }

    protected void gotoDetail(PropertyItemInfo info) {
        Intent intent = createIntent();
        intent.putExtra(PropertyConstants.DATA_ITEM_INFO, info);
        ((Activity) mContext).startActivityForResult(intent,
                PropertyConstants.ACTION_MODIFY_LIST);
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

    private void initText(View parent, int resId, String text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
    }
}
