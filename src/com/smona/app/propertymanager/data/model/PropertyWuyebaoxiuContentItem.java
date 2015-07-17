package com.smona.app.propertymanager.data.model;

import java.util.ArrayList;

import com.smona.app.propertymanager.data.table.PropertyWuyebaoxiuTypeTable;

import android.content.Context;
import android.database.Cursor;

public class PropertyWuyebaoxiuContentItem extends PropertyContentItem {
    public ArrayList<PropertyItemInfo> types;
    public PropertyCustomerContentItem customer;

    public PropertyWuyebaoxiuContentItem() {
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
                PropertyWuyebaoxiuTypeTable.getInstance().mContentUri_NoNotify,
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
                .getColumnIndex(PropertyWuyebaoxiuTypeTable.TYPE_NAME);
        int idIndex = cursor
                .getColumnIndex(PropertyWuyebaoxiuTypeTable.TYPE_ID);

        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String id = cursor.getString(idIndex);

            PropertyWuyebaoxiuTypeItem info = new PropertyWuyebaoxiuTypeItem();
            info.type_id = id;
            info.type_name = name;

            types.add(info);
        }
    }

    public String toString() {
        return "PropertyWuyebaoxiuContentItem==[ customer: " + customer.toString()
                + ", types: " + types.size() + "]";
    }
}
