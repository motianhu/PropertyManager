package com.smona.app.propertymanager.data.model;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.smona.app.propertymanager.data.table.PropertyErshouwupinpinpaiTypeTable;
import com.smona.app.propertymanager.data.table.PropertyErshouwupinwupinTypeTable;
import com.smona.app.propertymanager.data.table.PropertyErshouwupinxinjiuTypeTable;

public class PropertyErshouwupinTypeItem extends PropertyTypeItem {
    public ArrayList<PropertyErshouwupinpinpaiTypeItem> pinpais;
    public ArrayList<PropertyErshouwupinwupinTypeItem> wupins;
    public ArrayList<PropertyErshouwupinxinjiuTypeItem> xinjius;

    public PropertyErshouwupinTypeItem() {
        pinpais = new ArrayList<PropertyErshouwupinpinpaiTypeItem>();
        wupins = new ArrayList<PropertyErshouwupinwupinTypeItem>();
        xinjius = new ArrayList<PropertyErshouwupinxinjiuTypeItem>();
    }

    public void loadDBData(Context context) {
        loadWupins(context);
        loadXinjius(context);
    }

    public void loadPinpais(Context context) {
        pinpais.clear();
        setQueryParams(
                PropertyErshouwupinpinpaiTypeTable.getInstance().mContentUri_NoNotify,
                null, null, null, null);

        setLoadCallback(new LoadCallback() {
            @Override
            public void processCursor(Cursor cursor) {
                addPinpai(cursor);
            }
        });
        executeQuery(context);
    }

    private void addPinpai(Cursor cursor) {
        int nameIndex = cursor
                .getColumnIndex(PropertyErshouwupinpinpaiTypeTable.TYPE_NAME);
        int idIndex = cursor
                .getColumnIndex(PropertyErshouwupinpinpaiTypeTable.TYPE_ID);

        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String id = cursor.getString(idIndex);

            PropertyErshouwupinpinpaiTypeItem info = new PropertyErshouwupinpinpaiTypeItem();
            info.type_id = id;
            info.type_name = name;
            pinpais.add(info);
        }
    }

    private void loadWupins(Context context) {
        setQueryParams(
                PropertyErshouwupinwupinTypeTable.getInstance().mContentUri_NoNotify,
                null, null, null, null);

        setLoadCallback(new LoadCallback() {
            @Override
            public void processCursor(Cursor cursor) {
                addWupin(cursor);
            }
        });
        executeQuery(context);
    }

    private void addWupin(Cursor cursor) {
        int nameIndex = cursor
                .getColumnIndex(PropertyErshouwupinwupinTypeTable.TYPE_NAME);
        int idIndex = cursor
                .getColumnIndex(PropertyErshouwupinwupinTypeTable.TYPE_ID);

        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String id = cursor.getString(idIndex);

            PropertyErshouwupinwupinTypeItem info = new PropertyErshouwupinwupinTypeItem();
            info.type_id = id;
            info.type_name = name;

            wupins.add(info);
        }
    }

    private void loadXinjius(Context context) {
        setQueryParams(
                PropertyErshouwupinxinjiuTypeTable.getInstance().mContentUri_NoNotify,
                null, null, null, null);

        setLoadCallback(new LoadCallback() {
            @Override
            public void processCursor(Cursor cursor) {
                addXinjiu(cursor);
            }
        });
        executeQuery(context);
    }

    private void addXinjiu(Cursor cursor) {
        int nameIndex = cursor
                .getColumnIndex(PropertyErshouwupinxinjiuTypeTable.TYPE_NAME);
        int idIndex = cursor
                .getColumnIndex(PropertyErshouwupinxinjiuTypeTable.TYPE_ID);

        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String id = cursor.getString(idIndex);

            PropertyErshouwupinxinjiuTypeItem info = new PropertyErshouwupinxinjiuTypeItem();
            info.type_id = id;
            info.type_name = name;

            xinjius.add(info);
        }
    }

    public String toString() {
        return "PropertyTaosujianyiContentItem==[yewu: " + pinpais.size()
                + ",wupins: " + wupins.size() + ", xinjius: " + xinjius.size()
                + "]";
    }
}
