package com.smona.app.propertymanager.notify;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseDataAdapter;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class NotifyMessageAdapter extends PropertyBaseDataAdapter {

    public NotifyMessageAdapter(Context context, ArrayList<ItemInfo> content) {
        super(context, content);
    }

    @Override
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(
                R.layout.wuyetongzhi_detail_item, null);
    }

    public void initConvertView(View convertView, final ItemInfo info) {
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });
        TextView time = (TextView) convertView.findViewById(R.id.notify_time);
        time.setText("2015-06-27");
        TextView title = (TextView) convertView.findViewById(R.id.notify_title);
        title.setText("Hellow world");
    }

    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setClass(mContext, WuyetongzhiDetailActivity.class);
        return intent;
    }
}
