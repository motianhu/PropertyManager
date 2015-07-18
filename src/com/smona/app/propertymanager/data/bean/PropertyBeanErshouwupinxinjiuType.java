package com.smona.app.propertymanager.data.bean;

import android.content.ContentValues;

import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.ICreateDBValues;
import com.smona.app.propertymanager.data.table.PropertyErshouwupinxinjiuTypeTable;

public class PropertyBeanErshouwupinxinjiuType implements ICreateDBValues {
    public String newcode;
    public String newname;

    @Override
    public ContentValues onCreateDBValues() {
        ContentValues values = new ContentValues();
        values.put(PropertyErshouwupinxinjiuTypeTable.TYPE_ID, newcode);
        values.put(PropertyErshouwupinxinjiuTypeTable.TYPE_NAME, newname);
        return values;
    }
}
