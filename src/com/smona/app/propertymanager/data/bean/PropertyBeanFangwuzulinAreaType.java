package com.smona.app.propertymanager.data.bean;

import android.content.ContentValues;

import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.ICreateDBValues;
import com.smona.app.propertymanager.data.table.PropertyFangwuzulinareaTypeTable;

public class PropertyBeanFangwuzulinAreaType implements ICreateDBValues {
    public String areacode;
    public String areaname;

    @Override
    public ContentValues onCreateDBValues() {
        ContentValues values = new ContentValues();
        values.put(PropertyFangwuzulinareaTypeTable.TYPE_ID, areacode);
        values.put(PropertyFangwuzulinareaTypeTable.TYPE_NAME, areaname);
        return values;
    }
}
