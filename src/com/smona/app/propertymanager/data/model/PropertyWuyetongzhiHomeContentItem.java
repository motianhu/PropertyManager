package com.smona.app.propertymanager.data.model;

import java.util.List;

import android.content.Context;

public class PropertyWuyetongzhiHomeContentItem extends PropertyContentItem {
    public PropertyCustomerContentItem customer = new PropertyCustomerContentItem();
    public String pageno;
    public String pagesize;
    public String totalrecord;
    public List<PropertyWuyetongzhiContentItem> icobject;

    public void loadDBData(Context context) {
        customer.loadDBData(context);
    }

    public String toString() {
        return "PropertyWuyetongzhiHomeContentItem[customer:" + customer
                + ",pageno: " + pageno + ",pagesize: " + pagesize
                + ",totalrecord: " + totalrecord + ",icobject: " + icobject
                + "]";
    }
}
