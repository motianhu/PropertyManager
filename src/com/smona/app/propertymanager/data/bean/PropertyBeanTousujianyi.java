package com.smona.app.propertymanager.data.bean;

import java.util.ArrayList;
import java.util.List;

import com.smona.app.propertymanager.PropertyApplication;
import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.IPersistence;
import com.smona.app.propertymanager.data.db.PropertyProvider;
import com.smona.app.propertymanager.data.table.PropertyTousujianyiTypeTable;

import android.content.ContentValues;
import android.content.Context;

public class PropertyBeanTousujianyi implements IPersistence {
    public PropertyBeanCustomer customer;
    public String msg;
    public List<PropertyBeanTousujianyiType> icobject;

    public String iccode;
    public String loginname;
    public String sessionid;
    public String answercode;

    public String toString() {
        return "PropertyBeanTousujianyi[customer:" + customer + ", msg: " + msg
                + ", icobject: " + icobject + ", iccode: " + iccode
                + ", loginname: " + loginname + ", sessionid: " + sessionid
                + ", answercode: " + answercode + "]";
    }

    @Override
    public void saveDataToDB(Context context) {
        insertCustomer(context);
        insertTypes(context);
    }

    private void insertCustomer(Context context) {
        customer.saveDataToDB(context);
    }

    private void insertTypes(Context context) {
        PropertyProvider provider = ((PropertyApplication) (context
                .getApplicationContext())).getPropertyProvider();
        int size = icobject.size();
        ArrayList<ContentValues> list = new ArrayList<ContentValues>();
        for (int index = 0; index < size; index++) {
            ContentValues value = icobject.get(index).onCreateDBValues();
            list.add(value);
        }
        provider.batchUpdateLocals(
                PropertyTousujianyiTypeTable.getInstance().mContentUri_NoNotify,
                list, PropertyTousujianyiTypeTable.TYPE_ID);
    }
}
