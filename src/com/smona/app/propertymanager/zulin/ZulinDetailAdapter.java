package com.smona.app.propertymanager.zulin;

import java.util.ArrayList;

import com.smona.app.propertymanager.BaseDataAdapter;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.ItemInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ZulinDetailAdapter extends BaseDataAdapter {

    public ZulinDetailAdapter(Context context, ArrayList<ItemInfo> content) {
        super(context, content);
    }

    @SuppressLint("InflateParams")
    public View createContentView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.fangwuzulin_item,
                null);
    }

    public void initConvertView(View convertView, final ItemInfo info) {
        View parent = convertView.findViewById(R.id.pulish_time);
        initText(parent, R.id.name, R.string.pulish_time);
        initText(parent, R.id.value, R.string.pulish_time);

        parent = convertView.findViewById(R.id.position);
        initText(parent, R.id.name, R.string.position);
        initText(parent, R.id.value, R.string.position);

        parent = convertView.findViewById(R.id.tel);
        initText(parent, R.id.name, R.string.lianxidianhua);
        initText(parent, R.id.value, R.string.lianxidianhua);

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail(info);
            }
        });
    }

    public Intent createIntent() {
        Intent intent = new Intent();
        intent.setClass(mContext, FangwuzulinDetailActivity.class);
        return intent;
    }

    private void initText(View parent, int resId, int text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
    }
}
