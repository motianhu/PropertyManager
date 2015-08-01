package com.smona.app.propertymanager.common;

import java.util.ArrayList;

import com.smona.app.propertymanager.data.model.PropertyItemInfo;

public abstract class PropertyFilterTypeActivity extends PropertyFetchListActivity {

    protected ArrayList<PropertyItemInfo> mAllDatas = new ArrayList<PropertyItemInfo>();
    protected ArrayList<PropertyItemInfo> mShowDatas = new ArrayList<PropertyItemInfo>();
    
}
