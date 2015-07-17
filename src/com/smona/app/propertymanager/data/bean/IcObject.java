package com.smona.app.propertymanager.data.bean;

import android.content.ContentValues;

import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.ICreateDBValues;
import com.smona.app.propertymanager.data.table.PropertyWuyebaoxiuTypeTable;

public class IcObject implements ICreateDBValues {
    public String repaircode;
    public String repairname;

    @Override
    public ContentValues onCreateDBValues() {
        ContentValues values = new ContentValues();
        values.put(PropertyWuyebaoxiuTypeTable.TYPE_ID, repaircode);
        values.put(PropertyWuyebaoxiuTypeTable.TYPE_NAME, repairname);
        return values;
    }
}
