package com.smona.app.propertymanager.data.bean;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import com.smona.app.propertymanager.PropertyApplication;
import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.IPersistence;
import com.smona.app.propertymanager.data.db.PropertyProvider;
import com.smona.app.propertymanager.data.table.PropertyErshouwupinwupinTypeTable;
import com.smona.app.propertymanager.data.table.PropertyErshouwupinxinjiuTypeTable;

public class PropertyBeanErshouwupinwupins implements IPersistence {
    public List<PropertyBeanErshouwupinwupinType> icobject;
    public List<PropertyBeanErshouwupinxinjiuType> inobject;

    @Override
    public void saveDataToDB(Context context) {
        insertWupins(context);
        insertXinjius(context);
    }

    private void insertWupins(Context context) {
        if (icobject == null || icobject.size() == 0) {
            return;
        }
        PropertyProvider provider = ((PropertyApplication) (context
                .getApplicationContext())).getPropertyProvider();
        int size = icobject.size();
        ArrayList<ContentValues> list = new ArrayList<ContentValues>();
        for (int index = 0; index < size; index++) {
            ContentValues value = icobject.get(index).onCreateDBValues();
            list.add(value);
        }
        provider.batchUpdateLocals(
                PropertyErshouwupinwupinTypeTable.getInstance().mContentUri_NoNotify,
                list, PropertyErshouwupinwupinTypeTable.TYPE_ID);
    }

    private void insertXinjius(Context context) {
        if (inobject == null || inobject.size() == 0) {
            return;
        }
        PropertyProvider provider = ((PropertyApplication) (context
                .getApplicationContext())).getPropertyProvider();
        int size = inobject.size();
        ArrayList<ContentValues> list = new ArrayList<ContentValues>();
        for (int index = 0; index < size; index++) {
            ContentValues value = inobject.get(index).onCreateDBValues();
            list.add(value);
        }
        provider.batchUpdateLocals(
                PropertyErshouwupinxinjiuTypeTable.getInstance().mContentUri_NoNotify,
                list, PropertyErshouwupinxinjiuTypeTable.TYPE_ID);
    }

}
