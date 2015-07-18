package com.smona.app.propertymanager.data.bean;

import android.content.ContentValues;

import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.ICreateDBValues;
import com.smona.app.propertymanager.data.table.PropertyFangwuzulinhuxingTypeTable;

public class PropertyBeanFangwuzulinHuxingType implements ICreateDBValues {
    public String housecode;
    public String housename;

    @Override
    public ContentValues onCreateDBValues() {
        ContentValues values = new ContentValues();
        values.put(PropertyFangwuzulinhuxingTypeTable.TYPE_ID, housecode);
        values.put(PropertyFangwuzulinhuxingTypeTable.TYPE_NAME, housename);
        return values;
    }
}
