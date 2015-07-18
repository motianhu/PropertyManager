package com.smona.app.propertymanager.data.bean;

import android.content.ContentValues;

import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.ICreateDBValues;
import com.smona.app.propertymanager.data.table.PropertyErshouwupinpinpaiTypeTable;

public class PropertyBeanErshouwupinpinpaiType implements ICreateDBValues {
    public String brandcode;
    public String brandname;

    @Override
    public ContentValues onCreateDBValues() {
        ContentValues values = new ContentValues();
        values.put(PropertyErshouwupinpinpaiTypeTable.TYPE_ID, brandcode);
        values.put(PropertyErshouwupinpinpaiTypeTable.TYPE_NAME, brandname);
        return values;
    }
}
