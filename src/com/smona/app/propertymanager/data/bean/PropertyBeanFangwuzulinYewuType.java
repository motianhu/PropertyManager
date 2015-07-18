package com.smona.app.propertymanager.data.bean;

import android.content.ContentValues;

import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.ICreateDBValues;
import com.smona.app.propertymanager.data.table.PropertyFangwuzulinyewuTypeTable;

public class PropertyBeanFangwuzulinYewuType implements ICreateDBValues {
    public String typecode;
    public String typename;

    @Override
    public ContentValues onCreateDBValues() {
        ContentValues values = new ContentValues();
        values.put(PropertyFangwuzulinyewuTypeTable.TYPE_ID, typecode);
        values.put(PropertyFangwuzulinyewuTypeTable.TYPE_NAME, typename);
        return values;
    }
}
