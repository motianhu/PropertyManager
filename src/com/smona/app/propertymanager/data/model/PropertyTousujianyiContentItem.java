package com.smona.app.propertymanager.data.model;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.smona.app.propertymanager.data.table.PropertyTousujianyiTypeTable;

public class PropertyTousujianyiContentItem extends PropertyContentItem {
    public PropertyCustomerContentItem customer;
    public ArrayList<PropertyItemInfo> types;

    public PropertyTousujianyiContentItem() {
        types = new ArrayList<PropertyItemInfo>();
        customer = new PropertyCustomerContentItem();
    }

    public void loadDBData(Context context) {
        loadCustomer(context);
        loadIcObjects(context);
    }

    private void loadCustomer(Context context) {
        customer.loadDBData(context);
    }

    private void loadIcObjects(Context context) {
        setQueryParams(
                PropertyTousujianyiTypeTable.getInstance().mContentUri_NoNotify,
                null, null, null, null);

        setLoadCallback(new LoadCallback() {
            @Override
            public void processCursor(Cursor cursor) {
                addIcObject(cursor);
            }
        });
        executeQuery(context);
    }

    private void addIcObject(Cursor cursor) {
        int nameIndex = cursor
                .getColumnIndex(PropertyTousujianyiTypeTable.TYPE_NAME);
        int idIndex = cursor
                .getColumnIndex(PropertyTousujianyiTypeTable.TYPE_ID);

        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String id = cursor.getString(idIndex);

            PropertyTousujianyiTypeItem info = new PropertyTousujianyiTypeItem();
            info.type_id = id;
            info.type_name = name;

            types.add(info);
        }
    }

    public String toString() {
        return "PropertyTaosujianyiContentItem==[ customer: "
                + customer.toString() + ", types: " + types.size() + "]";
    }
}
