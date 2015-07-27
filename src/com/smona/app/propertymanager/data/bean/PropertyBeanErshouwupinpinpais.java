package com.smona.app.propertymanager.data.bean;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import com.smona.app.propertymanager.PropertyApplication;
import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.IPersistence;
import com.smona.app.propertymanager.data.db.PropertyProvider;
import com.smona.app.propertymanager.data.table.PropertyErshouwupinpinpaiTypeTable;

public class PropertyBeanErshouwupinpinpais implements IPersistence {
    public List<PropertyBeanErshouwupinpinpaiType> icobject;

    @Override
    public void saveDataToDB(Context context) {
        insertPinpais(context);
    }

    private void insertPinpais(Context context) {
        if (icobject == null || icobject.size() == 0) {
            return;
        }
        PropertyProvider provider = ((PropertyApplication) (context
                .getApplicationContext())).getPropertyProvider();
        provider.delete(
                PropertyErshouwupinpinpaiTypeTable.getInstance().mContentUri_NoNotify,
                null, null);

        int size = icobject.size();
        ArrayList<ContentValues> list = new ArrayList<ContentValues>();
        for (int index = 0; index < size; index++) {
            ContentValues value = icobject.get(index).onCreateDBValues();
            list.add(value);
        }
        provider.batchUpdateLocals(
                PropertyErshouwupinpinpaiTypeTable.getInstance().mContentUri_NoNotify,
                list, PropertyErshouwupinpinpaiTypeTable.TYPE_ID);
    }

}
