package com.smona.app.propertymanager.data.model;

import java.util.ArrayList;

import com.smona.app.propertymanager.data.table.PropertyFangwuzulinareaTypeTable;
import com.smona.app.propertymanager.data.table.PropertyFangwuzulinhuxingTypeTable;
import com.smona.app.propertymanager.data.table.PropertyFangwuzulinyewuTypeTable;

import android.content.Context;
import android.database.Cursor;

public class PropertyFangwuzulinTypeItem extends PropertyTypeItem {
    public ArrayList<PropertyFangwuzulinyewuTypeItem> yewus;
    public ArrayList<PropertyFangwuzulinhuxingTypeItem> hourse;
    public ArrayList<PropertyFangwuzulinareaTypeItem> areas;

    public PropertyFangwuzulinTypeItem() {
        yewus = new ArrayList<PropertyFangwuzulinyewuTypeItem>();
        hourse = new ArrayList<PropertyFangwuzulinhuxingTypeItem>();
        areas = new ArrayList<PropertyFangwuzulinareaTypeItem>();
    }

    public void loadDBData(Context context) {
        loadYewus(context);
        loadIcObjects(context);
        loadIaObjects(context);
    }

    private void loadYewus(Context context) {
        setQueryParams(
                PropertyFangwuzulinyewuTypeTable.getInstance().mContentUri_NoNotify,
                null, null, null, null);

        setLoadCallback(new LoadCallback() {
            @Override
            public void processCursor(Cursor cursor) {
                addYewu(cursor);
            }
        });
        executeQuery(context);
    }

    private void addYewu(Cursor cursor) {
        int nameIndex = cursor
                .getColumnIndex(PropertyFangwuzulinyewuTypeTable.TYPE_NAME);
        int idIndex = cursor
                .getColumnIndex(PropertyFangwuzulinyewuTypeTable.TYPE_ID);

        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String id = cursor.getString(idIndex);

            PropertyFangwuzulinyewuTypeItem info = new PropertyFangwuzulinyewuTypeItem();
            info.type_id = id;
            info.type_name = name;

            yewus.add(info);
        }
    }

    private void loadIcObjects(Context context) {
        setQueryParams(
                PropertyFangwuzulinhuxingTypeTable.getInstance().mContentUri_NoNotify,
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
                .getColumnIndex(PropertyFangwuzulinhuxingTypeTable.TYPE_NAME);
        int idIndex = cursor
                .getColumnIndex(PropertyFangwuzulinhuxingTypeTable.TYPE_ID);

        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String id = cursor.getString(idIndex);

            PropertyFangwuzulinhuxingTypeItem info = new PropertyFangwuzulinhuxingTypeItem();
            info.type_id = id;
            info.type_name = name;

            hourse.add(info);
        }
    }

    private void loadIaObjects(Context context) {
        setQueryParams(
                PropertyFangwuzulinareaTypeTable.getInstance().mContentUri_NoNotify,
                null, null, null, null);

        setLoadCallback(new LoadCallback() {
            @Override
            public void processCursor(Cursor cursor) {
                addIaObject(cursor);
            }
        });
        executeQuery(context);
    }

    private void addIaObject(Cursor cursor) {
        int nameIndex = cursor
                .getColumnIndex(PropertyFangwuzulinareaTypeTable.TYPE_NAME);
        int idIndex = cursor
                .getColumnIndex(PropertyFangwuzulinareaTypeTable.TYPE_ID);

        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String id = cursor.getString(idIndex);

            PropertyFangwuzulinareaTypeItem info = new PropertyFangwuzulinareaTypeItem();
            info.type_id = id;
            info.type_name = name;

            areas.add(info);
        }
    }

    public String toString() {
        return "PropertyTaosujianyiContentItem==[yewu: " + yewus.size()
                + ",huxing: " + hourse.size() + ",area: " + areas.size() + "]";
    }
}
