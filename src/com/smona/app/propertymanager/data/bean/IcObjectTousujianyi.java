package com.smona.app.propertymanager.data.bean;

import android.content.ContentValues;

import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.ICreateDBValues;
import com.smona.app.propertymanager.data.table.PropertyTousujianyiTypeTable;

public class IcObjectTousujianyi implements ICreateDBValues {
    public String complaintcode;
    public String complaintname;

    @Override
    public ContentValues onCreateDBValues() {
        ContentValues values = new ContentValues();
        values.put(PropertyTousujianyiTypeTable.TYPE_ID, complaintcode);
        values.put(PropertyTousujianyiTypeTable.TYPE_NAME, complaintname);
        return values;
    }
}
