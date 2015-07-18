package com.smona.app.propertymanager.data.bean;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import com.smona.app.propertymanager.PropertyApplication;
import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.IPersistence;
import com.smona.app.propertymanager.data.db.PropertyProvider;
import com.smona.app.propertymanager.data.table.PropertyFangwuzulinhuxingTypeTable;
import com.smona.app.propertymanager.data.table.PropertyFangwuzulinareaTypeTable;
import com.smona.app.propertymanager.data.table.PropertyFangwuzulinyewuTypeTable;

public class PropertyBeanFangwuzulinType implements IPersistence {
    public List<PropertyBeanFangwuzulinYewuType> yewu;
    public List<PropertyBeanFangwuzulinHuxingType> icobject;
    public List<PropertyBeanFangwuzulinAreaType> iaobject;

    @Override
    public void saveDataToDB(Context context) {
        insertYewuTypes(context);
        insertHuxingTypes(context);
        insertAreaTypes(context);
    }

    private void insertYewuTypes(Context context) {
        if (yewu == null || yewu.size() == 0) {
            return;
        }
        PropertyProvider provider = ((PropertyApplication) (context
                .getApplicationContext())).getPropertyProvider();
        int size = yewu.size();
        ArrayList<ContentValues> list = new ArrayList<ContentValues>();
        for (int index = 0; index < size; index++) {
            ContentValues value = yewu.get(index).onCreateDBValues();
            list.add(value);
        }
        provider.batchUpdateLocals(
                PropertyFangwuzulinyewuTypeTable.getInstance().mContentUri_NoNotify,
                list, PropertyFangwuzulinyewuTypeTable.TYPE_ID);
    }

    private void insertHuxingTypes(Context context) {
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
                PropertyFangwuzulinhuxingTypeTable.getInstance().mContentUri_NoNotify,
                list, PropertyFangwuzulinhuxingTypeTable.TYPE_ID);
    }

    private void insertAreaTypes(Context context) {
        if (iaobject == null || iaobject.size() == 0) {
            return;
        }
        PropertyProvider provider = ((PropertyApplication) (context
                .getApplicationContext())).getPropertyProvider();
        int size = iaobject.size();
        ArrayList<ContentValues> list = new ArrayList<ContentValues>();
        for (int index = 0; index < size; index++) {
            ContentValues value = iaobject.get(index).onCreateDBValues();
            list.add(value);
        }
        provider.batchUpdateLocals(
                PropertyFangwuzulinareaTypeTable.getInstance().mContentUri_NoNotify,
                list, PropertyFangwuzulinareaTypeTable.TYPE_ID);
    }

    public String toString() {
        return "PropertyBeanFangwuzulinType[yewu:" + yewu + ",house:"
                + icobject + ",area: " + iaobject + "]";
    }
}
