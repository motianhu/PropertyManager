package com.smona.app.propertymanager.data.bean;

import android.content.ContentValues;

import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.ICreateDBValues;
import com.smona.app.propertymanager.data.table.PropertyErshouwupinwupinTypeTable;

public class PropertyBeanErshouwupinwupinType implements ICreateDBValues {
    public String areacode;
    public String areaname;

    @Override
    public ContentValues onCreateDBValues() {
        ContentValues values = new ContentValues();
        values.put(PropertyErshouwupinwupinTypeTable.TYPE_ID, areacode);
        values.put(PropertyErshouwupinwupinTypeTable.TYPE_NAME, areaname);
        return values;
    }
}
