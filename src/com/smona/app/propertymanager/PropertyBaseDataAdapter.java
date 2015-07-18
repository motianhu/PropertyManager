package com.smona.app.propertymanager;

import java.util.ArrayList;

import com.smona.app.propertymanager.data.model.PropertyItemInfo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class PropertyBaseDataAdapter extends BaseAdapter {
    protected Context mContext;
    protected ArrayList<PropertyItemInfo> mContent;

    public PropertyBaseDataAdapter(Context context,
            ArrayList<PropertyItemInfo> content) {
        mContext = context;
        mContent = content;
    }

    @Override
    public int getCount() {
        return mContent.size();
    }

    @Override
    public Object getItem(int position) {
        return mContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PropertyItemInfo info = mContent.get(position);
        if (convertView == null) {
            convertView = createContentView(mContext);
            convertView.setTag(info);
        }
        initConvertView(convertView, info);
        return convertView;
    }

    public abstract View createContentView(Context context);

    public abstract void initConvertView(View view, PropertyItemInfo info);

    protected void gotoDetail(PropertyItemInfo info) {
        Intent intent = createIntent();
        intent.putExtra("iteminfo", info);
        mContext.startActivity(intent);
    }

    protected Intent createIntent() {
        return null;
    }
}
