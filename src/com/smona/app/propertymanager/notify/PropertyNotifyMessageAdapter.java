package com.smona.app.propertymanager.notify;

import java.util.ArrayList;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.model.PropertyWuyetongzhiContentItem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class PropertyNotifyMessageAdapter extends PropertyBaseDataAdapter {

    public PropertyNotifyMessageAdapter(Context context,
            ArrayList<PropertyItemInfo> content) {
        super(context, content);
    }

    @Override
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.property_wuyetongzhi_item, null);
    }

    public void initConvertView(View convertView, final PropertyItemInfo info) {
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });

        PropertyWuyetongzhiContentItem item;
        if (info instanceof PropertyWuyetongzhiContentItem) {
            item = (PropertyWuyetongzhiContentItem) info;
        } else {
            return;
        }

        TextView title = (TextView) convertView.findViewById(R.id.notify_title);
        title.setText(item.title);
        TextView time = (TextView) convertView.findViewById(R.id.notify_time);
        time.setText(item.publishtime);

        ImageView image = (ImageView) convertView.findViewById(R.id.image_read);
        int resId = "1".equals(item.noticestatus) ? R.drawable.property_wuyetongzhi_message_read
                : R.drawable.property_wuyetongzhi_message_unread;
        image.setImageResource(resId);
    }

    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setClass(mContext, PropertyWuyetongzhiDetailActivity.class);
        return intent;
    }
}
