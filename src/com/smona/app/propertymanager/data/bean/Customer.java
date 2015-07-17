package com.smona.app.propertymanager.data.bean;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import com.smona.app.propertymanager.PropertyApplication;
import com.smona.app.propertymanager.data.db.PropertyProvider;
import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.IPersistence;
import com.smona.app.propertymanager.data.table.PropertyCustomerTable;

public class Customer implements IPersistence {
    public String customerid;
    public String communtiycode;
    public String propertyphone;
    public List<String> pictureurl;
    public String username;
    public String userphone;
    public String useraddress;

    @Override
    public void saveDataToDB(Context context) {
        ContentValues values = new ContentValues();
        values.put(PropertyCustomerTable.CUSTOMER_NAME, username);
        values.put(PropertyCustomerTable.CUSTOMER_PHONE, userphone);
        values.put(PropertyCustomerTable.CUSTOMER_PICTURE, pictureurl.get(0));
        values.put(PropertyCustomerTable.CUSTOMER_ADDRESS, useraddress);
        values.put(PropertyCustomerTable.PROPERTY_PHONE, propertyphone);

        PropertyProvider provider = ((PropertyApplication) context
                .getApplicationContext()).getPropertyProvider();
        provider.insert(
                PropertyCustomerTable.getInstance().mContentUri_NoNotify,
                values);
    }
}
