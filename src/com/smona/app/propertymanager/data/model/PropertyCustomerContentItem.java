package com.smona.app.propertymanager.data.model;

import java.util.ArrayList;
import java.util.List;

import com.smona.app.propertymanager.data.table.PropertyCustomerTable;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class PropertyCustomerContentItem extends PropertyContentItem {
    public String customerid;
    public String communtiycode;
    public String propertyphone;
    public List<String> pictureurl;
    public String username;
    public String userphone;
    public String useraddress;

    public void loadDBData(Context context) {
        setQueryParams(
                PropertyCustomerTable.getInstance().mContentUri_NoNotify, null,
                null, null, null);

        setLoadCallback(new LoadCallback() {
            @Override
            public void processCursor(Cursor cursor) {
                addCustomer(cursor);
            }
        });
        executeQuery(context);
    }

    private void addCustomer(Cursor cursor) {
        int nameIndex = cursor
                .getColumnIndex(PropertyCustomerTable.CUSTOMER_NAME);
        int phoneIndex = cursor
                .getColumnIndex(PropertyCustomerTable.CUSTOMER_PHONE);
        int picIndex = cursor
                .getColumnIndex(PropertyCustomerTable.CUSTOMER_PICTURE);
        int addIndex = cursor
                .getColumnIndex(PropertyCustomerTable.CUSTOMER_ADDRESS);
        int propertyIndex = cursor
                .getColumnIndex(PropertyCustomerTable.PROPERTY_PHONE);

        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String phone = cursor.getString(phoneIndex);
            String picUrl = cursor.getString(picIndex);
            String address = cursor.getString(addIndex);
            String property = cursor.getString(propertyIndex);

            username = name;
            userphone = phone;
            pictureurl = new ArrayList<String>();
            pictureurl.add(picUrl);
            useraddress = address;
            propertyphone = property;
        }
    }

    public PropertyCustomerContentItem() {

    }

    public PropertyCustomerContentItem(Parcel in) {
        username = in.readString();
        userphone = in.readString();
        useraddress = in.readString();
        propertyphone = in.readString();
        pictureurl = new ArrayList<String>();
        in.readList(pictureurl, ClassLoader.getSystemClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(userphone);
        dest.writeString(useraddress);
        dest.writeString(propertyphone);
        dest.writeList(pictureurl);
    }

    public static final Parcelable.Creator<PropertyCustomerContentItem> CREATOR = new Creator<PropertyCustomerContentItem>() {
        @Override
        public PropertyCustomerContentItem[] newArray(int size) {
            return new PropertyCustomerContentItem[size];
        }

        @Override
        public PropertyCustomerContentItem createFromParcel(Parcel in) {
            return new PropertyCustomerContentItem(in);
        }
    };

    public String toString() {
        return "PropertyCustomerContentItem[username: " + username
                + ", userphone: " + userphone + ", useraddress: " + useraddress
                + ", pictureurl: " + pictureurl + "]";
    }
}
